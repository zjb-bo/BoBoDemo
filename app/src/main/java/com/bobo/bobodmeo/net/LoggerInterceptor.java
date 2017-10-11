package com.bobo.bobodmeo.net;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by ZJB on 2017/10/11.
 *
 * @ copyright: iwhere chengdu technology
 */

public class LoggerInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        StringBuffer stringBuffer = new StringBuffer();
        Request request = chain.request();//请求体
        stringBuffer.append("请求url：")
                    .append(request.url())
                    .append("\n")
                    .append("请求method:")
                    .append(request.method())
                    .append("\n");

        Response response = chain.proceed(request);//响应体
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        stringBuffer
                .append("\n")
                .append("Code：")
                .append(response.code())
                .append("\n")
                .append("Msg:")
                .append(response.message())
                .append("返回结果：")
                .append(new String(responseBody.bytes()));

        Logger.e(stringBuffer.toString());

        return response;
    }
}
