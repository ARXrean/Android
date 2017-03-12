package com.example.kzy.assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

/**
 * Created by kzy on 2017/3/12.
 */

public class EndSurvey extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_survey);

        Button goName=(Button)findViewById(R.id.btnNext);
        goName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EndSurvey.this,TextInfo.class);
                startActivity(intent);
            }
        });
    }
}
