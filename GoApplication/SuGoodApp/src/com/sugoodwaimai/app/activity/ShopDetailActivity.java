package com.sugoodwaimai.app.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.adapter.ShopTuanGouAdapter;
import com.sugoodwaimai.app.application.SugoodApplication;
import com.sugoodwaimai.app.entity.DianPing;
import com.sugoodwaimai.app.entity.Shop;
import com.sugoodwaimai.app.entity.TakeawayShop;
import com.sugoodwaimai.app.global.Constant;
import com.sugoodwaimai.app.util.HttpUtil;
import com.sugoodwaimai.app.util.JsonUtil;
import com.sugoodwaimai.app.util.SetListViewHeight;
import com.sugoodwaimai.app.util.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by wilk on 17/2/27 19:01
 * ganweib@gmail.com
 * describe:
 */

public class ShopDetailActivity extends BaseActivity {
    private static final String TAG = ShopDetailActivity.class.getSimpleName();

    private SimpleDraweeView sdvHeaderAD;
    private TextView tvFoodShopName;
    private RatingBar ratbarShop;
    private RatingBar ratbarShop3;
    private RatingBar ratbarShop1;
    private RatingBar ratbarShop2;
   // private LinearLayout llStar;
   // private TextView tvContentAverage;
  //  private TextView tvGrade;
    private TextView tvShopAddr;
//    private RelativeLayout rlTel;
//    private TextView tvTel;
    private TextView   sj_tell;
   // private RelativeLayout rlWaimai;
   // private TextView tvHadBuy;
//    private TextView tvFavorable1;
//    private TextView tvFavorable2;
    private ListView lvCombo;
    private RelativeLayout rlDianping1;
    private RelativeLayout rlDianping2;
    private RelativeLayout rl_nodp;
    private TextView tvEvaluate; //评价
    private RelativeLayout rlLookEvaluate;
    private SimpleDraweeView sdvAvatar1;
    private TextView tvUserName1;
    private TextView back;
    private View lined1;
    private View lined2;
    private View lined3;
    //private LinearLayout llStarEvaluate1;
   // private TextView tvScore1;
    private TextView tvEvaContet1;
    private SimpleDraweeView sdvAvatar2;
    private TextView tvUserName2;
    private LinearLayout llStarEvaluate2;
   // private TextView tvScore2;
    private TextView tvEvaContet2;
    private ScrollView scrollView;
    private RelativeLayout rl_tel;
    private TextView tv_look;
    private LinearLayout li_look;
    private RelativeLayout rl_wai;
    ImageView mFarvorite;
    private String shopID;
    private LinearLayout li_sjxinxi;
    private TextView tx_time;
    private TextView tx_wifi;
    int favoritesId =0;
    boolean   isfarvorite =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tuangou_shop_detail_activity);

        getIntentData();
        initView();
        initNetData();
//        updateView();
    }

    /**
     * 数据请求回来后 更新界面
     */
    private void updateView(final Shop shop) {
        sdvHeaderAD.setImageURI(Constant.PHOTOBASEURL + shop.getBaoshop().getPhoto());
        tvFoodShopName.setText(shop.getBaoshop().getShopName());
        if (shop.getBaoshop().getIsWai().equals("1")) {
            rl_wai.setVisibility(View.VISIBLE);
            rl_wai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    TakeawayShop takeawayShop = new TakeawayShop();
                    takeawayShop.setLogistics(3);
                    Intent intent = new Intent(ShopDetailActivity.this, TakeawayShopDetailActivity.class);
                    intent.putExtra("shopId", shopID);
                    intent.putExtra("shop", takeawayShop);
                    startActivity(intent);

                }
            });
        } else {
            rl_wai.setVisibility(View.GONE);
            lined3.setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(shop.getBaoshop().getScore())) {
            ratbarShop.setRating(0);
            ratbarShop3.setRating(0);
        } else {
            ratbarShop.setRating(Integer.parseInt(shop.getBaoshop().getScore()));
            ratbarShop3.setRating(Integer.parseInt(shop.getBaoshop().getScore()));
        }
      //  tvContentAverage.setText(("¥" + shop.getBaoshop().getPrice() + "/人"));
       // tvGrade.setText(shop.getBaoshop().getScore());
        tvShopAddr.setText(shop.getBaoshop().getAddr());
        //tvTel.setText(shop.getBaoshop().getTel());
        sj_tell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + shop.getBaoshop().getTel());
                intent.setData(data);
                startActivity(intent);
            }
        });
//        tvHadBuy.setText(shop.getBaoshop().);

//        if (TextUtils.isEmpty(shop.getYouHui().getMinAmount()) || TextUtils.isEmpty(shop.getYouHui().getAmount())) {
//            tvFavorable1.setVisibility(View.GONE);
//        }
//        tvFavorable2.setVisibility(View.GONE);
//        tvFavorable1.setText("满" + shop.getYouHui().getMinAmount() + "减" + shop.getYouHui().getAmount());
        tvEvaluate.setText("评价(" + shop.getBaoshop().getNum() + ")");
//        tvFavorable1.setVisibility(View.GONE);
//        tvFavorable2.setVisibility(View.GONE);
        Log.e("SHOPDATY", "updateView: " + shop.getDianping());
        if (shop.getDianping().size() == 0 || shop.getDianping() == null) {
            lined1.setVisibility(View.GONE);
            lined2.setVisibility(View.GONE);
            rlDianping1.setVisibility(View.GONE);
            rlDianping2.setVisibility(View.GONE);
            tvEvaContet1.setVisibility(View.GONE);
            tvEvaContet2.setVisibility(View.GONE);
            rl_nodp.setVisibility(View.VISIBLE);
            tvEvaluate.setVisibility(View.GONE);
            ratbarShop3.setVisibility(View.GONE);
            li_look.setVisibility(View.GONE);
        } else {
            rlDianping1.setVisibility(View.VISIBLE);
            rlDianping2.setVisibility(View.VISIBLE);
            tvEvaContet1.setVisibility(View.VISIBLE);
            tvEvaContet2.setVisibility(View.VISIBLE);

            rl_nodp.setVisibility(View.GONE);
        }
        if (shop.getDianping().size() != 0 && shop.getDianping().size() >= 1) {

            lined2.setVisibility(View.GONE);
            DianPing dianping = shop.getDianping().get(0);
            System.out.println("tvUserName1:"+tvUserName1);
            System.out.println("dianping:"+dianping.getNickname());
            sdvAvatar1.setImageURI(Constant.PHOTOBASEURL + dianping.getFace());
            tvUserName1.setText(dianping.getNickname());
            ratbarShop1.setRating(Integer.parseInt( dianping.getScore()));
            tvEvaContet1.setText(dianping.getContents());
            llStarEvaluate2.setVisibility(View.GONE);
            //tvScore1.setText(dianping.getScore());
            rlDianping1.setVisibility(View.VISIBLE);
            rlDianping2.setVisibility(View.GONE);
            tvEvaContet1.setVisibility(View.VISIBLE);
            tvEvaContet2.setVisibility(View.GONE);
        } else {

        }

        if (shop.getDianping().size() != 0 && shop.getDianping().size() == 2) {
            DianPing dianping = shop.getDianping().get(1);
            ratbarShop2.setRating(Integer.parseInt( dianping.getScore()));
            sdvAvatar2.setImageURI(Constant.PHOTOBASEURL + dianping.getFace());
            tvUserName2.setText(dianping.getNickname());
            tvEvaContet2.setText(dianping.getContents());
       //     tvScore2.setText(dianping.getScore());
            rlDianping1.setVisibility(View.VISIBLE);
            rlDianping2.setVisibility(View.VISIBLE);
            tvEvaContet1.setVisibility(View.VISIBLE);
            tvEvaContet2.setVisibility(View.VISIBLE);
        } else {

        }
        System.out.println("getIsWifi"+shop.getBaoshop().getIsWifi());
        if (shop.getBaoshop().getIsWifi().equals("1")){
            tx_wifi.setVisibility(View.VISIBLE);
        }
        tx_time.setText(shop.getBaoshop().getBusinessTime());
            if (shop.getBaoTuan().size() > 0) {
            ShopTuanGouAdapter mAdapter = new ShopTuanGouAdapter(this, shop.getBaoTuan());
            lvCombo.setAdapter(mAdapter);
            SetListViewHeight setListViewHeight = new SetListViewHeight();
            setListViewHeight.setHegiht(lvCombo);
            scrollView.smoothScrollTo(0, 20);
            scrollView.setFocusable(true);


            lvCombo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent();
                    intent.setClass(ShopDetailActivity.this, TuanGouNewDetailActivity.class);
                    intent.putExtra("shopId", shopID);
                    intent.putExtra("tuanId", shop.getBaoTuan().get(position).getTuanId());
                    startActivity(intent);
                }
            });
        }
    }

    /**
     * 获取上级传过来的intent 值
     */
    private void getIntentData() {
        shopID = getIntent().getStringExtra("shopId");

    }

    // 加载网络数据
    private void initNetData() {

        showLoading("");

        RequestParams params = new RequestParams();
        params.put("shopId", shopID);
        HttpUtil.post(Constant.SHOP, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e("gan", "onSuccess: " + response.toString());
                Shop shop = JsonUtil.toObject(response.toString(), Shop.class);
                Log.e("shop", "onSuccess: " + shop.toString());
                updateView(shop);
                closeLoading();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e("gan", "onFailure: " + "ssdad");
                Toast.makeText(ShopDetailActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
                closeLoading();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.e("gan", "onFailure:," + errorResponse.toString());
                Toast.makeText(ShopDetailActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
                closeLoading();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                closeLoading();
                Log.e("gan", "onFailure: " + errorResponse.toString());
                Toast.makeText(ShopDetailActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {


        lined1= (View) findViewById(R.id.lined1);
        lined2= (View) findViewById(R.id.lined2);
        lined3= (View) findViewById(R.id.lined3);
        li_sjxinxi= (LinearLayout) findViewById(R.id.li_sjxinxi);
        tx_time= (TextView) findViewById(R.id.tx_time);
        tx_wifi= (TextView) findViewById(R.id.tx_wifi);
        if (SugoodApplication.isLogin){
            isFarvorite();}
        mFarvorite= (ImageView) findViewById(R.id.collect);

        mFarvorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SugoodApplication.isLogin) {
                    if (isfarvorite){
                        delect();
                    }else
                        farvorite();
                } else {
                    startActivity(new Intent(ShopDetailActivity.this, LoginActivity.class));
                }

            }
        });


        rl_wai = (RelativeLayout) findViewById(R.id.rl_wai);
        sdvHeaderAD = (SimpleDraweeView) findViewById(R.id.sdv_head_ad);
        tvFoodShopName = (TextView) findViewById(R.id.tv_foodShopName);
        //llStar = (LinearLayout) findViewById(R.id.ll_star);
        ratbarShop = (RatingBar) findViewById(R.id.ratbarShop);
        ratbarShop3= (RatingBar) findViewById(R.id.ratbarShop3);
        ratbarShop1= (RatingBar) findViewById(R.id.ratbarShop1);
        ratbarShop2= (RatingBar) findViewById(R.id.ratbarShop2);
      //  tvContentAverage = (TextView) findViewById(R.id.tv_content_average);
        tvShopAddr = (TextView) findViewById(R.id.tv_shop_addr);
//        rlTel = (RelativeLayout) findViewById(R.id.rl_tel);
//        tvTel = (TextView) findViewById(R.id.tv_tel);
        sj_tell = (TextView) findViewById(R.id.sj_tell);
        //rlWaimai = (RelativeLayout) findViewById(R.id.rl_waimai);
       // tvHadBuy = (TextView) findViewById(R.id.tv_hadBuy);
//        tvFavorable1 = (TextView) findViewById(R.id.tv_favorable1);
//        tvFavorable2 = (TextView) findViewById(R.id.tv_favorable2);
        lvCombo = (ListView) findViewById(R.id.lv_group);
        rlDianping1 = (RelativeLayout) findViewById(R.id.rl_dianping1);
        rlDianping2 = (RelativeLayout) findViewById(R.id.rl_dianping2);
        rl_nodp = (RelativeLayout) findViewById(R.id.rl_nodp);
        tvEvaluate = (TextView) findViewById(R.id.tv_evaluate);
        rlLookEvaluate = (RelativeLayout) findViewById(R.id.rl_look_evaluate);
        sdvAvatar1 = (SimpleDraweeView) findViewById(R.id.sdv_avatar);
        tvUserName1 = (TextView) findViewById(R.id.tv_userName1);
        back= (TextView) findViewById(R.id.back);
     //   llStarEvaluate1 = (LinearLayout) findViewById(R.id.ll_stars);
        //tvScore1 = (TextView) findViewById(R.id.tv_score);
        tvEvaContet1 = (TextView) findViewById(R.id.tv_evaluate_content);
        sdvAvatar2 = (SimpleDraweeView) findViewById(R.id.sdv_avatar_2);
        tvUserName2 = (TextView) findViewById(R.id.tv_userName2);
        llStarEvaluate2 = (LinearLayout) findViewById(R.id.ll_stars_2);
     //   tvScore2 = (TextView) findViewById(R.id.tv_score2);
        tvEvaContet2 = (TextView) findViewById(R.id.tv_evaluate_content2);
      //tvGrade = (TextView) findViewById(R.id.tv_grade);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        tv_look = (TextView) findViewById(R.id.tv_look);
        li_look  = (LinearLayout) findViewById(R.id.li_look);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });
//        rlTel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                Uri data = Uri.parse("tel:" + tvTel.getText());
//                intent.setData(data);
//                startActivity(intent);
//            }
//        });
        li_look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("shopId", shopID);
                intent.setClass(ShopDetailActivity.this, PingLunActivity.class);
                startActivity(intent);
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
        params.put("type", "2");
        Log.e(TAG, "farvorite: " + getIntent().getStringExtra("shopId"));
        HttpUtil.post(Constant.SHOP_COLLECtION_ADD, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        ToastUtil.setToast(ShopDetailActivity.this, "收藏成功");
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
                                ToastUtil.setToast(ShopDetailActivity.this, "已收藏该商店");
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
                        ToastUtil.setToast(ShopDetailActivity.this, response.getString("message"));
                    } else {
                        Resources resources = getBaseContext().getResources();
                        Drawable imageDrawable = resources.getDrawable(R.drawable.farvorite_false2); //图片在drawable目录下
                        mFarvorite.setBackgroundDrawable(imageDrawable);
                        isfarvorite= false;
                        ToastUtil.setToast(ShopDetailActivity.this, "删除商店收藏成功");
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

                ToastUtil.setToast(ShopDetailActivity.this, "删除商店失败");
            }
        });
    }
}
