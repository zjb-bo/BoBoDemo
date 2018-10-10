package com.example.libnet.net;

/**
 * Created by smart on 18-9-26.
 * 网络相关的常量配置
 */

public interface NetConstantsConfig {
    //retrofit configs
    int NET_CONNECT_TIME_SECONDS = 30;
    int NET_READ_TIME_SECONDS = 30;

    //rxjava configs
    int NET_TIME_OUT = 15; //重新链接的时间间隔
    int NET_RETRY_TINE = 3; //重新链接的次数


    //TODO: config base url
    String NET_BASE_URL_ONLINE = "http://api.douban.com";



}
