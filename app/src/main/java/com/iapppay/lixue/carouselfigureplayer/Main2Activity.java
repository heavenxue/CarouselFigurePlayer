package com.iapppay.lixue.carouselfigureplayer;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Main2Activity extends AppCompatActivity {
    LinearLayout imageButton;
    ViewPager viewPager;
    int lastPostion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageButton = (LinearLayout) findViewById(R.id.imagebutton);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new PicturePagerAdapter(getSupportFragmentManager(),Constants.IMAGES));
        for (int i = 0;i < 5;i ++){
            ImageView image = new ImageView(getBaseContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 8, 8, 8);//设置指示器内图标的外边距
            image.setLayoutParams(params);
//                    Log.d(TAG,"--------setImageResource-------------");
//                    image.setImageResource(indicatorDrawableId);
            image.setImageDrawable(getResources().getDrawable(R.drawable.selector_radio_play_indicator));
//            image.setImageResource(R.drawable.selector_radio_play_indicator);
            imageButton.addView(image);
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                imageButton.getChildAt(lastPostion).setSelected(false);
                imageButton.getChildAt(position).setSelected(true);
                lastPostion = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}

