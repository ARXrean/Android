package com.example.kzy.musicplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button signIn;
    private TextView txRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInitWindow();

        signIn = (Button) findViewById(R.id.signIn);
        txRegister = (TextView) findViewById(R.id.txRegister);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Okhttp okhttp = new Okhttp();
//                okhttp.doLoginPost();
//                String loginResult = okhttp.getRes();

                Intent toList = new Intent(MainActivity.this, ListActicity.class);
                startActivity(toList);
            }
        });
    }

    public void setInitWindow(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        // 加上这句设置为全屏 不加则只隐藏title  
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
