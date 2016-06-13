package com.iapppay.lixue.carouselfigureplayerlib;

/**
 * 轮播图控制器
 * Created by Administrator on 2016/6/13.
 */
public class PlayController implements Runnable{

    private int swichSpace;//切换间隔
    private boolean currentTowardsTheRight;//是否向右轮播
    private PlayMode playMode = PlayMode.CIRCLE;//默认是转圈
    private CarouselFigurePlayer player; //轮播器

    public PlayController(CarouselFigurePlayer carouselFigurePlayer){
        this.player = carouselFigurePlayer;
    }

    @Override
    public void run() {
        if (player.isPlaying()){
            if (player.getAdapter().getCount() > 1){
                int nextItem = 0;
                switch (playMode){
                    case CIRCLE:
                        nextItem = (player.getCurrentItem() + 1) % player.getAdapter().getCount();
                        break;
                    case SWING:
                        //如果当前是向右播放
                        if (currentTowardsTheRight){
                            //如果到最后一张图片
                            if (player.getCurrentItem() == player.getAdapter().getCount() -1){
                                currentTowardsTheRight = false;
                                nextItem = player.getAdapter().getCount() - 1;
                            }else{
                                nextItem = player.getCurrentItem() + 1;
                            }
                        }else{
                            //如果到了第一张图片
                            if (player.getCurrentItem() == 0){
                                currentTowardsTheRight = true;
                                nextItem = player.getCurrentItem() + 1;
                            }else{
                                nextItem = player.getCurrentItem() - 1;
                            }
                        }
                        break;
                }
                player.setCurrentItem(nextItem,true);
            }
            player.postDelayed(this,swichSpace);
        }
    }

    /**
     * 启动
     **/
    public void start(){
        if (player.isPlaying()){
            player.removeCallbacks(this);
            player.postDelayed(this,swichSpace);
        }
    }

    /**
     * 停止
     **/
    public void stop(){
        if (player.isPlaying()){
            player.removeCallbacks(this);
        }
    }

    /**
     *重置
     **/
    public void reset(){
        if(player.getAdapter() != null && player.getAdapter().getCount() > 0){
            int nextItem = 0;
            if(playMode == PlayMode.CIRCLE){
                int realCount = ((PlayAdapterInterface) player.getAdapter()).getRealCount();
                if(realCount > 1){
                    nextItem = (player.getAdapter().getCount()/2) -  ((player.getAdapter().getCount()/2) % realCount);
                }else{
                    nextItem = 0;
                }
            }else{
                currentTowardsTheRight = true;
            }
            player.setCurrentItem(nextItem, true);
        }
    }

    /**
     * 设置切换间隔时间
     **/
    public void setSwichSpace(int swichSpace){
        this.swichSpace = swichSpace;
    }

    /**
     * 设置播放模式
     * 不设置，则默认是圆形
     **/
    public void setPlayMode(PlayMode playMode){
        this.playMode = playMode;
    }
}
