package com.bobo.bobodmeo.net;

import android.app.Activity;

import com.example.libnet.net.RNet;

import retrofit2.Retrofit;

/**
 * Created by smart on 18-10-9.
 * 在基础网络框架 libNet 上再次进行封装
 *
 */

public class RNetUtil {

    private RetrofitService retrofitService;
    private static volatile RNetUtil rNetUtil;


    public static RNetUtil getInstance(){
        if(rNetUtil == null){
            synchronized (RNetUtil.class){
                if(rNetUtil == null){
                    rNetUtil = new RNetUtil();
                }
            }
        }
        return rNetUtil;
    }

    /**
     * 获取 ApiSerVice
     * @return
     */
    public RetrofitService getApiService(){
        Retrofit retrofit = RNet.getInstance().getRetrofit();
        if (retrofit == null) {
            throw new NullPointerException("retrofit == null ,RNet init fail");
        }
        if(retrofitService == null){
            retrofitService = retrofit.create(RetrofitService.class);
        }
        return retrofitService;
    }

    /**
     * 显示loading dialog
     * @param activity
     */
    public RNetUtil showLoadingDialog(Activity activity){
        RNet.getInstance().showLoadingDialog(activity);
        return this;
    }


}
