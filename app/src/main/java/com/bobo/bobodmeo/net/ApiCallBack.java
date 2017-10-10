package com.bobo.bobodmeo.net;

import com.bobo.bobodmeo.bean.BaseBean;
import com.orhanobut.logger.Logger;

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
//        Logger.e("headers:"+response.headers().toString()+"\nresponse:"+response.code()+ response.message());
        T body = response.body();
        if(response.code() == 200){
            if(body.getTypeStutue() == 0){//返回正常
                onMyResponse(response);
            }else if(body.getTypeStutue() == -1){//用户登录过期

            }else if(body.getTypeStutue() == -2){//用户余额不足...

            }else if(body.getTypeStutue() == -111){//默认值，没有该字段，正常返回
                onMyResponse(response);
            }
        }else{
            String errorInfo = "请求内容出错：" + response.code() + " 错误信息：" + response.message();
            onMyFailure(new Throwable(errorInfo));
//            Logger.e(body+"");
        }
    }

    @Override
    public void onFailure(Throwable t) {
        RNet.getInstance().hideLoadingDialog();
        onMyFailure(t);
        Logger.e(t.getMessage());

    }


    public abstract void onMyResponse(Response<T> response);
    public abstract void onMyFailure(Throwable t);

}
