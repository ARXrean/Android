package com.example.kzy.assignment2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by kzy on 2017/3/12.
 */

public class TextInfo extends Activity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_info);



        textView=(TextView)findViewById(R.id.textView4);
        DealTextFile dealTextFile=new DealTextFile();
        textView.setText(dealTextFile.readFile());
    }
}
