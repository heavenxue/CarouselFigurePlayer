package com.iapppay.lixue.carouselfigureplayerlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.FrameLayout;

/**
 * 轮播图
 * Created by Administrator on 2016/6/14.
 */
public class PictureViewPlayer extends FrameLayout {
    private CarouselFigurePlayer viewPlayer;
    private ViewPlayIndicator pointViewPlayIndicator;
    private int lastPosition;
//    private int indicatorDrawableResId;//指针图标
    private int indicatorDrawableMagin = 8;//指针图标外间距，默认为8

    //-------------－－自定义属性－－－－－－－－－－－－
    private Drawable indicatorDrawable ;
    private boolean isAutoPlay = true; //是否自动切换
    private boolean isInfiniteLoop = true; //是否无限循环
    private boolean isNeedIndicationPoint = true;//是否需要指示点
    private float pointLeft_Right_Margin = 13;//指示点的间距，单位是px,在布局文件中设置单位最好为dp
    private float pointBottomMargin = 5;//指示点上移的距离,单位是px，在布局文件中设置单位最好为dp

    public PictureViewPlayer(Context context) {
        super(context);
        init();
    }

    public PictureViewPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseCustomAttributes(context,attrs,0);
        init();
    }

    public PictureViewPlayer(Context context,AttributeSet attributeSet,int def){
        super(context,attributeSet,def);
        parseCustomAttributes(context,attributeSet,def);
        init();
    }

    /**
     * 初始化
     */
    private void init(){
        viewPlayer = new CarouselFigurePlayer(getContext());
        viewPlayer.setId(viewPlayer.hashCode());
        addView(viewPlayer);

        pointViewPlayIndicator = new ViewPlayIndicator(getContext());
        pointViewPlayIndicator.setId(pointViewPlayIndicator.hashCode());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        addView(pointViewPlayIndicator, layoutParams);

        viewPlayer.setOnSetAdapterListener(new CarouselFigurePlayer.OnSetAdapterListener() {
            @Override
            public void onSetAdapter() {
                if(viewPlayer.getAdapter() != null){
                    pointViewPlayIndicator.setIndicatorDrawableMargin(indicatorDrawableMagin);
                    pointViewPlayIndicator.setCount(viewPlayer.getRealCount());
                    pointViewPlayIndicator.setIndicatorDrawable(indicatorDrawable);
                    pointViewPlayIndicator.selected(viewPlayer.getRealCurrentItem());
                }
            }
        });

        viewPlayer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pointViewPlayIndicator.selected(viewPlayer.getRealCurrentItem(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 解析自定义属性
     */
    private void parseCustomAttributes(Context context, AttributeSet attrs,int defStyleAttr) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,R.styleable.CarouselFigureView, defStyleAttr, 0);
        if (typedArray != null){
            int a = typedArray.getIndexCount();
            for (int i = 0;i<a;i++){
                int attr = typedArray.getIndex(i);
                if (attr == R.styleable.CarouselFigureView_isAutoPlay) {
                    isAutoPlay = typedArray.getBoolean(attr, true);
                }else if(attr == R.styleable.CarouselFigureView_indicator){
                    indicatorDrawable = typedArray.getDrawable(attr);
                    if (indicatorDrawable != null){
                        Log.i("PictureViewPlayer","indicatorDrawable");
                    }
                }
            }
            typedArray.recycle();
        }
    }

    public CarouselFigurePlayer getViewPlayer() {
        return viewPlayer;
    }

    public ViewPlayIndicator getViewPlayIndicator() {
        return pointViewPlayIndicator;
    }

    public int getIndicatorDrawableMagin() {
        return indicatorDrawableMagin;
    }

    public void setIndicatorDrawableMagin(int indicatorDrawableMagin) {
        this.indicatorDrawableMagin = indicatorDrawableMagin;
    }
}
