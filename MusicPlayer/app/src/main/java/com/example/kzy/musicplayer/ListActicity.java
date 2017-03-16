package com.example.kzy.musicplayer;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 骈纬国 on 2017/3/14.
 */

public class ListActicity extends ActivityGroup {

    private List<View>viewList;
    private MyPagerAdapter adapter;
    private ViewPager pager;
    private ImageView onlineTab, localTab, personalTab;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_list);

        onlineTab = (ImageView) findViewById(R.id.onlineTab);
        localTab = (ImageView) findViewById(R.id.localTab);
        personalTab = (ImageView) findViewById(R.id.personalTab);

        viewList = new ArrayList<View>();
//        View onlinelist = View.inflate(this, R.layout.onlinelist, null);
        View onlinelist = getLocalActivityManager().
                startActivity("1",new Intent(ListActicity.this, Onlinelist.class)).getDecorView();
        final View locallist = View.inflate(this, R.layout.locallist, null);
        final View personal = View.inflate(this, R.layout.personal, null);

        viewList.add(onlinelist);
        viewList.add(locallist);
        viewList.add(personal);

        adapter = new MyPagerAdapter(viewList);

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

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

        personalTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(2);
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
                    personalTab.setImageResource(R.mipmap.personal1);
                } else if(position == 1){
                    onlineTab.setImageResource(R.mipmap.online1);
                    localTab.setImageResource(R.mipmap.local2);
                    personalTab.setImageResource(R.mipmap.personal1);
                }else if(position == 2){
                    onlineTab.setImageResource(R.mipmap.online1);
                    localTab.setImageResource(R.mipmap.local1);
                    personalTab.setImageResource(R.mipmap.personal2);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



}
