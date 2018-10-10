package com.example.libnet.net;

/**
 * Created by smart on 18-10-8.
 * 类描述: 统一处理网络结果的接口
 * 需要具体实现
 */

public interface HandleNetResult {

    void handleNetLogicResult(BaseBean baseBean);//统一处理业务逻辑

    void handleNetError(Throwable e);//统一处理网络错误

    void handleServerError(int code);//统一服务器错误处理
}
