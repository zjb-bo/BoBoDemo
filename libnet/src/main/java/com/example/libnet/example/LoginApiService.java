package com.example.libnet.example;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by smart on 18-9-26.
 */

public interface LoginApiService {

    @POST("/v2/book/login")
    Call<ResponseBody> login(@Query("userName") String userName, @Query("userPwd") String userPwd, @Query("checkCode") int checkCode);

}
