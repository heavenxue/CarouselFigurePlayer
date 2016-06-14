package com.iapppay.lixue.carouselfigureplayer;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.lixue.aibei.universalimageloaderlib.cache.disc.naming.Md5FileNameGenerator;
import com.lixue.aibei.universalimageloaderlib.core.ImageLoaderConfiguration;
import com.lixue.aibei.universalimageloaderlib.core.UniversalImageLoader;
import com.lixue.aibei.universalimageloaderlib.core.assist.QueueProcessingType;

/**
 * Created by Administrator on 2016/6/14.
 */
public class UILApplication extends Application {

    @Override
    public void onCreate() {
        if (Constants.Config.DEVELOPER_MODE && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyDialog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyDeath().build());
        }

        super.onCreate();

        initImageLoader(getApplicationContext());
    }

    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);//线程优先级3
        config.denyCacheImageMultipleSizesInMemory();//不缓存多个大小的图像
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());//缓存文件名
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);//按照先进先出队列处理线程
        config.writeDebugLogs(); // Remove for release app
        config.isDecodeGif(true);

        // Initialize ImageLoader with configuration.
        UniversalImageLoader.getInstance().init(config.build());
    }
}
