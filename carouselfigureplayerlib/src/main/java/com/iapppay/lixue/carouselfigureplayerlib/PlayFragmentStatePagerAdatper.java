package com.iapppay.lixue.carouselfigureplayerlib;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * 播放状态适配器
 * Created by Administrator on 2016/6/14.
 */
public abstract class PlayFragmentStatePagerAdatper extends FragmentStatePagerAdapter implements PlayAdapterInterface{
    private PlayMode playMode = PlayMode.CIRCLE;//播放模式，默认转圈

    public PlayFragmentStatePagerAdatper(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        switch (playMode){
            case CIRCLE:return getRealCount() > 1 ? getRealCount() * 1000 : getRealCount();
            case SWING: return getRealCount();
                default:return 0;
        }
    }
    @Override
    public int getRealPosition(int position){
        if(getRealCount() <= 0){
            return 0;
        }
        switch(playMode){
            case CIRCLE :
                return position % getRealCount();
            case SWING :
                return position;
            default :
                return position;
        }
    }

    @Override
    public final Fragment getItem(int position) {
        return getRealItem(getRealPosition(position));
    }

    @Override
    public int getRealCount() {
        return 0;
    }

    /**
     * 获取真实的项
     * @param position
     * @return
     */
    public abstract Fragment getRealItem(int position);

    @Override
    public PlayMode getViewPlayMode() {
        return playMode;
    }

    @Override
    public void setViewPlayMode(PlayMode viewPlayMode) {
        this.playMode = viewPlayMode;
    }
}
