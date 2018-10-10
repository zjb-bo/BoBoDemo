package com.example.libnet.net.rxjava;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.libnet.example.NetApp;
import com.example.libnet.net.BaseBean;
import com.example.libnet.net.HandleNetResult;
import com.example.libnet.net.NetConstantsConfig;
import com.example.libnet.net.RNet;

import java.net.SocketException;
import java.util.concurrent.TimeUnit;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by smart on 18-9-26.
 * 类描述: Rxjava的网络辅助类
 * 网络结果统一处理, 网络请求预处理
 */

public class RxNetHelper {
    private static HandleNetResult mHandleNetResult = new RxDefaultHandleNetResult();

    public static void initHandleNetResult(@NonNull HandleNetResult result) {
        mHandleNetResult = result;
    }

    public static <T> Observable.Transformer<BaseBean<T>, T> handleResult() {
        return new Observable.Transformer<BaseBean<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseBean<T>> baseBeanObservable) {
                return baseBeanObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .unsubscribeOn(Schedulers.io())
                        .timeout(NetConstantsConfig.NET_TIME_OUT, TimeUnit.SECONDS)//重连间隔时间
                        .retry(NetConstantsConfig.NET_RETRY_TINE)//重连次数
                        .flatMap(new Func1<BaseBean<T>, Observable<T>>() {
                            @Override
                            public Observable<T> call(BaseBean<T> tBaseBean) {
                                RNet.getInstance().hideLoadingDialog();

                                if (tBaseBean.getServer_status() == 200) {
                                    mHandleNetResult.handleNetLogicResult(tBaseBean);
                                    return createObservable(tBaseBean.getData());
                                } else {
                                    mHandleNetResult.handleServerError(tBaseBean.getServer_status());
                                    return Observable.empty();
                                }
                            }
                        });
            }
        };
    }


    private static <T> Observable<T> createObservable(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onStart();
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    mHandleNetResult.handleNetError(e);
                    subscriber.onError(e);
                }
            }
        });
    }

    //=========================================生命周期绑定层=======================================================
















    //============================================业务逻辑统一处理层====================================================

    /**
     * 如果需要自己处理可以 自己实现HandleNetResult接口,
     * 然后通过方法{@link RxNetHelper#initHandleNetResult(HandleNetResult)}进行设置
     */
    public static class RxDefaultHandleNetResult implements HandleNetResult {

        @Override
        public void handleNetLogicResult(BaseBean baseBean) {
            //todo 统一处理共同的业务逻辑
            int typeStutue = baseBean.getTypeStutue();
            switch (typeStutue){
                case 0:
                    //未登录
                    Toast.makeText(NetApp.getApplication(), "未登录", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(NetApp.getApplication(), "token过期", Toast.LENGTH_SHORT).show();
                    //token过期
                    break;
                case 2:
                    //...
                    break;
            }
        }

        @Override
        public void handleNetError(Throwable e) {
            //todo 统一处理网络错误提示
            if(e instanceof HttpException){
                //网络链接错误
                Toast.makeText(NetApp.getApplication(), "网络链接错误", Toast.LENGTH_SHORT).show();
            }else if(e instanceof SocketException){
                //请打开网络
                Toast.makeText(NetApp.getApplication(), "请打开网络", Toast.LENGTH_SHORT).show();
            }else {
                //网络错误
                Toast.makeText(NetApp.getApplication(), "网络错误", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void handleServerError(int code) {
            //todo 统一处理服务器错误提示
            String server_status = String.valueOf(code);
            if(server_status.startsWith("4")){
                //资源不存在
                Toast.makeText(NetApp.getApplication(), "资源不存在", Toast.LENGTH_SHORT).show();
            }else if(server_status.startsWith("5")){
                //服务器内部错误
                Toast.makeText(NetApp.getApplication(), "服务器内部错误", Toast.LENGTH_SHORT).show();
            }else {
                // 其他错误
                Toast.makeText(NetApp.getApplication(), "其他错误", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
