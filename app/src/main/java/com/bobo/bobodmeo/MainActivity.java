package com.bobo.bobodmeo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.libnet.net.ApiCallBack;
import com.example.libnet.net.BaseBean;
import com.example.libnet.net.RNet;

/**
 * Created by Zjb
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
                .getApiService(BusinessApiService.class)
                .getUserInfo("量子力学", 0, 2)
                .enqueue(new ApiCallBack<UserBean>() {
                    @Override
                    public void onMyResponse(BaseBean<UserBean> baseBean) {
                        Toast.makeText(MainActivity.this, baseBean.getInfo(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onMyFailure(Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

}
