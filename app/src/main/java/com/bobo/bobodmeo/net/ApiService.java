package com.bobo.bobodmeo.net;

import okhttp3.ResponseBody;
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

    @GET("/v2/book/search")
    Call<ResponseBody> getUserInfo(@Query("q") String searchCondition, @Query("start") int start, @Query("count") int count);

}
