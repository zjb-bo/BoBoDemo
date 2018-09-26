package com.example.libnet.net;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.example.libnet.ui.BaseLoadingDialog;
import com.example.libnet.ui.DefaultLoadingDialog;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by Zjb
 * date: 2017/10/10
 * 类描述： Retrofit 网络框架得请求类
 *  T : ApiService
 */

public class RNet {
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;
    private static RNet rNet;
    private BaseLoadingDialog loadingDialog;
    private RetrofitService retrofitService;


    private RNet() {
        retrofit = new Retrofit.Builder()
                .baseUrl(NetConstantsConfig.NET_BASE_URL_ONLINE)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkClient())
                .build();

    }

    /**
     * 获取 当前类的单列
     * @return
     */
    public static RNet getInstance(){
        if(rNet == null){
            synchronized (RNet.class){
                if(rNet == null){
                    rNet = new RNet();
                }
            }
        }
        return rNet;
    }

    /**
     * 获取 ApiSerVice
     * @return
     */
    public RetrofitService getApiService(){
        if (retrofit == null) {
            throw new NullPointerException("retrofit == null ,RNet init fail");
        }
        if(retrofitService == null){
            retrofitService = retrofit.create(RetrofitService.class);
        }
        return retrofitService;
    }


    /**
     * 默认的 OkHttpClient
     * @return
     */
    private OkHttpClient getOkClient() {
        okHttpClient = new OkHttpClient.Builder()
                        .addInterceptor(new LoggerInterceptor())
                        .connectTimeout(NetConstantsConfig.NET_CONNECT_TIME_SECONDS, TimeUnit.SECONDS)
                        .readTimeout(NetConstantsConfig.NET_READ_TIME_SECONDS,TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true)
                        .build();
        //设置拦截器
        return okHttpClient;
    }

    /**
     * 供外部 设置自己的 okHttpClient
     * @param okHttpClient
     */
    public void setOkHttpClient(OkHttpClient okHttpClient){
        if(okHttpClient == null){
            throw new NullPointerException("okHttpClient can not null");
        }else {
            this.okHttpClient = okHttpClient;
        }
    }

    //show loading ui
    public RNet showLoadingDialog(@NonNull Activity activity){
        if(!activity.isDestroyed()){
            if(loadingDialog == null){
                loadingDialog = new DefaultLoadingDialog(activity);
            }
            loadingDialog.show();
        }
        return this;
    }

    /**
     * if you want custom loading ui please see {@link BaseLoadingDialog}
     * @param baseLoadingDialog
     */
    public void setLoadingDialog(@NonNull BaseLoadingDialog baseLoadingDialog){
        loadingDialog = baseLoadingDialog;
    }

    //hide loading ui and release
    public void hideLoadingDialog(){
        if(loadingDialog != null && loadingDialog.isShowing()){
            loadingDialog.cancel();
            loadingDialog = null;
        }
    }

}
