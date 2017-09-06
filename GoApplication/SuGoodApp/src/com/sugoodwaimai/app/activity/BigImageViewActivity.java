package com.sugoodwaimai.app.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.global.Constant;
import com.sugoodwaimai.app.util.GlideUtil;

/**
 * Created by wilk on 17/4/23 23:49
 * ganweib@gmail.com
 * describe:
 */

public class BigImageViewActivity extends BaseActivity {

    ImageView bigImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bigimg);
        bigImg = (ImageView) findViewById(R.id.bigImg);
        String url = getIntent().getStringExtra("url");
        GlideUtil.displayImage(Constant.PHOTOBASEURL + url, bigImg);
        bigImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
