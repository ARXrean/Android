package com.example.kzy.musicplayer;

import android.app.DownloadManager;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 骈纬国 on 2017/3/16.
 */

public class Okhttp {

    private OkHttpClient okHttpClient = new OkHttpClient();
    private String res;
    private String serverUrl = "";

    public void setServerUrl(String url){
        serverUrl = url;
    }

    public String getRes(){
        return res;
    }

    public void doGet(){

        //创建OkHttpClient对象
//        OkHttpClient okHttpClient = new OkHttpClient();

        //构造Request
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(serverUrl + "").build();

        asynExecuteRequest(request);
    }

    public void doLoginPost(){
        FormBody body = new FormBody.Builder().add("userId","abc").add("userPassword","123").build();

        Request.Builder builder = new Request.Builder();
        Request request = builder.url(serverUrl + "").post(body).build();

        executeRequest(request);
    }

    public void executeRequest(Request request){

        Call call = okHttpClient.newCall(request);

//        执行Call
        try {
            Response response = call.execute();
            res = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void asynExecuteRequest(Request request){

        //将Request封装为Call
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("wronggggggg","onFaileure:"+e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                res = response.body().string();
                Log.e("OKKKKKKKKKKKKKK","onResponse:"+res);
            }
        });
    }
}
