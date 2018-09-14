package com.bobo.bobodmeo.net;

import com.bobo.bobodmeo.bean.BaseBean;
import com.orhanobut.logger.Logger;

import java.net.SocketException;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Zjb
 * Company:iwhere chengdu technology
 * date: 2017/10/10
 * 类描述：所有的回调信息，方便返回结果 统一处理
 */

public abstract class ApiCallBack<T extends BaseBean> implements Callback<T>{
    @Override
    public void onResponse(Response<T> response) {
        RNet.getInstance().hideLoadingDialog();
        Logger.e("headers:"+response.headers().toString()+"\nresponse:"+response.code()+ response.message());
        T body = response.body();
        if(response.code() == 200){
            handleSubjectReslut(body, response);
        }else{
            handleServerError(response, body);
        }
    }


    @Override
    public void onFailure(Throwable t) {
        handleNetError(t);
    }

    private void handleNetError(Throwable t) {
//        if(t instanceof SocketException){
//            Logger.e("网络错误："+t.getMessage());
//        }

        //close ui
        RNet.getInstance().hideLoadingDialog();
        //net Error Msg
        onMyFailure(t);
        Logger.e("网络错误："+t.getMessage());
    }

    public abstract void onMyResponse(Response<T> response);
    public abstract void onMyFailure(Throwable t);

    /**
     * 处理业务逻辑的返回结果
     * @param body 返回体
     * @param response　
     */
    private void handleSubjectReslut(T body, Response<T> response) {
        if(body.getTypeStutue() == 0){//返回正常
            onMyResponse(response);
        }else if(body.getTypeStutue() == -1){//用户登录过期

        }else if(body.getTypeStutue() == -2){//用户余额不足...

        }else if(body.getTypeStutue() == -111){//默认值，没有该字段，正常返回
            onMyResponse(response);
        }
    }

    /**
     * 处理服务器得Error
     * @param response 返回体
     * @param body 返回体
     */
    private void handleServerError(Response<T> response, T body) {
        String errorInfo = "请求内容出错：" + response.code() + " 错误信息：" + response.message();
        onMyFailure(new Throwable(errorInfo));
        Logger.e(body+"");
    }

}
