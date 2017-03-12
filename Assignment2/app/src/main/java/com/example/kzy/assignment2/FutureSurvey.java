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
 * Created by kzy on 2017/3/12.
 */

public class FutureSurvey extends Activity {
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.future_survey);

        radioGroup=(RadioGroup)findViewById(R.id.rgFuture);

        final Button goName=(Button)findViewById(R.id.btnNext);
        goName.setEnabled(false);
        goName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    DealTextFile dealTextFile=new DealTextFile();
                    dealTextFile.writeFile("Future:"+((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString());
                Intent intent = new Intent(FutureSurvey.this,EmailSurvey.class);
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
