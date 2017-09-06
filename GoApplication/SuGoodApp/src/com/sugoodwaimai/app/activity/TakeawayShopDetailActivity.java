package com.sugoodwaimai.app.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.adapter.TakeawayShopPagerAdapter;
import com.sugoodwaimai.app.application.SugoodApplication;
import com.sugoodwaimai.app.entity.ShopOrder;
import com.sugoodwaimai.app.entity.TakeawayShopInfo;
import com.sugoodwaimai.app.fragment.TakeawayCommentFragment;
import com.sugoodwaimai.app.fragment.TakeawayCommentFragment2;
import com.sugoodwaimai.app.fragment.TakeawayDescFragment;
import com.sugoodwaimai.app.fragment.TakeawayShopcarFragment;
import com.sugoodwaimai.app.global.Constant;
import com.sugoodwaimai.app.listener.ViewdisMissListener;
import com.sugoodwaimai.app.util.GlideUtil;
import com.sugoodwaimai.app.util.HttpUtil;
import com.sugoodwaimai.app.util.JsonUtil;
import com.sugoodwaimai.app.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Package :com.android.supermarket.takeaway
 * Description :
 * Author :Rc3
 * Created at :2017/2/28 14:37.
 */

public class TakeawayShopDetailActivity extends AppCompatActivity {

    private static final String TAG = TakeawayShopDetailActivity.class.getSimpleName();

    Context mContext;
    List<ShopOrder> mList;
    @BindView(R.id.takeaway_shop_tabs)
    TabLayout mTab;
    @BindView(R.id.takeaway_shopcar_view1)
    View mView;
    @BindView(R.id.takeaway_shop_description_vp)
    ViewPager mViewpager;
    //高斯模糊的view
    @BindView(R.id.takeaway_shop_description_rl)
    RelativeLayout mBlurLayout;
    @BindView(R.id.takeaway_shop_detail_text_tv)
    TextView mShopNameTv;
//    @BindView(R.id.takeaway_shop_description_tv)
//    TextView mShopName;
    @BindView(R.id.takeaway_shop_detail_img)
    CircleImageView mShopImage;
    @BindView(R.id.takeaway_shop_delivary_price_tv)
    TextView mDeliveryPrice;
    @BindView(R.id.takeaway_shop_delivary_price_line)
    LinearLayout mDescline;
    @BindView(R.id.takeaway_shop_activity)
    TextView mActivity;
    @BindView(R.id.takeaway_shop_work_time)
    TextView mWorktime;
    @BindView(R.id.takeaway_shop_detail_header_back)
    ImageView mBack;
    @BindView(R.id.takeaway_shop_detail_farvorite_img)
    ImageView mFarvorite;
//    @BindView(R.id.takeaway_shop_detail)
//    ImageView mDetail;
    List<TakeawayShopInfo> shopInfo;
    boolean   isfarvorite =false;
    int favoritesId =0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waimai_shop_detail_activity);
        ButterKnife.bind(this);
        mContext = this;
        initView();

        requestData();

        SugoodApplication.getInstance().getShopCarProductList().clear();

    }

    @OnClick(R.id.takeaway_shop_work_time)
    void goDesc2() {
        goDesActivity();
    }
    @OnClick(R.id.takeaway_shop_activity)
    void goDesc() {
        goDesActivity();
    }

    @OnClick(R.id.takeaway_shop_detail_farvorite_img)
    void collection() {
        goDesActivity();
    }
//    @OnClick(R.id.takeaway_shop_detail)
//    void detail() {
//
//    }

    private void initData() {
        //renderscript高斯模糊
//<<<<<<< .mine
////        Bitmap bitmap = BlurBitmapUtil.getBlurBitmap(mContext, BitmapFactory.decodeResource(getResources(), R.drawable.shop_icon), 20f);
//||||||| .r24
//        Bitmap bitmap = BlurBitmapUtil.getBlurBitmap(mContext, BitmapFactory.decodeResource(getResources(), R.drawable.shop_icon), 20f);
//=======
//      //  Bitmap bitmap = BlurBitmapUtil.getBlurBitmap(mContext, BitmapFactory.decodeResource(getResources(), R.color.red), 20f);
//>>>>>>> .r25
//
//<<<<<<< .mine
////        mBlurLayout.setBackground(new BitmapDrawable(bitmap));
//||||||| .r24
//        mBlurLayout.setBackground(new BitmapDrawable(bitmap));
//=======
//        //mBlurLayout.setBackground(new BitmapDrawable(bitmap));
//>>>>>>> .r25
//        mBlurLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(mContext, TakeawayAboutShopActivity.class));
//            }
//        });


        //viewpager
        List<Fragment> mFragmentList = new ArrayList<>();
        TakeawayShopcarFragment fragment1 = new TakeawayShopcarFragment();

        TakeawayCommentFragment2 fragment2 = new TakeawayCommentFragment2();
      // TakeawayDescFragment fragment3 = new TakeawayDescFragment();


        fragment1.setListner(new ViewdisMissListener() {


            @Override
            public void setViewVisble() {
                mView.setVisibility(View.VISIBLE);
            }

            @Override
            public void setViewGone() {
                mView.setVisibility(View.GONE);
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString("shopId", getIntent().getStringExtra("shopId"));
        bundle.putSerializable("shop", shopInfo.get(0).getEle().get(0));
        mDescline.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           goDesActivity();
                                       }
                                   }

        );
        System.out.println("TakeawayShopDetailActivityShop1111:"+shopInfo.get(0).getEle().get(0).toString());

        fragment1.setArguments(bundle);
        fragment2.setArguments(bundle);
        //fragment3.setArguments(bundle);
        mFragmentList.add(fragment1);
        mFragmentList.add(fragment2);
        //mFragmentList.add(fragment3);


        TakeawayShopPagerAdapter pagerAdapter = new TakeawayShopPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewpager.setAdapter(pagerAdapter);
        mTab.setupWithViewPager(mViewpager);


    }

    private void goDesActivity() {
        Intent intent = new Intent();

        intent.putExtra("shop", shopInfo.get(0).getEle().get(0));

        intent.setClass(TakeawayShopDetailActivity.this, TakeawayDescActivity.class);
        startActivity(intent);
    }

    private void initView() {
        mViewpager = (ViewPager) findViewById(R.id.takeaway_shop_description_vp);
        mView = findViewById(R.id.takeaway_shopcar_view1);
        mTab = (TabLayout) findViewById(R.id.takeaway_shop_tabs);
        mBlurLayout = (RelativeLayout) findViewById(R.id.takeaway_shop_description_rl);
        isFarvorite();
        mFarvorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SugoodApplication.isLogin) {
                    if (isfarvorite){
                        delect();
                    }else
                    farvorite();
                } else {
                    startActivity(new Intent(TakeawayShopDetailActivity.this, LoginActivity.class));
                }

            }
        });
    }

    /**
     * 是否收藏
     */
    private void isFarvorite() {
        RequestParams params = new RequestParams();
        params.put("userId", SugoodApplication.user.getUserId());
        params.put("shopId", getIntent().getStringExtra("shopId"));


        HttpUtil.post(Constant.IS_SHOP_COLLECtION, params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, final JSONObject response) {
                        Log.e(TAG, "farvoriteresponse: " + response.toString());


                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {

                                try {
                                    boolean session = response.getBoolean("session");
                                    System.out.println("farvoriteresponse0:"+response.getJSONArray("List").getJSONObject(0).getInt("favoritesId"));
                                    //    System.out.println("farvoriteresponse1:"+response.getJSONArray("List").getString(1));
                                    favoritesId =response.getJSONArray("List").getJSONObject(0).getInt("favoritesId");


                                    if (session){
                                        Resources resources = getBaseContext().getResources();
                                        Drawable imageDrawable = resources.getDrawable(R.drawable.farvorite_true); //图片在drawable目录下
                                        mFarvorite.setBackgroundDrawable(imageDrawable);
                                        isfarvorite= true;
                                    }else
                                    {
                                        Resources resources = getBaseContext().getResources();
                                        Drawable imageDrawable = resources.getDrawable(R.drawable.farvorite_false); //图片在drawable目录下
                                        mFarvorite.setBackgroundDrawable(imageDrawable);
                                        isfarvorite= false;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.i(TAG, "onFailure: " + new String(responseString));


                    }

                }
        );
    }

    /**
     * 店铺收藏
     */
    private void farvorite() {
        RequestParams params = new RequestParams();
        params.put("userId", SugoodApplication.user.getUserId());
        params.put("shopId", getIntent().getStringExtra("shopId"));
        params.put("type", "1");
        Log.e(TAG, "farvorite: " + getIntent().getStringExtra("shopId"));
        HttpUtil.post(Constant.SHOP_COLLECtION_ADD, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        ToastUtil.setToast(TakeawayShopDetailActivity.this, "收藏成功");
                        isFarvorite();
//                        new Handler().post(new Runnable() {
//                            @Override
//                            public void run() {
//                                ToastUtil.setToast(TakeawayShopDetailActivity.this, "收藏成功");
//                                Resources resources = getBaseContext().getResources();
//                                Drawable imageDrawable = resources.getDrawable(R.drawable.farvorite_true); //图片在drawable目录下
//                                mFarvorite.setBackgroundDrawable(imageDrawable);
//                                isfarvorite= true;
//                            }
//                        });
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, final byte[] responseBody, Throwable error) {
                        Log.i(TAG, "onFailure: " + new String(responseBody));

                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.setToast(TakeawayShopDetailActivity.this, "已收藏该商店");
                            }
                        });
                    }

                }
        );
    }

    private void delect() {

        //showLoading("");
        RequestParams params = new RequestParams();
        params.put("Id", favoritesId);
        Log.e("TUi", "favoritesId: " + favoritesId);
        HttpUtil.post(Constant.DELETESHOP, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e("TUi", "onSuccess: " + response.toString());

                try {
                    if (!response.getBoolean("success")) {
                        ToastUtil.setToast(TakeawayShopDetailActivity.this, response.getString("message"));
                    } else {
                        Resources resources = getBaseContext().getResources();
                        Drawable imageDrawable = resources.getDrawable(R.drawable.farvorite_false); //图片在drawable目录下
                        mFarvorite.setBackgroundDrawable(imageDrawable);
                        isfarvorite= false;
                        ToastUtil.setToast(TakeawayShopDetailActivity.this, "删除商店收藏成功");
                        isfarvorite= false;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e("TUi", "onSuccess: " + responseString);

                ToastUtil.setToast(TakeawayShopDetailActivity.this, "删除商店失败");
            }
        });
    }

    private void requestData() {


        RequestParams params = new RequestParams();

        params.put("shopId", getIntent().getStringExtra("shopId"));
        HttpUtil.post(Constant.TAKEAWAY_MENU_LIST_URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String result = new String(responseBody);
                    System.out.println("resultaaa:"+result);
                    result = "["+result+"]";
                    shopInfo = JsonUtil.toList(result, TakeawayShopInfo.class);
                    initData();
                    if (shopInfo.get(0).getEle().size() > 0) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                setText(shopInfo.get(0).getEle().get(0));
                            }
                        });

                    }

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, final Throwable error) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @OnClick(R.id.takeaway_shop_detail_header_back)
    void onBack() {
        finish();
    }


    public void setText(TakeawayShopInfo.EleBean text) {

        mShopNameTv.setText(text.getShopName());
       // mShopName.setText(text.getShopName());
        GlideUtil.displayImage(Constant.PHOTOBASEURL + text.getPhoto(), mShopImage);
        mWorktime.setText(text.getIntro());
        if(text.getIntro().equals("")){
            mWorktime.setVisibility(View.GONE);
        }
        String a="";
        String b="";
        String c="";
        if (text.getFullMoney() == 0 || text.getNewMoney() == 0){

        }else{
            mActivity.setVisibility(View.VISIBLE);
           a = "满"+Double.parseDouble(text.getFullMoney()+"")/100+"元减"+ Double.parseDouble(text.getNewMoney()+" ")/100+"元";
        }
        if (text.getIsNew() != 0){
            mActivity.setVisibility(View.VISIBLE);
          b=  "新客户下单立减："+ Double.parseDouble(text.getJianDuos()+" ")/100+"元";
        }
        if (text.getIsFan() == 1&&text.getFanMoney()!=0)
        {
            mActivity.setVisibility(View.VISIBLE);
            c="最高支持返现" +  Double.parseDouble(text.getFanMoney()+" ")/100+"元";
        }
        mActivity.setText(a+b+c);
        mDeliveryPrice.setText("起送:￥" + (double)text.getSinceMoney() + "元"+" | 配送费：￥"+text.getLogistics());
    }
}
