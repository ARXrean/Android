package com.example.kzy.musicplayerzz;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LocalListActivity extends Activity implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener{
    private ListView localListView;
    private SimpleAdapter myAdapter;
    private List<Map<String,Object>> dataList;
    private OkHttpClient okHttpClient = new OkHttpClient();

    private Button btnPlay=null;
    private Button btnStop=null;
    private Button btnDelete=null;
    private Button btnNext=null;
    private Button btnRefresh=null;

    private String userId;
    private String result;
    private String url;

    int musicIndex;

    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locallist);

        connectToServer();
    }

    public void bindControl(){
        player = new MediaPlayer();

        btnPlay=(Button)findViewById(R.id.btnPlay);
        btnStop=(Button)findViewById(R.id.btnStop);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        btnNext=(Button)findViewById(R.id.btnNext);
        btnRefresh=(Button)findViewById(R.id.btnRefresh);

        localListView=(ListView)findViewById(R.id.localListView);
        dataList = new ArrayList<Map<String,Object>>();

        myAdapter = new SimpleAdapter(this, getData(), R.layout.activity_onlinelistitem, new String[]{"name", "maker"},
                new int[]{R.id.onlineMusicName, R.id.onlineMusicMaker});

        localListView.setAdapter(myAdapter);
    }

    public void onClickFunction(){
        localListView.setOnItemClickListener(this);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnPlay.getText() == "Play"){
                    if(url == null)
                        Toast.makeText(LocalListActivity.this, "Please choose one", Toast.LENGTH_SHORT).show();
                    else{
                        player.start();
                        btnPlay.setText("Pause");
                    }
                } else{
                    player.pause();
                    btnPlay.setText("Play");
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Stop();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                player.reset();
                musicIndex++;
                if(musicIndex==dataList.size()){
                    musicIndex=0;
                }
                url="http://192.168.155.1:8080//MusicPlayer/Music/"+dataList.get(musicIndex).get("name")+"-"+dataList.get(musicIndex).get("maker")+".mp3";
                Play();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        String urlLocal="http://192.168.155.1:8080//MusicPlayer/music";
                        doPostDelete(urlLocal,"deleteMusic",musicIndex,userId);
                    }
                }.start();
            }
        });

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                player.reset();
                musicIndex++;
                if(musicIndex==dataList.size()){
                    musicIndex=0;
                }
                url="http://192.168.155.1:8080//MusicPlayer/Music/"+dataList.get(musicIndex).get("name")+"-"+dataList.get(musicIndex).get("maker")+".mp3";
                Play();
            }
        });

        btnRefresh.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        String urlLocal="http://192.168.155.1:8080//MusicPlayer/music";
                        doPostRefresh(urlLocal,"allLocal",userId);
                    }
                }.start();
            }
        });

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                player.reset();
                musicIndex++;
                if(musicIndex==dataList.size()){
                    musicIndex=0;
                }
                url="http://192.168.155.1:8080//MusicPlayer/Music/"+dataList.get(musicIndex).get("name")+"-"+dataList.get(musicIndex).get("maker")+".mp3";
                Play();
            }
        });

        localListView.setSelector(R.color.colorBlue);
    }



    public void Play(){
        try {
            player.setDataSource(url);
            player.prepareAsync();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    player.start();
                    btnPlay.setText("Pause");
                }
            });
        } catch (IllegalArgumentException e) {
            Toast.makeText(LocalListActivity.this, "Failed to play", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IllegalStateException e) {
            Toast.makeText(LocalListActivity.this, "Failed to play", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(LocalListActivity.this, "Failed to play", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void Stop(){
        player.stop();
        btnPlay.setText("Play");
        url = null;
    }

    private List<Map<String,Object>> getData(){
        if(!result.equals("")){
            String[] musicArray=result.split(":");
            for(int i=0; i<musicArray.length; i++){
                Map<String, Object>map = new HashMap<String, Object>();
                map.put("name",musicArray[i].split("-")[0]);
                map.put("maker",musicArray[i].split("-")[1]);
                dataList.add(map);
            }
        }
        else{

        }

        return dataList;
    }

    public void connectToServer(){
        userId=this.getIntent().getExtras().getString("userId");
        new Thread(){
            @Override
            public void run() {
                String url="http://192.168.155.1:8080/MusicPlayer/music";
                doPostLocal(url,"allLocal",userId);
            }
        }.start();
    }

    public void doPostLocal(String url,String type,String userId){
        FormBody body = new FormBody.Builder().add("type",type).add("userId",userId).build();

        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).post(body).build();

        asynExecuteRequestLocal(request);
    }

    public void doPostRefresh(String url,String type,String userId){
        FormBody body = new FormBody.Builder().add("type",type).add("userId",userId).build();

        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).post(body).build();

        asynExecuteRequestRefresh(request);
    }

    public void doPostDelete(String url,String type,int i,String userId){
        FormBody body = new FormBody.Builder().add("type",type).add("musicName",dataList.get(i).get("name").toString()).add("userId",userId).build();

        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).post(body).build();

        asynExecuteRequestDelete(request);
    }

    public void asynExecuteRequestLocal(Request request){

        //将Request封装为Call
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                result = response.body().string();
                bindControl();
                onClickFunction();
            }
        });
    }

    public void asynExecuteRequestDelete(Request request){

        //将Request封装为Call
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.body().string().equals("deleteSuccess")){
                    btnRefresh.callOnClick();
                }
            }
        });
    }

    public void asynExecuteRequestRefresh(Request request){

        //将Request封装为Call
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                result = response.body().string();
                Log.e("abc",result);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dataList = new ArrayList<Map<String,Object>>();

                        myAdapter = new SimpleAdapter(LocalListActivity.this, getData(), R.layout.activity_onlinelistitem, new String[]{"name", "maker"},
                                new int[]{R.id.onlineMusicName, R.id.onlineMusicMaker});

                        localListView.setAdapter(myAdapter);
                    }
                });
            }
        });
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        musicIndex=i;
        player.reset();
        url="http://192.168.155.1:8080//MusicPlayer/Music/"+dataList.get(i).get("name")+"-"+dataList.get(i).get("maker")+".mp3";
        Toast.makeText(getApplicationContext(),"start play",Toast.LENGTH_SHORT).show();
        Play();
    }
}
