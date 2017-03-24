package com.example.kzy.musicplayerzz;

import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;
import java.net.URL;
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

public class CircleListActivity extends Activity implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener {

    private VideoView video1;
    MediaController mediaco;

    private ListView circleListView;
    private SimpleAdapter myAdapter;
    private List<Map<String,Object>> dataList;
    private OkHttpClient okHttpClient = new OkHttpClient();

    private String result;
    int videoIndex;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circlelist);

        video1=(VideoView)findViewById(R.id.vvMV);
        mediaco=new MediaController(CircleListActivity.this);
        video1.setMediaController(mediaco);

        connectServer();
    }

    public void bindControl(){
        circleListView=(ListView)findViewById(R.id.lvMV);

        dataList = new ArrayList<Map<String,Object>>();

        myAdapter = new SimpleAdapter(this, getData(), R.layout.activity_onlinelistitem, new String[]{"name", "maker"},
                new int[]{R.id.onlineMusicName, R.id.onlineMusicMaker});

        circleListView.setAdapter(myAdapter);
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
        new Thread(){
            @Override
            public void run() {
                String url="http://192.168.155.1:8080/MusicPlayer/video";
                doPostAllVideo(url,"allVideo");
            }
        }.start();
    }

    public void doPostAllVideo(String url,String type){
        FormBody body = new FormBody.Builder().add("type",type).build();

        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).post(body).build();

        asynExecuteRequestAllVideo(request);
    }

    public void onClickFunction(){
        circleListView.setOnItemClickListener(this);

        circleListView.setSelector(R.color.colorBlue);
    }

    public void asynExecuteRequestAllVideo(Request request){

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

    public void playVideo(){
        Uri uri=Uri.parse(url);
        video1.setVideoURI(uri);
        video1.requestFocus();
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        videoIndex=i;
        url="http://192.168.155.1:8080//MusicPlayer/Video/"+dataList.get(i).get("name")+"-"+dataList.get(i).get("maker")+".mp4";
        Log.e("abc",url);
        playVideo();
    }
}
