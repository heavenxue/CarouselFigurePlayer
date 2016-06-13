package com.iapppay.lixue.carouselfigureplayerlib;

/**
 * 播放器适配接口
 * Created by Administrator on 2016/6/13.
 */
public interface PlayAdapterInterface {

    /**
     * 获取真实数量
     * @return
     */
    public int getRealCount();

    /**
     * 获取真实位置
     * @param position
     * @return 真实位置
     */
    public int getRealPosition(int position);

    /**
     * 获取播放模式
     * @return
     */
    public PlayMode getViewPlayMode();

    /**
     * 设置播放模式
     * @param viewPlayMode
     */
    public void setViewPlayMode(PlayMode viewPlayMode);
}
