package com.example.libnet.example;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by Zjb
 * date: 2017/10/10
 * 类描述：
 */

public class NetApp extends Application{
    private static NetApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initLogger();
    }

    public static NetApp getApplication(){
        return app;
    }

    private void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
