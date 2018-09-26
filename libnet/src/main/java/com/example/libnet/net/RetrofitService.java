package com.example.libnet.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by smart on 18-9-26.
 * 具体的业务Url 请求方法
 * 请设置 baseUrl {@link com.example.libnet.net.NetConstantsConfig#NET_BASE_URL_ONLINE}
 * retrofit 网络框架
 */

public interface RetrofitService {

    //TODO: do net work
    //Test
    @GET("/v2/book/search")
    Call<ResponseBody> getUserInfo(@Query("q") String searchCondition, @Query("start") int start, @Query("count") int count);

    @GET("/v2/book/search")
    Observable<ResponseBody> getRxUserInfo(@Query("q") String searchCondition, @Query("start") int start, @Query("count") int count);

}
