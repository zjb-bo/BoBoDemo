package com.example.libnet.example;

import com.example.libnet.net.BaseBean;

/**
 * Created by Zjb
 * Company:iwhere chengdu technology
 * date: 2017/10/10
 * 类描述：用户JsonBean
 */

public class UserBean extends BaseBean {


    /**
     * id : 59ccd68d9658d94aef83a38a
     * musicCode : 01
     * musicUrl : http://qiniu.iwhere.com/track/backgroundMusic/backgroundmusic1.mp3
     */

    private String id;
    private String musicCode;
    private String musicUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMusicCode() {
        return musicCode;
    }

    public void setMusicCode(String musicCode) {
        this.musicCode = musicCode;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }
}
