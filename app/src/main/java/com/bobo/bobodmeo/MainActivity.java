package com.bobo.bobodmeo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bobo.bobodmeo.bean.BaseBean;
import com.bobo.bobodmeo.bean.User;
import com.bobo.bobodmeo.net.ApiCallBack;
import com.bobo.bobodmeo.net.RNet;

import retrofit2.Response;

/**
 * Created by Zjb
 * Company:iwhere chengdu technology
 * date: 2017/10/10
 * 类描述：
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
    }

    private void loadData() {
        RNet.getInstance()
                .showLoadingDialog(this)
                .getApiService()
                .getUserInfo("123")
                .enqueue(new ApiCallBack<BaseBean<User>>() {
                    @Override
                    public void onMyResponse(Response<BaseBean<User>> response) {

                    }
                    @Override
                    public void onMyFailure(Throwable t) {

                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        RNet.getInstance().release();
    }
}
