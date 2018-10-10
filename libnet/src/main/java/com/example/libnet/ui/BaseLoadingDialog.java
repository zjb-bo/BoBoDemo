package com.example.libnet.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import com.example.libnet.net.RNet;

/**
 * Created by smart on 18-9-26.
 * Base net loadingDialog UI ,
 * if you want custom ui need extends this class
 * and use method {@link RNet#setLoadingDialog(BaseLoadingDialog)} to set
 */

public class BaseLoadingDialog extends Dialog{
    private Context mContext;

    public BaseLoadingDialog(@NonNull Context context) {
        this(context, 0);
    }

    public BaseLoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    @CallSuper
    protected void showLoadingDialog(){
        if(mContext == null){
            return;
        }
        if(mContext instanceof Activity){
            Activity activity = (Activity) mContext;
            if(!activity.isDestroyed()){
                show();
            }
        }else {
            show();
        }
    }

    @CallSuper
    protected void hideLoadingDialog(){
        if(mContext != null && isShowing()){
            cancel();
            //notice: 如果同一个retry中,则不会多次显示,只显示第一次
            mContext = null;
        }
    }

}
