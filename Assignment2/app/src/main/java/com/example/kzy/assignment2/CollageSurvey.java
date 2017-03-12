package com.example.kzy.assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;

import java.io.IOException;

/**
 * Created by kzy on 2017/3/11.
 */

public class CollageSurvey extends Activity {
    private ListView listView=null;
    private String[] listViewData=new String[]{"重庆大学","川美","重医","重师","重庆科技学院","第三军医大"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collage_survey);
        initView();
        initEvent();
    }

    protected void initView(){
        listView=(ListView)findViewById(R.id.lvCollege);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listViewData));
    }

    protected  void initEvent(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    DealTextFile dealTextFile=new DealTextFile();
                    dealTextFile.writeFile("College:"+listView.getItemAtPosition(i).toString());
                Intent intent = new Intent(CollageSurvey.this,SubjectSurvey.class);
                startActivity(intent);
            }
        });
    }

}
