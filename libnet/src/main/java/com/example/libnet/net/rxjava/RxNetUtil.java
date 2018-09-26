package com.example.libnet.net.rxjava;

import com.example.libnet.net.BaseBean;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by smart on 18-9-26.
 */

public class RxNetUtil {

    public static <T> Observable compoose(Observable<BaseBean> observable){
//        return observable.compose(new SimpleTransformer<T>());
        return observable.flatMap(new Func1<BaseBean, Observable<?>>() {
            @Override
            public Observable<T> call(BaseBean baseBean) {
                return null;
            }
        });
    }



    public static class SimpleTransformer<T> implements Observable.Transformer<BaseBean, T> {

        @Override
        public Observable<T> call(Observable<BaseBean> observable) {
            return observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
                    .timeout(5, TimeUnit.SECONDS)//重连间隔时间
                    .retry(5)//重连次数
                    .flatMap(new Func1<BaseBean, Observable<T>>() {
                        @Override
                        public Observable<T> call(BaseBean baseBean) {
                            return null;
                        }
                    });
        }
    }
}
