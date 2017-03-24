package com.example.kzy.musicplayerzz;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
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

/**
 * Created by kzy on 2017/3/22.
 */

public class OnlineListActivity extends Activity implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener{
    private ListView onLineListView;
    private SimpleAdapter myAdapter;
    private List<Map<String,Object>> dataList;
    private OkHttpClient okHttpClient = new OkHttpClient();

    private Button btnPlay=null;
    private Button btnStop=null;
    private Button btnLike=null;
    private Button btnNext=null;

    private String result;
    private String url=null;
    private int musicIndex;
    private String userId=null;

    public MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onlinelist);

        connectServer();
    }

    public void bindControl(){
        userId=this.getIntent().getExtras().getString("userId");
        btnPlay=(Button)findViewById(R.id.btnPlay);
        btnStop=(Button)findViewById(R.id.btnStop);
        btnLike=(Button)findViewById(R.id.btnLike);
        btnNext=(Button)findViewById(R.id.btnNext);

        player = new MediaPlayer();

        onLineListView=(ListView)findViewById(R.id.onlineListView);
        dataList = new ArrayList<Map<String,Object>>();

        myAdapter = new SimpleAdapter(this, getData(), R.layout.activity_onlinelistitem, new String[]{"name", "maker"},
                new int[]{R.id.onlineMusicName, R.id.onlineMusicMaker});

        onLineListView.setAdapter(myAdapter);
    }

    private List<Map<String,Object>> getData(){
        String[] musicArray=result.split(":");
        for(int i=0; i<musicArray.length; i++){
            Map<String, Object>map = new HashMap<String, Object>();
            map.put("name",musicArray[i].split("-")[0]);
            map.put("maker",musicArray[i].split("-")[1]);
            dataList.add(map);
        }


        return dataList;
    }

    public void connectServer(){
        new Thread(postThread).start();
    }

    private Thread postThread=new Thread() {
        public void run() {
            String url="http://192.168.155.1:8080/MusicPlayer/music";
            doPost(url,"all");
        }
    };

    public void doPost(String url,String type){
        FormBody body = new FormBody.Builder().add("type",type).build();

        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).post(body).build();

        asynExecuteRequest(request);
    }

    public void doPostAddLocal(String url,String type,String userId,String musicName,String musicMaker){
        FormBody body = new FormBody.Builder().add("type",type).add("userId",userId).add("musicName",musicName).add("musicMaker",musicMaker).build();

        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).post(body).build();

        asynExecuteRequestLocalPost(request);
    }

    public void asynExecuteRequest(Request request){

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

    public void asynExecuteRequestLocalPost(Request request){

        //将Request封装为Call
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.body().string().equals("addFalse")){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(OnlineListActivity.this, "Already exist", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(OnlineListActivity.this, "add success", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    public void onClickFunction(){
        onLineListView.setOnItemClickListener(this);

        onLineListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return true;
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnPlay.getText() == "Play"){
                    if(url == null)
                        Toast.makeText(OnlineListActivity.this, "Please choose one", Toast.LENGTH_SHORT).show();
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

        btnLike.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        String urlLocal="http://192.168.155.1:8080//MusicPlayer/music";
                        doPostAddLocal(urlLocal,"addLocalMusic",userId,dataList.get(musicIndex).get("name").toString(),dataList.get(musicIndex).get("maker").toString());
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

        onLineListView.setSelector(R.color.colorBlue);
    }


    public void Stop(){
        player.stop();
        btnPlay.setText("Play");
        url = null;
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
            Toast.makeText(OnlineListActivity.this, "Failed to play", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IllegalStateException e) {
            Toast.makeText(OnlineListActivity.this, "Failed to play", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(OnlineListActivity.this, "Failed to play", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
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
        Log.e("abc",url);
        Play();
    }

}
