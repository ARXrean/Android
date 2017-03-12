package com.example.kzy.assignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DealTextFile dealTextFile=new DealTextFile();
        dealTextFile.deleteOriginalFile();
        Button goName=(Button)findViewById(R.id.btnStart);
        goName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NameSurvey.class);
                startActivity(intent);
            }
        });
    }

}
