package com.example.kzy.assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.IOException;

/**
 * Created by kzy on 2017/3/11.
 */

public class AgeSurvey extends Activity{
    private RadioGroup radioGroup;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.age_survey);

        radioGroup=(RadioGroup)findViewById(R.id.rgAge);

        button=(Button)findViewById(R.id.btnNext);
        button.setEnabled(false);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DealTextFile dealTextFile=new DealTextFile();
                dealTextFile.writeFile("Age:"+((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString());
                Intent intent = new Intent(AgeSurvey.this,CollageSurvey.class);
                startActivity(intent);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                button.setEnabled(true);
            }
        });
    }
}
