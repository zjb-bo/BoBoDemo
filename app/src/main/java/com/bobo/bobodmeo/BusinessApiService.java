package com.bobo.bobodmeo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by smart on 18-9-26.
 * 具体的业务Url 请求方法
 * 请设置 baseUrl {@link com.example.libnet.net.NetConstantsConfig#NET_BASE_URL_ONLINE}
 * retrofit 网络框架
 */

public interface BusinessApiService {

    @GET("/v2/book/search")
    Call<ResponseBody> getUserInfo(@Query("q") String searchCondition, @Query("start") int start, @Query("count") int count);

}
