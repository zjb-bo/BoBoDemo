package com.bobo.bobodmeo.net;

import android.app.Activity;
import android.app.Dialog;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by Zjb
 * Company:iwhere chengdu technology
 * date: 2017/10/10
 * 类描述：
 */

public class RNet {
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;
    private ApiService apiService;
    private static RNet rNet;
    private Dialog loadingDialog;


    private RNet() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com")
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
    public ApiService getApiService(){
        if (retrofit == null) {
            throw new NullPointerException("retrofit == null ,RNet 初始化失败");
        }
        if(apiService == null){
            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }


    /**
     * 默认的 OkHttpClient
     * @return
     */
    private OkHttpClient getOkClient() {
        okHttpClient = new OkHttpClient.Builder()
                        .addInterceptor(new LoggerInterceptor())
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30,TimeUnit.SECONDS)
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
            throw new NullPointerException("setOkHttpClient不能为空");
        }else {
            this.okHttpClient = okHttpClient;
        }
    }

    public RNet showLoadingDialog(Activity activity){
        if(!activity.isDestroyed()){
            if(loadingDialog == null){
                loadingDialog = new LoadingDialog(activity);
            }
            loadingDialog.show();
        }
        return this;
    }

    public void hideLoadingDialog(){
        if(loadingDialog != null && loadingDialog.isShowing()){
            loadingDialog.dismiss();
        }
    }

    public void release(){
        if(loadingDialog != null){
            loadingDialog.cancel();
            loadingDialog = null;
        }
    }

}
