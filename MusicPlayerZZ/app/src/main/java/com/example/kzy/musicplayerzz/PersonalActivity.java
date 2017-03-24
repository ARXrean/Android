package com.example.kzy.musicplayerzz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PersonalActivity extends AppCompatActivity {
    private TextView textView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        bindControl();
    }

    public void bindControl(){
        textView=(TextView)findViewById(R.id.tvUserId);
        textView.setText(this.getIntent().getExtras().getString("userId").toString());
    }
}
