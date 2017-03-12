package com.example.kzy.assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.io.IOException;

/**
 * Created by kzy on 2017/3/11.
 */

public class GradeSurvey extends Activity {
    private Spinner spinner;
    String string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grade_survey);

        spinner=(Spinner)findViewById(R.id.spGrade);
        spinner.setSelection(1,true);

        Button goName=(Button)findViewById(R.id.btnNext);
        goName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    DealTextFile dealTextFile=new DealTextFile();
                    dealTextFile.writeFile("Grade:"+(String)spinner.getSelectedItem());
                Intent intent = new Intent(GradeSurvey.this,DomnumSurvey.class);
                startActivity(intent);
            }
        });
    }
}
