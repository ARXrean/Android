package com.example.kzy.zzmusicplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    private EditText etRegisterAccount=null;
    private EditText etRegisterPassword=null;
    private EditText etRegisterPasswordAgain=null;
    private Button btnRegister=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
                if(etRegisterAccount.getText().equals("") || etRegisterPassword.getText().equals("") || etRegisterPasswordAgain.getText().equals("")){
                    Toast.makeText(getApplicationContext(),"empty!",Toast.LENGTH_SHORT).show();
                }
                else if(!etRegisterPassword.getText().equals(etRegisterPasswordAgain.getText())){
                    Toast.makeText(getApplicationContext(),"password not same!",Toast.LENGTH_SHORT).show();
                }
                else{
//                    HttpPost httpRequest=new HttpPost("http://192.168.155.1:8080/MusicPlayer/userRegister");
//                    //创建参数
//                    List<NameValuePair> params=new ArrayList<NameValuePair>();
//                    params.add(new BasicNameValuePair("userId",etRegisterAccount.getText().toString()));
//                    params.add (new BasicNameValuePair("userPassword", etRegisterPassword.getText().toString()));
//
//                    try {
//                        httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//
//                        HttpResponse httpResponse=new DefaultHttpClient().execute(httpRequest);
//                        if(httpResponse.getStatusLine().getStatusCode()==200){
//                            byte[] data=new byte[2048];
//                            data= EntityUtils.toByteArray((HttpEntity)httpResponse.getEntity());
//
//                            ByteArrayInputStream bais=new ByteArrayInputStream(data);
//                            DataInputStream dis=new DataInputStream(bais);
//                            String user=new String(dis.readUTF());
//                            Toast.makeText(getApplicationContext(),user,Toast.LENGTH_SHORT).show();
//                        }
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    } catch (ClientProtocolException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        });
    }
}
