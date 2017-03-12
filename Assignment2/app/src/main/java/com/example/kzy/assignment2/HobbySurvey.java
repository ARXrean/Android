package com.example.kzy.assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;

import java.io.IOException;

/**
 * Created by kzy on 2017/3/11.
 */

public class HobbySurvey extends Activity {
    private String text;
    private  CheckBox checkBox;
    private  CheckBox checkBox2;
    private  CheckBox checkBox3;
    private  CheckBox checkBox4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hobby_survey);

        text="";
        checkBox=(CheckBox)findViewById(R.id.checkBox);
        checkBox2=(CheckBox)findViewById(R.id.checkBox2);
        checkBox3=(CheckBox)findViewById(R.id.checkBox3);
        checkBox4=(CheckBox)findViewById(R.id.checkBox4);

        Button goName=(Button)findViewById(R.id.btnNext);
        goName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    DealTextFile dealTextFile=new DealTextFile();
                    dealTextFile.writeFile("Hobbies:"+text);
                Intent intent = new Intent(HobbySurvey.this,FutureSurvey.class);
                startActivity(intent);
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    text+=checkBox.getText().toString();
                }
            }
        });

        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    text+=checkBox2.getText().toString();
                }
            }
        });

        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    text+=checkBox3.getText().toString();
                }
            }
        });

        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    text+=checkBox4.getText().toString();
                }
            }
        });
    }
}
