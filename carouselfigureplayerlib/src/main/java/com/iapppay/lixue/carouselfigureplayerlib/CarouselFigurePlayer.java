package com.iapppay.lixue.carouselfigureplayerlib;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * 轮播图
 * Created by Administrator on 2016/6/13.
 */
public class CarouselFigurePlayer extends ViewPager {
    private boolean isPlaying;//是否播放中
    private PlayController playController;
    private PlayMode viewPlayMode = PlayMode.CIRCLE;//播放模式，默认是转圈
    private OnSetAdapterListener onSetAdapterListener;


    public CarouselFigurePlayer(Context context) {
        super(context);
        setAnimationDuration(500);
    }

    public CarouselFigurePlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAnimationDuration(500);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        if (adapter == null || !(adapter instanceof PlayAdapterInterface)){
            throw new IllegalArgumentException("适配器必须继实现PagerPlayAdapterInterface接口");
        }
        super.setAdapter(adapter);

        if(playController == null){
            playController = new PlayController(this);
        }

        playController.setPlayMode(viewPlayMode);
        ((PlayAdapterInterface) adapter).setViewPlayMode(viewPlayMode);
        adapter.notifyDataSetChanged();
        playController.reset();

        if(onSetAdapterListener != null){
            onSetAdapterListener.onSetAdapter();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (playController != null && isPlaying){
            switch (ev.getAction()){
                case MotionEvent.ACTION_DOWN:
                    playController.stop();
                    break;
                case MotionEvent.ACTION_UP:
                    playController.start();
                    break;
                case MotionEvent.ACTION_CANCEL:
                    playController.start();
                    break;
                default:break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public boolean isPlaying(){
        return isPlaying;
    }
    /**
     * 启动
     */
    public void start(){
        if(playController != null && !isPlaying){
            isPlaying = true;
            playController.start();
        }
    }

    /**
     * 停止
     */
    public void stop(){
        if(playController != null && isPlaying){
            playController.stop();
            isPlaying = false;
        }
    }

    /**
     * 重置
     */
    public void reset(){
        if(playController != null){
            playController.reset();
        }
    }

    /**
     * 设置播放模式
     * @param viewPlayMode
     */
    public void setViewPlayMode(PlayMode viewPlayMode) {
        this.viewPlayMode = viewPlayMode;

        if(playController != null){
            playController.setPlayMode(viewPlayMode);
            playController.reset();
        }

        if(getAdapter() != null){
            ((PlayAdapterInterface) getAdapter()).setViewPlayMode(viewPlayMode);
            getAdapter().notifyDataSetChanged();
        }
    }

    /**
     * 获取真实数量
     * @return
     */
    public int getRealCount(){
        if(getAdapter() != null && getAdapter() instanceof PlayAdapterInterface){
            return ((PlayAdapterInterface) getAdapter()).getRealCount();
        }else{
            return 0;
        }
    }

    /**
     * 获取真实位置
     * @param currentItem
     * @return 真实位置
     */
    public int getRealCurrentItem(int currentItem){
        if(getAdapter() != null && getAdapter() instanceof PlayAdapterInterface){
            return ((PlayAdapterInterface) getAdapter()).getRealPosition(currentItem);
        }else{
            return currentItem;
        }
    }

    /**
     * 获取真实位置
     * @return 真实位置
     */
    public int getRealCurrentItem(){
        return getRealCurrentItem(getCurrentItem());
    }

    /**
     * 设置自动切换动画持续时间
     * @param duration
     */
    public void setAnimationDuration(int duration){
        if(duration > 0){
            try {
                Field field = ViewPager.class.getDeclaredField("mScroller");
                field.setAccessible(true);
                FixedSpeedScroller scroller = new FixedSpeedScroller(this.getContext(), new AccelerateDecelerateInterpolator());
                field.set(this, scroller);
                scroller.setAnimationDuration(duration);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class FixedSpeedScroller extends Scroller{
        private int animationDuration = 400;
        public FixedSpeedScroller(Context context) {
            super(context);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public FixedSpeedScroller(Context context, Interpolator interpolator, boolean flywheel) {
            super(context, interpolator, flywheel);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy,animationDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, animationDuration);
        }

        public void setAnimationDuration(int animationDuration) {
            this.animationDuration = animationDuration;
        }
    }
    /**
     * 适配器监听器
     **/
    public interface OnSetAdapterListener{
        public void onSetAdapter();
    }
    /**
     * 当设置适配器时的监听器
     * @param onSetAdapterListener
     */
    public void setOnSetAdapterListener(OnSetAdapterListener onSetAdapterListener) {
        this.onSetAdapterListener = onSetAdapterListener;
    }

    /**
     * 设置切换间隔
     * @param switchSpace 切换间隔
     */
    public void setSwitchSpace(int switchSpace) {
        if(playController != null){
            playController.setSwichSpace(switchSpace);
        }
    }

}
