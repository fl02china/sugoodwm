package com.sugoodwaimai.app.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.adapter.TuanGoutcAdapter;
import com.sugoodwaimai.app.application.SugoodApplication;
import com.sugoodwaimai.app.entity.DianPing;
import com.sugoodwaimai.app.entity.ShopCarProduct;
import com.sugoodwaimai.app.entity.TakeawayShopInfo;
import com.sugoodwaimai.app.entity.TuanGouDetailNew;
import com.sugoodwaimai.app.global.Constant;
import com.sugoodwaimai.app.util.FullyLinearLayoutManager;
import com.sugoodwaimai.app.util.HttpUtil;
import com.sugoodwaimai.app.util.JsonUtil;
import com.sugoodwaimai.app.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2017/9/13 0013.
 */

public class TuanGouNewDetailActivity extends BaseActivity {
    private static final String TAG = TuanGouNewDetailActivity.class.getSimpleName();
    @BindView(R.id.back)
    TextView back;

    @BindView(R.id.tuangou_ad)
    SimpleDraweeView tuangouAd;
    @BindView(R.id.goodname)
    TextView goodname;
    @BindView(R.id.tuangou_average)
    TextView tuangouAverage;
    @BindView(R.id.line1)
    LinearLayout line1;
    @BindView(R.id.shop_name)
    TextView shopName;
    @BindView(R.id.tuangou_addr)
    TextView tuangouAddr;
    @BindView(R.id.sj_tell)
    TextView sjTell;
    //    @BindView(R.id.tv_tuangouMsg)
//    WebView tvTuangouMsg;
//    @BindView(R.id.tv_evaluate)
//    TextView tvEvaluate;
    @BindView(R.id.rl_look_evaluate)
    RelativeLayout rlLookEvaluate;
    @BindView(R.id.sdv_avatar)
    SimpleDraweeView sdvAvatar;
    @BindView(R.id.tv_userName1)
    TextView tvUserName1;
    @BindView(R.id.ratbarShop1)
    RatingBar ratbarShop1;
    @BindView(R.id.ll_stars)
    LinearLayout llStars;
    @BindView(R.id.createTime1)
    TextView createTime1;
    @BindView(R.id.rl_dianping1)
    RelativeLayout rlDianping1;
    @BindView(R.id.tv_evaluate_content)
    TextView tvEvaluateContent;
    @BindView(R.id.sdv_avatar_2)
    SimpleDraweeView sdvAvatar2;
    @BindView(R.id.tv_userName2)
    TextView tvUserName2;
    @BindView(R.id.ratbarShop2)
    RatingBar ratbarShop2;
    @BindView(R.id.ll_stars_2)
    LinearLayout llStars2;
    @BindView(R.id.createTime2)
    TextView createTime2;
    @BindView(R.id.rl_dianping2)
    RelativeLayout rlDianping2;
    @BindView(R.id.tv_evaluate_content2)
    TextView tvEvaluateContent2;
    @BindView(R.id.yh_commit)
    TextView yhCommit;
    @BindView(R.id.tv_evaluate2)
    TextView tvEvaluate2;
    @BindView(R.id.li_look)
    LinearLayout liLook;
    @BindView(R.id.scroller)
    ScrollView scroller;
    @BindView(R.id.tuangou_account)
    Button tuangouAccount;
    @BindView(R.id.head)
    RelativeLayout head;
    @BindView(R.id.tuangou_price)
    TextView tuangouPrice;
    @BindView(R.id.rl_nodp)
    RelativeLayout rlNodp;
    @BindView(R.id.tuangou_price2)
    TextView tuangouPrice2;
    @BindView(R.id.tuangou_average2)
    TextView tuangouAverage2;
    @BindView(R.id.RecyclerView)
    XRecyclerView RecyclerView;
    @BindView(R.id.tx_validTime)
    TextView txValidTime;
    @BindView(R.id.validTime)
    TextView validTime;
    @BindView(R.id.tx_timePeriod)
    TextView txTimePeriod;
    @BindView(R.id.timePeriod)
    TextView timePeriod;
    @BindView(R.id.tx_warmPrompt)
    TextView txWarmPrompt;
    @BindView(R.id.warmPrompt)
    TextView warmPrompt;
    @BindView(R.id.tv_evaluate)
    TextView tvEvaluate;
    @BindView(R.id.line_taocan)
    LinearLayout lineTaocan;
    @BindView(R.id.line_mustknow)
    LinearLayout lineMustknow;
    private String mShopId;
    private String mTuanId;
    private TuanGouDetailNew mTuanGoudetail;
    TakeawayShopInfo.EleBean shop = new TakeawayShopInfo.EleBean();
    ImageView mFarvorite;
    private String goodsId;
    int favoritesId =0;
    boolean   isfarvorite =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuangou_detail);
        ButterKnife.bind(this);
        RecyclerView.setLayoutManager(new FullyLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        RecyclerView.setHasFixedSize(true);
        getIntentData();
         initView();
        initNetData();

    }




    @OnClick(R.id.back)
    void onback() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void getIntentData() {
        mShopId = getIntent().getStringExtra("shopId");
        mTuanId = getIntent().getStringExtra("tuanId");

    }

    private void add() {
        SugoodApplication.getInstance().getShopCarProductList().clear();
        List<ShopCarProduct> shopCarList = SugoodApplication.getInstance().getShopCarProductList();
        shopCarList.clear();
        ShopCarProduct shopCar = new ShopCarProduct();
        shopCar.setFoodAmount(1);
        shopCar.setFoodName(mTuanGoudetail.getTuan().getTitle());
        shopCar.setProductId(mTuanGoudetail.getTuan().getTuanId());
        shopCar.setFoodPrice(mTuanGoudetail.getTuan().getTuanPrice());
        shopCarList.add(shopCar);
        SugoodApplication.getInstance().setShopCarProductList(shopCarList);

    }


    private void initView(){
        mFarvorite= (ImageView) findViewById(R.id.collect);
        if (SugoodApplication.isLogin){
            isFarvorite();}
        mFarvorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SugoodApplication.isLogin) {
                    if (isfarvorite){
                        delect();
                    }else
                        farvorite();
                } else {
                    startActivity(new Intent(TuanGouNewDetailActivity.this, LoginActivity.class));
                }

            }
        });
    }

    /**
     * 网络请求数据
     */
    private void initNetData() {
        showLoading("加载中");
        RequestParams params = new RequestParams();
        params.put("shopId", mShopId);
        params.put("tuanId", mTuanId);
        System.out.println("111mShopId:" + mShopId);
        System.out.println("111mTuanId:" + mTuanId);
        HttpUtil.post(Constant.SUGOODTUANGOU, params, new JsonHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                closeLoading();
                Log.e("ss", "onFailure: " + responseString.toString());
                Log.e("ss", "onFailure: " + throwable.toString());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                closeLoading();
                Log.e("ss", "onSuccess: " + response.toString());
                mTuanGoudetail = JsonUtil.toObject(response.toString(), TuanGouDetailNew.class);
                List<TuanGouDetailNew.TuanBean.DetailsBean> tuanList = mTuanGoudetail.getTuan().getDetails();
                if (tuanList.size() > 0) {
                    lineTaocan.setVisibility(View.VISIBLE);
                    TuanGoutcAdapter adapter = new TuanGoutcAdapter(TuanGouNewDetailActivity.this, tuanList);
                    //   System.out.println("111aatuanList:" + tuanList.get(0).toString());
                    RecyclerView.setAdapter(adapter);
                }
                updateView();
            }
        });


    }

    private void updateView() {


//        @BindView(R.id.tx_validTime)
//        TextView txValidTime;
//        @BindView(R.id.validTime)
//        TextView validTime;
//        @BindView(R.id.tx_timePeriod)
//        TextView txTimePeriod;
//        @BindView(R.id.timePeriod)
//        TextView timePeriod;
//        @BindView(R.id.tx_warmPrompt)
//        TextView txWarmPrompt;
//        @BindView(R.id.warmPrompt)
//        TextView warmPrompt;

        if (mTuanGoudetail.getTuan().getValidTime() != null) {
            validTime.setText(mTuanGoudetail.getTuan().getValidTime());
            lineMustknow.setVisibility(View.VISIBLE);
        } else {
            txValidTime.setVisibility(View.GONE);
            validTime.setVisibility(View.GONE);
        }

        if (mTuanGoudetail.getTuan().getTimePeriod() != null) {
            lineMustknow.setVisibility(View.VISIBLE);
            timePeriod.setText(mTuanGoudetail.getTuan().getTimePeriod());
        } else {
            txTimePeriod.setVisibility(View.GONE);
            timePeriod.setVisibility(View.GONE);
        }
        if (mTuanGoudetail.getTuan().getWarmPrompt() != null) {
            lineMustknow.setVisibility(View.VISIBLE);
            warmPrompt.setText(mTuanGoudetail.getTuan().getWarmPrompt());
        } else {
            txWarmPrompt.setVisibility(View.GONE);
            warmPrompt.setVisibility(View.GONE);
        }

        tuangouAd.setImageURI(Constant.PHOTOBASEURL + mTuanGoudetail.getTuan().getPhoto());
        goodname.setText(mTuanGoudetail.getTuan().getTitle());
        tuangouAverage.setText("门市价:¥" + mTuanGoudetail.getTuan().getPrice());
        tuangouPrice.setText(mTuanGoudetail.getTuan().getTuanPrice());
        tuangouAverage2.setText("门市价:¥" + mTuanGoudetail.getTuan().getPrice());
        tuangouPrice2.setText(mTuanGoudetail.getTuan().getTuanPrice());
        shopName.setText(mTuanGoudetail.getShop().getShopName());
        liLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("shopId", Integer.parseInt(mShopId));
                intent.setClass(TuanGouNewDetailActivity.this, PingLunActivity.class);
                startActivity(intent);
            }
        });
        System.out.println("aaa11:" + mTuanGoudetail.getShop().getShopName());
        tuangouAddr.setText(mTuanGoudetail.getShop().getAddr());
        tuangouAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SugoodApplication.isLogin) {
                    System.out.println("mTuanGoudetail" + mTuanGoudetail);
                    if (mTuanGoudetail != null) {
                        add();
                        Intent intent = new Intent();
                        shop.setLogistics(0.00);
                        shop.setShopId(Integer.parseInt(mShopId));
                        shop.setTel(mTuanGoudetail.getShop().getTel());
                        shop.setShopName(mTuanGoudetail.getShop().getShopName());
                        intent.putExtra("shop", shop);
                        intent.putExtra("endDate", (mTuanGoudetail.getTuan().getEndDate()));
                        intent.putExtra("goodname", mTuanGoudetail.getTuan().getTitle());
                        intent.putExtra("price", mTuanGoudetail.getTuan().getTuanPrice());
                        intent.putExtra("picture", mTuanGoudetail.getTuan().getPhoto());
                        intent.setClass(TuanGouNewDetailActivity.this, TuanGouSubmitOrderActivity.class);
                        startActivity(intent);

                    }

                } else {
                    startActivity(new Intent(TuanGouNewDetailActivity.this, LoginActivity.class));
                }
            }
        });
        sjTell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + mTuanGoudetail.getShop().getTel());
                intent.setData(data);
                startActivity(intent);
            }
        });
//        tvTuangouMsg.loadDataWithBaseURL(null, mTuanGoudetail.getTuan().getDetails(), "text/html", "utf-8", null);
//        tvTuangouMsg.getSettings().setJavaScriptEnabled(true);
        tvEvaluate.setText("全部用户评价(" + mTuanGoudetail.getShop().getNum() + ")");
        tvEvaluate2.setText("共" + mTuanGoudetail.getShop().getNum() + "条");
        System.out.println("1111mTuanGoudetail.getDianping().size():" + mTuanGoudetail.getDianping().size());
        System.out.println("1111 mTuanGoudetail.getDianping() == null:" + mTuanGoudetail.getDianping() == null);
        if (mTuanGoudetail.getDianping().size() == 0 || mTuanGoudetail.getDianping() == null) {
            rlDianping1.setVisibility(View.GONE);
            rlDianping2.setVisibility(View.GONE);
            tvEvaluateContent.setVisibility(View.GONE);
            tvEvaluateContent2.setVisibility(View.GONE);
            rlNodp.setVisibility(View.VISIBLE);
            liLook.setVisibility(View.GONE);

        } else {
            rlDianping1.setVisibility(View.VISIBLE);
            rlDianping2.setVisibility(View.VISIBLE);
            tvEvaluateContent.setVisibility(View.VISIBLE);
            tvEvaluateContent2.setVisibility(View.VISIBLE);

            rlNodp.setVisibility(View.GONE);
        }
        if (mTuanGoudetail.getDianping().size() != 0 && mTuanGoudetail.getDianping().size() == 1) {
            DianPing dianping = mTuanGoudetail.getDianping().get(0);
            System.out.println("tvUserName1:" + tvUserName1);
            System.out.println("dianping:" + dianping.getNickname());
            sdvAvatar.setImageURI(Constant.PHOTOBASEURL + dianping.getFace());
            tvUserName1.setText(dianping.getNickname());
            tvEvaluateContent.setText(dianping.getContents());
            llStars2.setVisibility(View.GONE);
            //tvScore1.setText(dianping.getScore());
            rlDianping1.setVisibility(View.VISIBLE);
            rlDianping2.setVisibility(View.GONE);
            tvEvaluateContent.setVisibility(View.VISIBLE);
            tvEvaluateContent2.setVisibility(View.GONE);
        } else {

        }

        if (mTuanGoudetail.getDianping().size() != 0 && mTuanGoudetail.getDianping().size() == 2) {
            DianPing dianping = mTuanGoudetail.getDianping().get(1);
            sdvAvatar2.setImageURI(Constant.PHOTOBASEURL + dianping.getFace());
            tvUserName2.setText(dianping.getNickname());
            tvEvaluateContent2.setText(dianping.getContents());
            //     tvScore2.setText(dianping.getScore());
            rlDianping1.setVisibility(View.VISIBLE);
            rlDianping2.setVisibility(View.VISIBLE);
            tvEvaluateContent.setVisibility(View.VISIBLE);
            tvEvaluateContent2.setVisibility(View.VISIBLE);
        } else {

        }


    }


    /**
     * 是否收藏
     */
    private void isFarvorite() {
        RequestParams params = new RequestParams();

        params.put("userId", SugoodApplication.user.getUserId());
        params.put("goodsId", mTuanId);


        HttpUtil.post(Constant.IS_GOODS_FAVORITES, params, new JsonHttpResponseHandler() {
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
        params.put("goodsId", mTuanId);
        params.put("type", "2");
        Log.e(TAG, "goodsId: " + mTuanId);
        Log.e(TAG, "userId: " + SugoodApplication.user.getUserId());
        HttpUtil.post(Constant.GOODS_COLLECtION_ADD, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Log.e(TAG, "farvoriteonSuccess: " + responseBody.toString());
                        ToastUtil.setToast(TuanGouNewDetailActivity.this, "收藏成功");
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
                                ToastUtil.setToast(TuanGouNewDetailActivity.this, "收藏失败");
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
        HttpUtil.post(Constant.DELETEGOOD, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e("TUi", "onSuccess: " + response.toString());

                try {
                    if (!response.getBoolean("success")) {
                        ToastUtil.setToast(TuanGouNewDetailActivity.this, response.getString("message"));
                    } else {
                        Resources resources = getBaseContext().getResources();
                        Drawable imageDrawable = resources.getDrawable(R.drawable.farvorite_false2); //图片在drawable目录下
                        mFarvorite.setBackgroundDrawable(imageDrawable);
                        isfarvorite= false;
                        ToastUtil.setToast(TuanGouNewDetailActivity.this, "删除商店收藏成功");
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

                ToastUtil.setToast(TuanGouNewDetailActivity.this, "删除商店失败");
            }
        });
    }


}
