package com.iapppay.lixue.carouselfigureplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.iapppay.lixue.carouselfigureplayerlib.PictureViewPlayer;

public class MyActivity extends AppCompatActivity {
    private PictureViewPlayer viewPager;
//    private LinearLayout indicatorImg;
    private int lastPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        initLayout();
        ininData();
    }

    private void initLayout(){
        viewPager = (PictureViewPlayer) findViewById(R.id.myViewPager);
//        indicatorImg = (LinearLayout) findViewById(R.id.indicatorImg);
    }

    private void ininData(){
        viewPager.getViewPlayer().setAdapter(new PicturePagerAdapter(getSupportFragmentManager(),Constants.IMAGES));
        viewPager.getViewPlayer().setSwitchSpace(3000);
        viewPager.getViewPlayIndicator().setIndicatorDrawableId(R.drawable.selector_radio_play_indicator);
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
