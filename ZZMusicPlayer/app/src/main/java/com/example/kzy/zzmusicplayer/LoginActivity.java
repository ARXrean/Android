package com.example.kzy.zzmusicplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private TextView textView=null;
    private Button button=null;
    private EditText etAccount=null;
    private EditText etPassword=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


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

                }
            }
        });
    }
}
