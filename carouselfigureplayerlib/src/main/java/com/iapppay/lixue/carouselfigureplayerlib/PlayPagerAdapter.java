package com.iapppay.lixue.carouselfigureplayerlib;

import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

/**
 * 轮播图适配器
 * Created by Administrator on 2016/6/13.
 */
public abstract class PlayPagerAdapter extends PagerAdapter implements PlayAdapterInterface{
    PlayMode playMode = PlayMode.CIRCLE;//默认是转圈
    @Override
    public final int getCount() {
        switch(playMode){
            case CIRCLE :
                return getRealCount() > 1?getRealCount() * 1000:getRealCount();
            case SWING :
                return getRealCount();
            default :
                return 0;
        }
    }

    @Override
    public final void destroyItem(ViewGroup container, int position, Object object) {
        destroyRealItem(container, getRealPosition(position), object);
    }

    @Override
    public final Object instantiateItem(ViewGroup container, int position) {
        return instantiateRealItem(container, getRealPosition(position));
    }

    @Override
    public int getRealPosition(int position) {
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

    /**
     * 销毁真实的项
     * @param container
     * @param realPosition
     * @param object
     */
    public abstract void destroyRealItem(ViewGroup container,int realPosition,Object object);

    /**
     * 初始化真实的项
     * @param container
     * @param realPosition
     * @return
     */
    public abstract Object instantiateRealItem(ViewGroup container,int realPosition);

    @Override
    public PlayMode getViewPlayMode() {
        return playMode;
    }

    @Override
    public void setViewPlayMode(PlayMode viewPlayMode) {
        this.playMode = viewPlayMode;
    }
}
