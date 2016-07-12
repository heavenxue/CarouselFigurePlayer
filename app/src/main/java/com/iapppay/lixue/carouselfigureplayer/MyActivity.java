package com.iapppay.lixue.carouselfigureplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.iapppay.lixue.carouselfigureplayerlib.PictureViewPlayer;

public class MyActivity extends AppCompatActivity {
    private PictureViewPlayer viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        initLayout();
        ininData();
    }

    private void initLayout(){
        viewPager = (PictureViewPlayer) findViewById(R.id.myViewPager);
    }

    private void ininData(){
        viewPager.getViewPlayer().setAdapter(new PicturePagerAdapter(getSupportFragmentManager(),Constants.IMAGES));
        viewPager.getViewPlayer().setSwitchSpace(5000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewPager.getViewPlayer().start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewPager.getViewPlayer().stop();
    }
}
