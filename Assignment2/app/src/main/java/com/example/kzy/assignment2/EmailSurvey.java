package com.example.kzy.assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.io.IOException;

/**
 * Created by kzy on 2017/3/12.
 */

public class EmailSurvey extends Activity {
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_survey);

        editText=(EditText)findViewById(R.id.editText);

        final Button goName=(Button)findViewById(R.id.btnNext);
        goName.setEnabled(false);
        goName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    DealTextFile dealTextFile=new DealTextFile();
                    dealTextFile.writeFile("Email:"+editText.getText().toString());
                    Intent intent = new Intent(EmailSurvey.this, EndSurvey.class);
                    startActivity(intent);
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()<=0){
                    goName.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editText.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+") && editable.length() > 0)
                {
                    goName.setEnabled(true);
                }
                else
                {
                    goName.setEnabled(false);
                }

            }
        });
    }
}
