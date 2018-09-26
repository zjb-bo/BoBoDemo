package com.example.libnet.net;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Zjb
 * date: 2017/10/10
 * 类描述：所有的回调信息，方便返回结果 统一处理
 * 仅仅只是使用retrofit 不使用 Rxjava 的时候用到
 */

public abstract class ApiCallBack<T> implements Callback<ResponseBody>{

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        RNet.getInstance().hideLoadingDialog();
        Logger.d("headers:"+response.headers().toString()+"\nresponse:"+response.code()+ response.message());
        ResponseBody result = response.body();
        BaseBean<T> tBaseBean = new BaseBean<>();
        try {
            tBaseBean  = new Gson().fromJson(result.string(), tBaseBean.getClass());
            if(response.code() == 200){
                handleSubjectResult(tBaseBean);
            }else{
                handleServerError(response, tBaseBean);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Logger.e("ApiCallBack json error");
        }
        Logger.d("BaseBean:" + tBaseBean);
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        onMyFailure(t);
        RNet.getInstance().hideLoadingDialog(); //close loading ui
        handleNetError(t); //handle net Error Msg
    }


    private void handleNetError(Throwable e) {
        if (e instanceof UnknownHostException) {
            Logger.e("请打开网络");
        } else if (e instanceof SocketTimeoutException) {
            Logger.e( "请求超时");
        } else if (e instanceof ConnectException) {
            Logger.e("连接失败");
        }else {
            Logger.e("请求失败");
        }
        e.printStackTrace();
    }

    public abstract void onMyResponse(BaseBean<T> baseBean);
    public abstract void onMyFailure(Throwable t);


    /**
     * 统一处理业务逻辑的返回结果
     * @param body 返回体
     */
    private void handleSubjectResult(BaseBean<T> body) {
        if(body.getTypeStutue() == 0){//返回正常
            onMyResponse(body);
        }
        else if(body.getTypeStutue() == -1){//用户登录过期

        }else if(body.getTypeStutue() == -2){//用户余额不足...

        }else if(body.getTypeStutue() == -111){//默认值，没有该字段，正常返回
            onMyResponse(body);
        }
    }

    /**
     * 处理服务器得Error
     * @param response 返回体
     * @param body 返回体
     */
    private void handleServerError(Response<ResponseBody> response, BaseBean<T> body) {
        String code = String.valueOf(response.code());
        String errorInfo;
        if(code.startsWith("4")){
            errorInfo = "资源不存在:" + code;
        }else if(code.startsWith("5")){
            errorInfo = "服务器内部错误:" + code;
        }else {
            errorInfo = "其他错误:" + code;
        }
        String errorInfo2 = errorInfo + "\n 错误信息：" + response.message();
        onMyFailure(new Throwable(errorInfo2));
        Logger.e(body+"");
    }

}
