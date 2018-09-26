package com.bobo.bobodmeo.app;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by Zjb
 * date: 2017/10/10
 * 类描述：
 */

public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        initLogger();
    }

    private void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
