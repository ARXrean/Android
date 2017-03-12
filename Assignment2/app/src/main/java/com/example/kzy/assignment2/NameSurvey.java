package com.example.kzy.assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

/**
 * Created by kzy on 2017/3/11.
 */

public class NameSurvey extends Activity{
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_survey);

        editText=(EditText)findViewById(R.id.etName);
        final Button goName=(Button)findViewById(R.id.btnNext);
        goName.setEnabled(false);
        goName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    DealTextFile dealTextFile=new DealTextFile();
                    dealTextFile.writeFile("Name:"+editText.getText().toString());
                Intent intent = new Intent(NameSurvey.this, SexSurvey.class);
                startActivity(intent);
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()>0){
                    goName.setEnabled(true);
                }
                else{
                    goName.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
