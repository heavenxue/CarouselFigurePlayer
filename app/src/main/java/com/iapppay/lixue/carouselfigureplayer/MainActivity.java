package com.iapppay.lixue.carouselfigureplayer;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {

    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        initData();
    }

    private void initview(){
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        setContentView(viewpager);
    }

    private void initData(){
        viewpager.setAdapter(new FragmentListPagerAdapter(getSupportFragmentManager(),new SwingFragment(),new CircleFragment()));
    }
}
