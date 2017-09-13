package com.sugoodwaimai.app.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.baidu.location.BDLocation;
//import com.baidu.location.BDLocationListener;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.adapter.TakeawayMainAdapter;
import com.sugoodwaimai.app.adapter.TakeawayMainPagerAdapter;
import com.sugoodwaimai.app.application.SugoodApplication;
import com.sugoodwaimai.app.entity.AdEntity;
import com.sugoodwaimai.app.entity.Ctiy;
import com.sugoodwaimai.app.entity.TakeawayShop;
import com.sugoodwaimai.app.fragment.TakeawayTypeFragment;
import com.sugoodwaimai.app.global.Constant;
import com.sugoodwaimai.app.listener.TakeawayPageChangeListener;
import com.sugoodwaimai.app.util.GlideUtil;
import com.sugoodwaimai.app.util.HttpUtil;
import com.sugoodwaimai.app.util.JsonUtil;
import com.sugoodwaimai.app.util.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * Package :com.android.supermarket.takeaway
 * Description :
 * Author :Rc3
 * Created at :2017/2/27 16:35.
 */

public class TakeawayMainActivity extends BaseActivity {
    private static final String TAG = TakeawayMainActivity.class.getSimpleName();

    private long exitTime = 0;
    @BindView(R.id.takeaway_main_rv)
    XRecyclerView mRecyclerView;
    @BindView(R.id.takeaway_search_editText)
    EditText mSeacher;
    //高德地图
    //public AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    public static String locationCity = "";
    public static String locationAddress = "";
    Context mContext;
    int TopRemarkNum = 0;
    int TopSoldNum = 1;
    int normalState = 2;
    int mainType = 0;
    int page = 1;
    boolean isLoadMore;
    List<Fragment> mList;
    List<ImageView> mDotList;
    List<TakeawayShop> mShopList;
    TakeawayMainAdapter rvAdapter;
    View headerView;
    List<AdEntity> EntityList;

    RelativeLayout mAllTypeLayout;
    RelativeLayout mMostSoldLayout;
    RelativeLayout mBestCommentLayout;
//    ImageView takeaway_all_shops_img;
//    ImageView takeaway_most_sold_shops_img;
//    ImageView takeaway_best_comment_shops_img;


    TextView mAllTypeTv;
    TextView mMostSoldTv;
    TextView mBestCommentTv;

    ImageView takeaway_banner_img;
    ImageView takeaway_banner_img1;
    ImageView takeaway_banner_img2;
    ImageView takeaway_banner_img3;
    ImageView takeaway_banner_img4;
    ViewPager vp_classic;
    @BindView(R.id.takeaway_map)
    ImageView takeaway_map;
    private boolean isToast = false;
    private int autoCurrIndex = 0;

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (msg.arg1 != 0) {
                        vp_classic.setCurrentItem(msg.arg1);
                    } else {
                        //false 当从末页调到首页是，不显示翻页动画效果，
                        vp_classic.setCurrentItem(msg.arg1, false);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.takeaway_main_activity);
        ButterKnife.bind(this);
        mContext = this;
        initLocation();
        showLoading("定位中");
        startLocation();
        // initData();
        takeaway_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //s startLocation();
           //   ToastUtil.setToast(TakeawayMainActivity.this, "高德定位刷新");

            }
        });

    }



    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {

                StringBuffer sb = new StringBuffer();
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if (location.getErrorCode() == 0) {
                    sb.append("定位成功" + "\n");
                    sb.append("定位类型: " + location.getLocationType() + "\n");
                    sb.append("经    度    : " + location.getLongitude() + "\n");
                    sb.append("纬    度    : " + location.getLatitude() + "\n");
                    sb.append("精    度    : " + location.getAccuracy() + "米" + "\n");
                    sb.append("提供者    : " + location.getProvider() + "\n");

                    sb.append("速    度    : " + location.getSpeed() + "米/秒" + "\n");
                    sb.append("角    度    : " + location.getBearing() + "\n");
                    // 获取当前提供定位服务的卫星个数
                    sb.append("星    数    : " + location.getSatellites() + "\n");
                    sb.append("国    家    : " + location.getCountry() + "\n");
                    sb.append("省            : " + location.getProvince() + "\n");
                    sb.append("市            : " + location.getCity() + "\n");
                    sb.append("城市编码 : " + location.getCityCode() + "\n");
                    sb.append("区            : " + location.getDistrict() + "\n");
                    sb.append("区域 码   : " + location.getAdCode() + "\n");
                    sb.append("地    址    : " + location.getAddress() + "\n");
                    sb.append("兴趣点    : " + location.getPoiName() + "\n");
                    //定位完成的时间
                    //sb.append("定位时间: " + Utils.formatUTC(location.getTime(), "yyyy-MM-dd HH:mm:ss") + "\n");
                    sb.append("定位时间: " + location.getTime() + "\n");
                    locationCity = location.getCity();

                    locationAddress = location.getAddress();
                    if (locationAddress != null) {
                        locationAddress = locationAddress.replace("[^\u4E00-\u9FA5]", "");
                        Log.e(TAG, "onReceiveLocation: " + locationAddress);
                    }
                    if (locationCity != null) {
                        locationCity = locationCity.replaceAll("[^\u4E00-\u9FA5]", "");

                    }
                    //      params.put("lat", "20.919973");
//      params.put("lng", "110.084040");
                    System.out.println("aaaaa111aaaa写死");
                    initNetData(  "20.919973",   "110.084040", locationCity);
                 //   initNetData(location.getLatitude() + "", location.getLongitude() + "", locationCity);
                 //   tvLocalCity.setText(locationCity);
                } else {
                    //定位失败
                    closeLoading();
                    ToastUtil.setToast(TakeawayMainActivity.this, "获取位置失败，请重新打开");
                    sb.append("定位失败" + "\n");
                    sb.append("错误码:" + location.getErrorCode() + "\n");
                    sb.append("错误信息:" + location.getErrorInfo() + "\n");
                    sb.append("错误描述:" + location.getLocationDetail() + "\n");
                //    tvLocalCity.setText("定位失败");
                }
                //定位之后的回调时间
                sb.append("回调时间: " + System.currentTimeMillis() + "\n");
//                sb.append("回调时间: " + Utils.formatUTC(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "\n");

                //解析定位结果，
                String result = sb.toString();
                System.out.println("-----定位信息-->>>" + sb.toString());
            } else {
                System.out.println("-----定位失败-->>>");
            }
        }
    };

    /**
     * 开始定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    public void startLocation() {
        //根据控件的选择，重新设置定位参数
        //resetOption();
        // 设置定位参数
        SugoodApplication.locationClient.setLocationOption(locationOption);
        // 启动定位
        SugoodApplication.locationClient.startLocation();
    }

    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void stopLocation() {
        // 停止定位
        SugoodApplication.locationClient.stopLocation();
    }

    /**
     * 销毁定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void destroyLocation() {
        if (null != SugoodApplication.locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            SugoodApplication.locationClient.onDestroy();
            SugoodApplication.locationClient = null;
            locationOption = null;
        }
    }
    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void initLocation() {
        //初始化client
        SugoodApplication.locationClient = new AMapLocationClient(this);
        locationOption = getDefaultOption();
        //设置定位参数
        SugoodApplication.locationClient.setLocationOption(locationOption);
        // 设置定位监听
        SugoodApplication.locationClient.setLocationListener(locationListener);

    }

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }

    //上传经纬度
    private void initNetData(String lat
            , String lng, String cityName) {
        SugoodApplication.lat=lat;
        SugoodApplication.lng=lng;
        RequestParams params = new RequestParams();
        params.put("lat", lat);
        params.put("lng", lng);
//      params.put("lat", "20.919973");
//      params.put("lng", "110.084040");
        params.put("cityName", cityName);

//        params.put("lat", SugoodApplication.mLocationClient.getLastKnownLocation().getLatitude()+"");
//        params.put("lng", SugoodApplication.mLocationClient.getLastKnownLocation().getLongitude()+"");
//        params.put("cityName", SugoodApplication.mLocationClient.getLastKnownLocation().getCity());
//        Log.e(TAG, "initNetData: "+SugoodApplication.mLocationClient.getLastKnownLocation().getLatitude() );
//        Log.e(TAG, "initNetData: "+SugoodApplication.mLocationClient.getLastKnownLocation().getLongitude() );
//        Log.e(TAG, "initNetData: " + SugoodApplication.getLocalCity());

//        params.put("cityName", "湛江市");
        HttpUtil.post(Constant.UPLOADLATLONG, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e(TAG, "onSuccess: " + response.toString());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                List<Ctiy> list = JsonUtil.toList(response.toString(), Ctiy.class);
                SugoodApplication.getInstance().setCtiyList(list);
                initData();
                Log.e(TAG, "onSuccess: " + response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e(TAG, "onFailure2: " + responseString);
            }
        });}


    @OnClick(R.id.takeaway_main_header_back)
    void onBack() {
        finish();
    }


    private void requestRvData(int page, int waicate, int xiaoPing, final boolean isLoadMore) {
        RequestParams params = new RequestParams();
        if (waicate != 0) {
            params.put("WaiCate", String.valueOf(waicate));
        }
        if (xiaoPing == TopRemarkNum) {
            params.put("xiaoPing", 0);
        } else if (xiaoPing == TopSoldNum) {
            params.put("xiaoPing", 1);
        }
        params.put("page", String.valueOf(page));
        params.put("pageSize", "15");
        params.put("lat", SugoodApplication.lat);
        params.put("lng", SugoodApplication.lng);
        HttpUtil.post(Constant.TAKEAWAY_MAIN_LIST_URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                closeLoading();
                if (statusCode == 200) {
                    Log.i(TAG, "onSuccess: " + new String(responseBody));

                    final String result = new String(responseBody);
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            if (!isLoadMore) {
                                mShopList.clear();
                            }
                            mShopList.addAll(JsonUtil.toList(result, TakeawayShop.class));
                            rvAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, final Throwable error) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        closeLoading();
                        Toast.makeText(mContext, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    private void initData() {


        headerView = LayoutInflater.from(mContext).inflate(R.layout.include_takeaway_main, null);
        initRVHeaderView(headerView);
        mRecyclerView.addHeaderView(headerView);
        mShopList = new ArrayList<>();

        rvAdapter = new TakeawayMainAdapter(mContext, mShopList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(rvAdapter);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mRecyclerView.setNestedScrollingEnabled(true);
                onRvRefresh(0, normalState);
            }

            @Override
            public void onLoadMore() {
                mRecyclerView.setNestedScrollingEnabled(true);
                page++;
                isLoadMore = true;
                requestRvData(page, 0, normalState, isLoadMore);
                mRecyclerView.loadMoreComplete();
                isLoadMore = false;
            }
        });

        mRecyclerView.refresh();


        mSeacher.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((actionId == 0 || actionId == 3) && event != null) {
                    RequestParams params = new RequestParams();
                    params.put("shopName", v.getText().toString().trim());
                    params.put("page", String.valueOf(page));
                    params.put("pageSize", "15");
                    params.put("lat", SugoodApplication.lat);
                    params.put("lng", SugoodApplication.lng);
//                    lat="20.919973";
//                    lng= "110.084040";
//                    System.out.println("aaaaa11111");
//                    params.put("lat", "20.919973");
//                    params.put("lng", "110.084040");
               //     System.out.println("params:"+params.toString());
                    HttpUtil.post(Constant.TAKEAWAY_MAIN_LIST_URL, params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if (statusCode == 200) {
                                Log.i(TAG, "onSuccess223: " + new String(responseBody));

                                final String result = new String(responseBody);
                                new Handler().post(new Runnable() {
                                    @Override
                                    public void run() {

                                        mShopList.clear();

                                        mShopList.addAll(JsonUtil.toList(result, TakeawayShop.class));
                                        if(mShopList.size()!=0){
                                        rvAdapter.notifyDataSetChanged();
                                        closeKeyBoard();
                                        mRecyclerView.smoothScrollBy(0, headerView.getMeasuredHeight());}else
                                        {
                                            mRecyclerView.refresh();
                                        }
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, final Throwable error) {
                            new Handler().post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(mContext, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                return false;
            }
        });


    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    private void initRVHeaderView(final View headerView) {
        System.out.println("1111111111111111");
        mAllTypeLayout = (RelativeLayout) headerView.findViewById(R.id.takeaway_all_shops_rl);
        mMostSoldLayout = (RelativeLayout) headerView.findViewById(R.id.takeaway_most_sold_shops_rl);
        mBestCommentLayout = (RelativeLayout) headerView.findViewById(R.id.takeaway_best_comment_shops_rl);
//        takeaway_all_shops_img = (ImageView) headerView.findViewById(R.id.takeaway_all_shops_img);
//        takeaway_most_sold_shops_img = (ImageView) headerView.findViewById(R.id.takeaway_most_sold_shops_img);
//        takeaway_best_comment_shops_img = (ImageView) headerView.findViewById(R.id.takeaway_best_comment_shops_img);
        vp_classic = (ViewPager) headerView.findViewById(R.id.vp_classic);

        mAllTypeTv = (TextView) headerView.findViewById(R.id.takeaway_all_shops_tv);
        mMostSoldTv = (TextView) headerView.findViewById(R.id.takeaway_most_sold_shops_tv);
        mBestCommentTv = (TextView) headerView.findViewById(R.id.takeaway_best_comment_shops_tv);

//        takeaway_banner_img = (ImageView) headerView.findViewById(R.id.takeaway_banner_img);
        takeaway_banner_img1 = (ImageView) headerView.findViewById(R.id.takeaway_banner_img1);
        takeaway_banner_img2 = (ImageView) headerView.findViewById(R.id.takeaway_banner_img2);
        takeaway_banner_img3 = (ImageView) headerView.findViewById(R.id.takeaway_banner_img3);
        takeaway_banner_img4 = (ImageView) headerView.findViewById(R.id.takeaway_banner_img4);

        takeaway_banner_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startInetnt(3);
            }
        });
        takeaway_banner_img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startInetnt(2);
            }
        });
        takeaway_banner_img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startInetnt(1);
            }
        });
        takeaway_banner_img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startInetnt(0);
            }
        });


//        takeaway_all_shops_img.setImageResource(R.drawable.superscript_right_icon);
//        takeaway_most_sold_shops_img.setImageResource(R.drawable.superscript_black_right_icon);
//        takeaway_best_comment_shops_img.setImageResource(R.drawable.superscript_black_right_icon);
        mAllTypeTv.setTextColor(getResources().getColor(R.color.red));
        mMostSoldTv.setTextColor(getResources().getColor(R.color.grey));
        mBestCommentTv.setTextColor(getResources().getColor(R.color.grey));
//        takeaway_banner_img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        /**
         *  请求广告图片
         */
        RequestParams params = new RequestParams();
        params.put("siteId", "93");
        HttpUtil.post(Constant.GOODFOODAD, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                closeLoading();
                Log.e("TAG_AD1", "onSuccess: " + response);
                if (response.equals("")){vp_classic.setVisibility(View.GONE);}else {

                    final List<AdEntity> adEntityList = JsonUtil.toList(response.toString(), AdEntity.class);
                    initViewpager(adEntityList);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {

                    final List<AdEntity> adEntityList = JsonUtil.toList(response.getString("list"), AdEntity.class);
                    initViewpager(adEntityList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
                Log.e("TAG_AD3", "onSuccess: " + responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e("Failure", "onFailure: " + statusCode + responseString);
            }
        });

        RequestParams adParams = new RequestParams();
        adParams.put("siteId", "96");
        HttpUtil.post(Constant.GOODFOODAD, adParams, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                super.onSuccess(statusCode, headers, response);
//                Log.e("TAG_AD", "onSuccess: " + response);
//                EntityList = JsonUtil.toList(response.toString(), AdEntity.class);
//                if (EntityList.size()>0){
//                    GlideUtil.displayImage(Constant.PHOTOBASEURL + EntityList.get(0).getPhoto(), takeaway_banner_img1);
//                    GlideUtil.displayImage(Constant.PHOTOBASEURL + EntityList.get(1).getPhoto(), takeaway_banner_img2);
//                    GlideUtil.displayImage(Constant.PHOTOBASEURL + EntityList.get(2).getPhoto(), takeaway_banner_img3);
//                    GlideUtil.displayImage(Constant.PHOTOBASEURL + EntityList.get(3).getPhoto(), takeaway_banner_img4);
//                }
//            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e("TAG_AD", "onSuccess: " + response);
                try {
                    EntityList = JsonUtil.toList(response.getString("list"), AdEntity.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (EntityList.size()>0){
                    GlideUtil.displayImage(Constant.PHOTOBASEURL + EntityList.get(0).getPhoto(), takeaway_banner_img4);
                    GlideUtil.displayImage(Constant.PHOTOBASEURL + EntityList.get(1).getPhoto(), takeaway_banner_img3);
                    GlideUtil.displayImage(Constant.PHOTOBASEURL + EntityList.get(2).getPhoto(), takeaway_banner_img2);
                    GlideUtil.displayImage(Constant.PHOTOBASEURL + EntityList.get(3).getPhoto(), takeaway_banner_img1);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e("Failure11", "onFailure: " + statusCode + responseString);
            }
        });

        mAllTypeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRvRefresh(0, 2);
//                View view = LayoutInflater.from(mContext).inflate(R.layout.takeaway_type_view, null);
//                final PopupWindow window = getPopupWindow(mContext, view, mAllTypeLayout);
//                final LinearLayout viewLayout = (LinearLayout) view.findViewById(R.id.takeaway_type_view_ll);
//                for (int i = 0; i < viewLayout.getChildCount(); i++) {
//                    final int num = i;
//
//                    viewLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            mAllTypeTv.setText(((TextView) viewLayout.getChildAt(num)).getText().toString());
//
//                            mainType = num;
//                            window.dismiss();
//                        }
//                    });
//                }
//                takeaway_all_shops_img.setImageResource(R.drawable.superscript_right_icon);
//                takeaway_most_sold_shops_img.setImageResource(R.drawable.superscript_black_right_icon);
//                takeaway_best_comment_shops_img.setImageResource(R.drawable.superscript_black_right_icon);
                mAllTypeTv.setTextColor(getResources().getColor(R.color.red));
                mMostSoldTv.setTextColor(getResources().getColor(R.color.grey));
                mBestCommentTv.setTextColor(getResources().getColor(R.color.grey));

            }
        });
        mMostSoldLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRvRefresh(0, 1);
//                View view = LayoutInflater.from(mContext).inflate(R.layout.takeaway_sold_view, null);
//                final PopupWindow window = getPopupWindow(mContext, view, mMostSoldLayout);
//                final LinearLayout viewLayout = (LinearLayout) view.findViewById(R.id.takeaway_sold_view_ll);
//                for (int i = 0; i < viewLayout.getChildCount(); i++) {
//                    final int num = i;
//                    viewLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            mMostSoldTv.setText(((TextView) viewLayout.getChildAt(num)).getText().toString());
//                            if (num == 0) {
//                                onRvRefresh(mainType, TopSoldNum);
//                            } else {
//                                onRvRefresh(mainType, normalState);
//                            }
//
//                            window.dismiss();
//                        }
//                    });
//                }
//
//
//                takeaway_all_shops_img.setImageResource(R.drawable.superscript_black_right_icon);
//                takeaway_most_sold_shops_img.setImageResource(R.drawable.superscript_right_icon);
//                takeaway_best_comment_shops_img.setImageResource(R.drawable.superscript_black_right_icon);
                mAllTypeTv.setTextColor(getResources().getColor(R.color.grey));
                mMostSoldTv.setTextColor(getResources().getColor(R.color.red));
                mBestCommentTv.setTextColor(getResources().getColor(R.color.grey));
            }
        });

        mBestCommentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRvRefresh(0, 0);
//                View view = LayoutInflater.from(mContext).inflate(R.layout.takeaway_comment_view, null);
//                final PopupWindow window = getPopupWindow(mContext, view, mBestCommentLayout);
//                final LinearLayout viewLayout = (LinearLayout) view.findViewById(R.id.takeaway_comment_view_ll);
//                for (int i = 0; i < viewLayout.getChildCount(); i++) {
//                    final int num = i;
//                    viewLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            mBestCommentTv.setText(((TextView) viewLayout.getChildAt(num)).getText().toString());
//                            if (num == 0) {
//                                onRvRefresh(mainType, normalState);
//                            } else {
//                                onRvRefresh(mainType, TopRemarkNum);
//                            }
//
//                            window.dismiss();
//                        }
//                    });
//                }
//
//                takeaway_all_shops_img.setImageResource(R.drawable.superscript_black_right_icon);
//                takeaway_most_sold_shops_img.setImageResource(R.drawable.superscript_black_right_icon);
//                takeaway_best_comment_shops_img.setImageResource(R.drawable.superscript_right_icon);
                mAllTypeTv.setTextColor(getResources().getColor(R.color.grey));
                mMostSoldTv.setTextColor(getResources().getColor(R.color.grey));
                mBestCommentTv.setTextColor(getResources().getColor(R.color.red));
            }
        });

        final ViewPager mViewpager = (ViewPager) headerView.findViewById(R.id.takeaway_main_vp);
        final LinearLayout mDotll = (LinearLayout) headerView.findViewById(R.id.takeaway_dot_ll);
//        final ImageView mBannerImg = (ImageView) headerView.findViewById( R.id.takeaway_banner_img);
        final LinearLayout mBannerLayout = (LinearLayout) headerView.findViewById(R.id.takeaway_banner_ll);
        final LinearLayout mNearShopLayout = (LinearLayout) headerView.findViewById(R.id.takeaway_near_shop_ll);
        mList = new ArrayList<>();
        mDotList = new ArrayList<>();
        mDotList.add((ImageView) headerView.findViewById(R.id.takeaway_dot1));
        mDotList.add((ImageView) headerView.findViewById(R.id.takeaway_dot2));
        for (int i = 0; i < 2; i++) {
            TakeawayTypeFragment fragment = new TakeawayTypeFragment();
            fragment.setListener(new TakeawayTypeFragment.OnItemClickListener() {
                @Override
                public void scroll() {


                    mRecyclerView.smoothScrollBy(0, headerView.getMeasuredHeight());
                }

                @Override
                public void onClick(int position) {

                    requestRvData(1, position, normalState, isLoadMore);
                }
            });
            mList.add(fragment);

        }
        TakeawayMainPagerAdapter adapter = new TakeawayMainPagerAdapter(getSupportFragmentManager(), mList);
        mViewpager.addOnPageChangeListener(new TakeawayPageChangeListener(mList, mDotList));
        mViewpager.setAdapter(adapter);

    }

    private void onRvRefresh(int waicate, int xingPing) {

        page = 1;
        requestRvData(page, waicate, xingPing, isLoadMore);
        mRecyclerView.refreshComplete();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }

    }



    private void startInetnt(int position) {
        Intent intent = new Intent();
        if (EntityList.get(position).getType().equals("1")) {
            intent.putExtra("tuanId", EntityList.get(position).getDiyid());
            intent.putExtra("shopId", "");
            intent.setClass(TakeawayMainActivity.this, TuanGouDetailActivity.class);
            startActivity(intent);
        } else if (EntityList.get(position).getType().equals("2")) {
            intent.putExtra("shopId", EntityList.get(position).getDiyid());
            intent.setClass(TakeawayMainActivity.this, ShopDetailActivity.class);
            startActivity(intent);
        } else if (EntityList.get(position).getType().equals("3")) {
            intent.putExtra("shopId", EntityList.get(position).getDiyid());
            intent.setClass(TakeawayMainActivity.this, TakeawayShopDetailActivity.class);
            startActivity(intent);
        } else if (EntityList.get(position).getType().equals("4")) {
            intent.setClass(TakeawayMainActivity.this, TakeawayMarketDetailActivity.class);
            intent.putExtra("goodsId", EntityList.get(position).getDiyid());
            startActivity(intent);
        } else if (EntityList.get(position).getType().equals("5")) {
            intent.setClass(TakeawayMainActivity.this, TakeawayMarketShopDetailActivity.class);
            intent.putExtra("cateId", EntityList.get(position).getDiyid());
            startActivity(intent);
//                        intent.putExtra("asa", (Serializable) mShopMainList);
        } else {
            ToastUtil.setToast(TakeawayMainActivity.this, "正在建设中。。。");
        }

    }

    private PopupWindow getPopupWindow(Context context, View contentView, View targetView) {
        PopupWindow popupwindow = new PopupWindow(context);
        popupwindow.setOutsideTouchable(true);
        popupwindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        popupwindow.setContentView(contentView);
        popupwindow.showAsDropDown(targetView);
        return popupwindow;
    }

    private void closeKeyBoard() {
        View view = getWindow().getDecorView();
        if (view != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private ArrayList<ImageView> imageList = new ArrayList<ImageView>();

    private void initViewpager(final List<AdEntity> adEntityList) {
        for (int i = 0; i < adEntityList.size(); i++) {
            ImageView iv = new ImageView(mContext);
            GlideUtil.displayImage(Constant.PHOTOBASEURL + adEntityList.get(i).getPhoto(), iv);
            imageList.add(iv);
        }
        ImageAdapter pagerAdapter = new ImageAdapter(mContext, imageList, adEntityList);
        vp_classic.setAdapter(pagerAdapter);
        // 设置自动轮播图片，5s后执行，周期是5s
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 0;
                if (autoCurrIndex == adEntityList.size() - 1) {
                    autoCurrIndex = -1;
                }
                message.arg1 = autoCurrIndex + 1;
                mHandler.sendMessage(message);
            }
        }, 5000, 5000);
    }

    class ImageAdapter extends PagerAdapter {
        private Context context;
        private List<ImageView> imageList;
        private List<AdEntity> adEntityList;

        public ImageAdapter(Context context, List<ImageView> imageList, List<AdEntity> adEntityList) {
            this.context = context;
            this.imageList = imageList;
            this.adEntityList = adEntityList;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            container.addView(imageList.get(position));

            imageList.get(position).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    if (adEntityList.get(position).getType().equals("1")) {
                        intent.putExtra("tuanId", adEntityList.get(position).getDiyid());
                        intent.putExtra("shopId", "");
                        startActivity(intent);
                        intent.setClass(TakeawayMainActivity.this, TuanGouDetailActivity.class);
                    } else if (adEntityList.get(position).getType().equals("2")) {
                        intent.putExtra("shopId", adEntityList.get(position).getDiyid());
                        intent.setClass(TakeawayMainActivity.this, ShopDetailActivity.class);
                        startActivity(intent);
                    } else if (adEntityList.get(position).getType().equals("3")) {
                        intent.putExtra("shopId", adEntityList.get(position).getDiyid());
                        intent.setClass(TakeawayMainActivity.this, TakeawayShopDetailActivity.class);
                        startActivity(intent);
                    } else if (adEntityList.get(position).getType().equals("4")) {
                        intent.setClass(TakeawayMainActivity.this, TakeawayMarketDetailActivity.class);
                        intent.putExtra("goodsId", adEntityList.get(position).getDiyid());
                        startActivity(intent);
                    } else if (adEntityList.get(position).getType().equals("5")) {
                        intent.setClass(TakeawayMainActivity.this, TakeawayMarketShopDetailActivity.class);
                        intent.putExtra("cateId", adEntityList.get(position).getDiyid());
                        startActivity(intent);
//                        intent.putExtra("asa", (Serializable) mShopMainList);
                    }else {
                        ToastUtil.setToast(TakeawayMainActivity.this,"暂不支持");
                    }

                }
            });

            return imageList.get(position);
        }

        @Override
        public int getCount() {
            return imageList.size();
        }


        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView(imageList.get(position));
        }


    }


}
