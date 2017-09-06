package com.sugoodwaimai.app.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.entity.TakeawayShopInfo;
import com.sugoodwaimai.app.global.Constant;
import com.sugoodwaimai.app.util.GlideUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/8/26 0026.
 */

public class TakeawayDescActivity extends AppCompatActivity {
    @BindView(R.id.header_back)
    ImageView headerBack;
    @BindView(R.id.desc_img)
    CircleImageView descImg;
    @BindView(R.id.takeaway_name)
    TextView takeawayName;
    @BindView(R.id.takeaway_rating)
    RatingBar takeawayRating;
    @BindView(R.id.takeaway_price)
    TextView takeawayPrice;
    @BindView(R.id.takeaway_time)
    TextView takeawayTime;
    @BindView(R.id.divider1)
    View divider1;
    @BindView(R.id.takeaway_desc)
    TextView takeawayDesc;
    @BindView(R.id.divider2)
    View divider2;
    @BindView(R.id.takeaway_favorable1)
    TextView takeawayFavorable1;
    @BindView(R.id.takeaway_favorable2)
    TextView takeawayFavorable2;
    @BindView(R.id.takeaway_favorable3)
    TextView takeawayFavorable3;
    @BindView(R.id.takeaway_phone)
    TextView takeawayPhone;
    @BindView(R.id.divider3)
    View divider3;
    @BindView(R.id.takeaway_address)
    TextView takeawayAddress;
    @BindView(R.id.takeaway_time2)
    TextView takeawayTime2;
    TakeawayShopInfo.EleBean shop;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.takeaway_shop_desc_activity);
        ButterKnife.bind(this);
        shop = (TakeawayShopInfo.EleBean) getIntent().getSerializableExtra("shop");
        System.out.println("shop:"+shop.toString());
        //ButterKnife.bind(this);
        // mContext = this;
        initView();

    }

    private void initView() {
        takeawayName.setText(shop.getShopName());
        takeawayRating.setRating(shop.getScore());

        takeawayPrice.setText("起送:￥" + (double)shop.getSinceMoney() + "元"+" 配送费：￥"+shop.getLogistics());
        if (!shop.getBusihour().equals("")) {
            takeawayTime.setText("配送时间：" + shop.getBusihour());
            takeawayTime2.setText(shop.getBusihour());
        }
        takeawayDesc.setText(shop.getIntro());
        GlideUtil.displayImage(Constant.PHOTOBASEURL + shop.getPhoto(), descImg);

        if (shop.getIsNew()==1){
            takeawayFavorable3.setVisibility(View.VISIBLE);
            takeawayFavorable3.setText("新客户立即减少："+shop.getJianDuos()/100+"元");
        }
        if (shop.getIsFan()==1){
            takeawayFavorable1.setVisibility(View.VISIBLE);
            takeawayFavorable1.setText("最高返现金："+shop.getFanMoney()/100+"元");
        }
        System.out.println("shop.getFullMoney()aaa:"+shop.getFullMoney());
        if (shop.getFullMoney()>0&&shop.getNewMoney()>0){
            takeawayFavorable2.setVisibility(View.VISIBLE);
            takeawayFavorable2.setText("满"+(double)shop.getFullMoney()/100+"元减"+(double)shop.getNewMoney()/100+"元");
        }
        takeawayPhone.setText(shop.getTel());
        takeawayAddress.setText(shop.getAddr());

    }
    @OnClick(R.id.header_back)
    void onBack() {
        finish();
    }

    @OnClick(R.id.takeaway_phone)
    void call() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + shop.getTel());
        intent.setData(data);
        startActivity(intent);
    }
}
