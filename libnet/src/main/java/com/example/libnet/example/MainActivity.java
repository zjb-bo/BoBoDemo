package com.example.libnet.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.libnet.R;
import com.example.libnet.net.ApiCallBack;
import com.example.libnet.net.BaseBean;
import com.example.libnet.net.RNet;

/**
 * Created by smart on 18-9-26.
 */

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_dialog);

        loadData();
    }

    private void loadData() {
        //request BusinessApiService for search book;
        RNet.getInstance()
                .showLoadingDialog(this)
                .getApiService(BusinessApiService.class)
                .getUserInfo("百年孤独", 0, 2)
                .enqueue(new ApiCallBack<UserBean>() {
                    @Override
                    public void onMyResponse(BaseBean<UserBean> baseBean) {

                    }

                    @Override
                    public void onMyFailure(Throwable t) {

                    }
                });

        //request LoginService for login;
        RNet.getInstance()
                .showLoadingDialog(this)
                .getApiService(LoginApiService.class)
                .login("zjb", "****", 151131)
                .enqueue(new ApiCallBack<UserBean>() {
                    @Override
                    public void onMyResponse(BaseBean<UserBean> baseBean) {

                    }

                    @Override
                    public void onMyFailure(Throwable t) {

                    }
                });
    }


}