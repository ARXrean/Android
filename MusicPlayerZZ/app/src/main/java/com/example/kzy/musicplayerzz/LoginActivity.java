package com.example.kzy.musicplayerzz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView textView=null;
    private Button button=null;
    private EditText etAccount=null;
    private EditText etPassword=null;

    private OkHttpClient okHttpClient = new OkHttpClient();

    private String  result = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initBindControl();
        setOnclickFunction();
    }

    public void initBindControl(){
        textView=(TextView)findViewById(R.id.tvRegister);
        button=(Button)findViewById(R.id.btnLogin);
        etAccount=(EditText)findViewById(R.id.etAccount);
        etPassword=(EditText)findViewById(R.id.etPassword);
    }

    public void setOnclickFunction(){
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etAccount.getText().equals("") || etPassword.getText().equals("")){
                    Toast.makeText(getApplicationContext(),"empty!",Toast.LENGTH_SHORT).show();
                }
                else {
                    new Thread(postThread).start();
                }
            }
        });
    }

    private Thread postThread=new Thread() {
        public void run() {
            String url="http://192.168.155.1:8080/MusicPlayer/userLogin";
            doPost(url,etAccount.getText().toString(),etPassword.getText().toString());
        }
    };

    public void doPost(String url,String userId,String userPassword){
        FormBody body = new FormBody.Builder().add("userId",userId).add("userPassword",userPassword).build();

        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).post(body).build();

        asynExecuteRequest(request);
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
                if(!result.equals("loginFalse")){
                    String[] resultArray=result.split("-");
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("userId",resultArray[0]);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"register false",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
