package com.example.kzy.musicplayer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;



/**
 * Created by 骈纬国 on 2017/3/16.
 */

public class Onlinelist extends Activity implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener{
    private ListView onlineListView;
    private SimpleAdapter myAdapter;
    private List<Map<String,Object>> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onlinelist);
        onlineListView = (ListView) findViewById(R.id.onlineListView);
        dataList = new ArrayList<Map<String,Object>>();

        myAdapter = new SimpleAdapter(this, getData(), R.layout.onlineitem, new String[]{"pic", "text"},
                new int[]{R.id.onlineImage, R.id.onlineText});

        onlineListView.setAdapter(myAdapter);
    }

    private List<Map<String,Object>> getData(){
        for(int i=0; i<20; i++){
            Map<String, Object>map = new HashMap<String, Object>();
            map.put("pic",R.mipmap.personal2);
            map.put("text","Item"+i);
            dataList.add(map);
        }


        return dataList;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
