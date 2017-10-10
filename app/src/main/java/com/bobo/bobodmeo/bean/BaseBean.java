package com.bobo.bobodmeo.bean;

/**
 * Created by Zjb
 * Company:iwhere chengdu technology
 * date: 2017/10/10
 * 类描述：所有的Bean的 共同的部分  与服务器规定，可以修改 但是该类需要存在
 * {server_status:200,info:"ok"}
 */

public class BaseBean<T> {

    private int server_status;
    private String info;
    private int typeStutue = -111;//必须的默认值
    private T data;


    public int getTypeStutue() {
        return typeStutue;
    }

    public void setTypeStutue(int typeStutue) {
        this.typeStutue = typeStutue;
    }

    public int getServer_status() {
        return server_status;
    }

    public void setServer_status(int server_status) {
        this.server_status = server_status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
