package com.bobo.bobodmeo.net;

import com.bobo.bobodmeo.bean.BaseBean;
import com.bobo.bobodmeo.bean.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Zjb
 * Company:iwhere chengdu technology
 * date: 2017/10/10
 * 类描述：
 */

public interface ApiService {

    @GET("/getBackgroundMusic")
    Call<BaseBean<User>> getUserInfo(@Query("userId") String userId);

}
