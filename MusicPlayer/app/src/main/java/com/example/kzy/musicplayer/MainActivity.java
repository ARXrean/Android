package com.example.kzy.musicplayer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.os.Message;



public class MainActivity extends AppCompatActivity {

    private Button signIn;
    private TextView txRegister;
    private ProgressDialog progressDialog = null;  //定义弹窗
    private Context context = this;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
//            hideProgressDialog();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInitWindow();

        signIn = (Button) findViewById(R.id.signIn);
        txRegister = (TextView) findViewById(R.id.txRegister);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                showProgressDialog("提示", "正在登录，请稍后......");
                Intent toList = new Intent(MainActivity.this, ListActicity.class);

                new Thread(){
                    @Override
                    public void run(){
                        Intent toList = new Intent(MainActivity.this, ListActicity.class);
                        context.startActivity(toList);
                        mHandler.sendMessage(new Message());
                    }
                }.start();
            }
        });
    }

    public void setInitWindow(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        // 加上这句设置为全屏 不加则只隐藏title  
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    //显示弹窗
    public void showProgressDialog(String title, String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle(title);
            progressDialog.setMessage(message);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle(title);
            progressDialog.setMessage(message);
        }
        progressDialog.show();
    }

    //关闭弹窗
    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
