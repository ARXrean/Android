package com.example.kzy.assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kzy on 2017/3/11.
 */

public class DomnumSurvey extends Activity {
    private Spinner spinner;
    private Spinner spinner2;
    private List<String> listMei;
    private List<String> listLan;
    private ArrayAdapter<String> adapterMei;
    private ArrayAdapter<String> adapterLan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.domnum_survey);

        spinner=(Spinner)findViewById(R.id.domBuilding);
        spinner.setSelection(1,true);
        spinner2=(Spinner) findViewById(R.id.spDonnum);
        listMei=getMeiData();
        listLan=getLanData();
        adapterMei = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, listMei);
        adapterLan = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, listLan);
        spinner2.setAdapter(adapterLan);

        initEvent();

        Button goName=(Button)findViewById(R.id.btnNext);
        goName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    DealTextFile dealTextFile=new DealTextFile();
                    dealTextFile.writeFile("Dom:"+(String)spinner.getSelectedItem()+"-"+(String)spinner2.getSelectedItem());
                Intent intent = new Intent(DomnumSurvey.this,HobbySurvey.class);
                startActivity(intent);
            }
        });
    }

    protected void initEvent(){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String data = (String)spinner.getItemAtPosition(i);
                if(data.equals("Mei")){
                    spinner2.setAdapter(adapterMei);
                }
                else{
                    spinner2.setAdapter(adapterLan);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    protected  List<String> getMeiData(){
        List<String> list = new ArrayList<String>();
        for(int i=10;i<30;i++){
            list.add("2"+i);
        }
        return list;
    }

    protected List<String> getLanData(){
        List<String> list = new ArrayList<String>();
        for(int i=15;i<25;i++){
            list.add("5"+i);
        }
        return list;
    }
}
