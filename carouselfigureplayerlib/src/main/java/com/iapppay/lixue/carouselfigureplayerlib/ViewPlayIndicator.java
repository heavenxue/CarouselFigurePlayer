package com.iapppay.lixue.carouselfigureplayerlib;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * View播放指示器
 * Created by Administrator on 2016/6/14.
 */
public class ViewPlayIndicator extends LinearLayout{
    private final static String TAG = "ViewPlayIndicator";
    private int lastCheckedPosition;//上次选中的图标的位置
    private Drawable indicatorDrawable;//指示器图片
    private int indicatorDrawableMargin;//外边距
    private int count;

    public ViewPlayIndicator(Context context) {
        this(context, null);
    }

    public ViewPlayIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        setGravity(Gravity.CENTER);
    }

    /**
     * 设置图标总数
     * @param count
     */
    public void setCount(int count){
        if (count != this.count){
            this.count = count;
            removeAllViews();
            if (count > 1 && indicatorDrawable != null){//有图片的话
                //然后初始化所有图标并放进图标的布局中
                for (int i = 0;i < count;i ++){
                    ImageView image = new ImageView(getContext());
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(indicatorDrawableMargin, indicatorDrawableMargin, indicatorDrawableMargin, indicatorDrawableMargin);//设置指示器内图标的外边距
                    image.setLayoutParams(params);
                    image.setImageDrawable(indicatorDrawable);
                    addView(image);
                }
                setVisibility(View.VISIBLE);
            }else{
                setVisibility(View.GONE);
            }
        }
    }

    /**
     * 设置图标的图片
     * @param indicatorDrawable
     */
    public void setIndicatorDrawable(Drawable indicatorDrawable){
        this.indicatorDrawable = indicatorDrawable;
        int oldCount = count;
        count = 0;
        setCount(oldCount);
    }
    /**
     * 设置图标外边距
     * @param indicatorDrawableMargin
     */
    public void setIndicatorDrawableMargin(int indicatorDrawableMargin) {
        this.indicatorDrawableMargin = indicatorDrawableMargin;
        int oldCount = count;
        count = 0;
        setCount(oldCount);
    }

    /**
     * 选中
     * @param selectedItemPosition
     */
    public void selected(int selectedItemPosition) {
        if(getChildCount() > 0 && selectedItemPosition < getChildCount()){
            // 取余后的索引，得到新的page的索引
//            int newPositon = selectedItemPosition % getChildCount();
            // 把上一个点设置为被选中
            (getChildAt(lastCheckedPosition)).setSelected(false);
            // 根据索引设置那个点被选中
            (getChildAt(selectedItemPosition)).setSelected(true);
            // 新索引赋值给上一个索引的位置
            lastCheckedPosition = selectedItemPosition;
//            (getChildAt(lastCheckedPosition)).setSelected(false);
//            (getChildAt(selectedItemPosition)).setSelected(true);
//            Log.i(TAG,"selectedItemPosition:" + selectedItemPosition + "，总数："+getChildCount());
//            Log.i(TAG,"lastCheckedPosition :" + lastCheckedPosition);
//            lastCheckedPosition = selectedItemPosition;//记录本次选中的+
        }
    }
}
