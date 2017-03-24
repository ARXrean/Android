package com.example.kzy.musicplayerzz;

import android.app.ActivityGroup;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActivityGroup {
    private List<View> viewList;
    private ZZPagerAdapter adapter;
    private ViewPager pager;
    private ImageView onlineTab, localTab, circleTab,personalTab;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindControl();
        onClickListener();
    }

    public void bindControl(){
        userId=this.getIntent().getExtras().getString("userId");
        onlineTab = (ImageView) findViewById(R.id.onlineTab);
        localTab = (ImageView) findViewById(R.id.localTab);
        circleTab=(ImageView)findViewById(R.id.circleTab);
        personalTab = (ImageView) findViewById(R.id.personalTab);

        viewList = new ArrayList<View>();

        //onlist View
        Intent onlIneListIntent=new Intent(MainActivity.this,OnlineListActivity.class);
        onlIneListIntent.putExtra("userId",userId);
        View onlinelist = getLocalActivityManager().startActivity("1",onlIneListIntent).getDecorView();

        //localList View
        Intent localListIntent=new Intent(MainActivity.this,LocalListActivity.class);
        localListIntent.putExtra("userId",userId);
        View localList = getLocalActivityManager().startActivity("1",localListIntent).getDecorView();

        //circleList
        Intent circleListIntent=new Intent(MainActivity.this,CircleListActivity.class);
        onlIneListIntent.putExtra("userId",userId);
        View circleList = getLocalActivityManager().startActivity("1",circleListIntent).getDecorView();

        //personalInfo View
        Intent personalIntent=new Intent(MainActivity.this,PersonalActivity.class);
        personalIntent.putExtra("userId",userId);
        View personalList = getLocalActivityManager().startActivity("1",personalIntent).getDecorView();

        viewList.add(onlinelist);
        viewList.add(localList);
        viewList.add(circleList);
        viewList.add(personalList);

        adapter = new ZZPagerAdapter(viewList);

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
    }

    public void onClickListener(){
        onlineTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(0);
            }
        });

        localTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(1);
            }
        });

        circleTab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(2);
            }
        });

        personalTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(3);
            }
        });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0){
                    onlineTab.setImageResource(R.mipmap.online2);
                    localTab.setImageResource(R.mipmap.local1);
                    circleTab.setImageResource(R.mipmap.circle);
                    personalTab.setImageResource(R.mipmap.personal1);
                } else if(position == 1){
                    onlineTab.setImageResource(R.mipmap.online1);
                    localTab.setImageResource(R.mipmap.local2);
                    circleTab.setImageResource(R.mipmap.circle);
                    personalTab.setImageResource(R.mipmap.personal1);
                }else if(position == 2){
                    onlineTab.setImageResource(R.mipmap.online1);
                    localTab.setImageResource(R.mipmap.local1);
                    circleTab.setImageResource(R.mipmap.circle2);
                    personalTab.setImageResource(R.mipmap.personal1);
                }else if(position==3){
                    onlineTab.setImageResource(R.mipmap.online1);
                    localTab.setImageResource(R.mipmap.local1);
                    circleTab.setImageResource(R.mipmap.circle);
                    personalTab.setImageResource(R.mipmap.personal2);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
