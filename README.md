#CarouselFigurePlayer

## 使用方法：

直接在xml文件中引用

```java
<com.iapppay.lixue.carouselfigureplayerlib.PictureViewPlayer
        android:id="@+id/myViewPager"
        android:layout_width="match_parent"
        android:layout_height="300dp"
        lixue:indicator = "@drawable/selector_radio_play_indicator"
        lixue:isNeedIndicationPoint = "true"
        >
    </com.iapppay.lixue.carouselfigureplayerlib.PictureViewPlayer>
```

然后直接对它进行设置适配器即可~

## 自定义属性
指定：指示器图片
    lixue:indicator = "@drawable/selector_radio_play_indicator"<br/>
是否可以显示指示器图片
    lixue:isNeedIndicationPoint = "true"

## 特点
* 图片可以进行循环轮播`pictureViewPlayer.getViewPlayer().setViewPlayMode(PlayMode.CIRCLE);`
* 图片可以摆动轮播  设置`pictureViewPlayer.getViewPlayer().setViewPlayMode(PlayMode.SWING);`
    

## 文章：
[思考--博客](http://www.jianshu.com/p/63671c05fbe8)