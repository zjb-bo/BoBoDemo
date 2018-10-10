package com.bobo.bobodmeo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bobo.bobodmeo.bean.UserBeanMain;
import com.bobo.bobodmeo.net.RNetUtil;
import com.example.libnet.net.ApiCallBack;
import com.example.libnet.net.BaseBean;
import com.example.libnet.net.rxjava.RxNetHelper;

import rx.Subscriber;

/**
 * Created by Zjb
 * date: 2017/10/10
 * 类描述：测试
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
    }

    private void loadData() {
        //不使用 Rxjava , 只使用Retrofit 模式
//        RNetUtil.getInstance()
//                .showLoadingDialog(this)
//                .getApiService()
//                .getUserInfo("量子力学", 0, 2)
//                .enqueue(new ApiCallBack<UserBeanMain>() {
//                    @Override
//                    public void handleServerError(int code) {
//
//                    }
//
//                    @Override
//                    public void onMyResponse(BaseBean<UserBeanMain> baseBean) {
//                        Toast.makeText(MainActivity.this, baseBean.getInfo(), Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onMyFailure(Throwable t) {
//                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });


        //使用 Rxjava + Retrofit 模式
        RNetUtil.getInstance()
                .showLoadingDialog(this)
                .getApiService()
                .getRxUserInfo("量子力学", 0, 2)
                .compose(RxNetHelper.<UserBeanMain>handleResult())//预处理变换
                .subscribe(new Subscriber<UserBeanMain>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UserBeanMain userBeanMain) {
                        Toast.makeText(MainActivity.this, userBeanMain.getMusicUrl(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

}
