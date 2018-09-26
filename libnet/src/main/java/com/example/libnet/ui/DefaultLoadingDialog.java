package com.example.libnet.ui;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.libnet.R;


/**
 * Created by Zjb
 * date: 2017/10/10
 * 类描述：默认的 网络加载 dialog
 * if you want custom please see {@link BaseLoadingDialog}
 */

public class DefaultLoadingDialog extends BaseLoadingDialog {

    public DefaultLoadingDialog(Context context) {
        super(context, R.style.LoadingDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_dialog);
        initAnim();
    }

    private void initAnim() {
        ImageView imageView = findViewById(R.id.img_anim);
        imageView.setImageResource(R.drawable.loading_anim);
        AnimationDrawable drawable = (AnimationDrawable) imageView.getDrawable();
        drawable.start();
    }
}
