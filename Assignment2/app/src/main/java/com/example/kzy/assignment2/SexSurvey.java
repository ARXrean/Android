package com.example.kzy.assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.IOException;

/**
 * Created by kzy on 2017/3/11.
 */

public class SexSurvey extends Activity {
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sex_survey);

        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);

        final Button goName=(Button)findViewById(R.id.btnNext);
        goName.setEnabled(false);
        goName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    DealTextFile dealTextFile=new DealTextFile();
                    dealTextFile.writeFile("Sex:"+((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString());
                Intent intent = new Intent(SexSurvey.this,AgeSurvey.class);
                startActivity(intent);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                goName.setEnabled(true);
            }
        });
    }
}
