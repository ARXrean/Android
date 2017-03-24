package com.example.kzy.musicplayerzz;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText etRegisterAccount=null;
    private EditText etRegisterPassword=null;
    private EditText etRegisterPasswordAgain=null;
    private Button btnRegister=null;
    private OkHttpClient okHttpClient = new OkHttpClient();

    private String  result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        intiBindControl();
        setOnclickFunction();
    }

    public void intiBindControl(){
        etRegisterAccount=(EditText)findViewById(R.id.etRegisterAccount);
        etRegisterPassword=(EditText)findViewById(R.id.etRegisterPassword);
        etRegisterPasswordAgain=(EditText)findViewById(R.id.etRegisterPasswordAgain);
        btnRegister=(Button)findViewById(R.id.btnRegister);
    }

    public void setOnclickFunction(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etRegisterAccount.getText().toString().equals("") || etRegisterPassword.getText().toString().equals("") || etRegisterPasswordAgain.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"empty!",Toast.LENGTH_SHORT).show();
                }
                else if(!etRegisterPassword.getText().toString().equals(etRegisterPasswordAgain.getText().toString())){
                    Toast.makeText(getApplicationContext(),"password not same!",Toast.LENGTH_SHORT).show();
                }
                else{
                    new Thread(postThread).start();
                }
            }
        });
    }

    private Thread postThread=new Thread() {
        public void run() {
            String url="http://192.168.155.1:8080/MusicPlayer/userRegister";
            doPost(url,etRegisterAccount.getText().toString(),etRegisterPassword.getText().toString());
        }
    };

    public void doPost(String url,String userId,String userPassword){
        FormBody body = new FormBody.Builder().add("userRegisterId",userId).add("userRegisterPassword",userPassword).build();

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
                if(result.equals("registerSuccess")){
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"register false",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
