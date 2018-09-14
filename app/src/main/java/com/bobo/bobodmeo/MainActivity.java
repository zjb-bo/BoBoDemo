package com.bobo.bobodmeo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bobo.bobodmeo.net.RNet;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Callback;
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
                .getUserInfo("量子力学", 0, 20)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Response<ResponseBody> response) {
                        try {
                            String s = new String(response.body().bytes());
                            Logger.json(s);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RNet.getInstance().release();
    }
}
