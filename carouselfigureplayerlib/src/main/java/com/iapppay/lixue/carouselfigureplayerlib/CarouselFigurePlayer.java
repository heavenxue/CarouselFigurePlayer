package com.iapppay.lixue.carouselfigureplayerlib;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * 轮播图
 * Created by Administrator on 2016/6/13.
 */
public class CarouselFigurePlayer extends ViewPager {
    private boolean isPlaying;//是否播放中
    private PlayController playController;

    public CarouselFigurePlayer(Context context) {
        super(context);
    }

    public CarouselFigurePlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean isPlaying(){
        return isPlaying;
    }
}
