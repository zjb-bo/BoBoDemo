package com.example.libnet.net;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by ZJB on 2017/10/11.
 * 类描述: 日志拦截器
 */

public class LoggerInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        Request request = chain.request();//请求体
        stringBuilder.append("请求url：")
                    .append(request.url())
                    .append("\n")
                    .append("请求method:")
                    .append(request.method())
                    .append("\n");

        Response response = chain.proceed(request);//响应体
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        stringBuilder
                .append("\n")
                .append("Code：")
                .append(response.code())
                .append("\n")
                .append("Msg:")
                .append(response.message())
                .append("返回结果：")
                .append(new String(responseBody.bytes()));

        Logger.d(stringBuilder.toString());

        return response;
    }
}
