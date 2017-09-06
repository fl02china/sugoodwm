package com.sugoodwaimai.app.activity;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.igexin.sdk.PushManager;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.entity.Ctiy;
import com.sugoodwaimai.app.global.Constant;
import com.sugoodwaimai.app.util.HttpUtil;
import com.sugoodwaimai.app.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;


/**
 * 基础页面
 */
public class MainActivity extends TabActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Context context;

    private TabHost tabhost;
    private TabHost.TabSpec homeTab;
    private TabHost.TabSpec nearbyTab;
    //private TabHost.TabSpec servicedemandTab;
    private TabHost.TabSpec mineTab;

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        initView();
        // com.getui.demo.DemoPushService 为第三方自定义推送服务
        PushManager.getInstance().initialize(this.getApplicationContext(), com.sugoodwaimai.app.service.DemoPushService.class);
        // com.getui.demo.DemoIntentService 为第三方自定义的推送服务事件接收类
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), com.sugoodwaimai.app.service.DemoIntentService.class);


      //  initNetData();
    }

    //上传经纬度
    private void initNetData() {

        RequestParams params = new RequestParams();

//        params.put("lat", SugoodApplication.mLocationClient.getLastKnownLocation().getLatitude());
//        params.put("lng", SugoodApplication.mLocationClient.getLastKnownLocation().getLongitude());
//        params.put("cityName", SugoodApplication.getLocalCity());
        params.put("lat", "20.912420741522");
        params.put("lng", "110.08775659179");
        params.put("cityName", "湛江市");
        Log.e(TAG, "initNetData: " + "jinru");
        HttpUtil.post(Constant.UPLOADLATLONG, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e(TAG, "onSuccess: " + response.toString());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                Log.e(TAG, "onSuccess: " + response.toString());
                List<Ctiy> list = JsonUtil.toList(response.toString(), Ctiy.class);
                Log.e(TAG, "onSuccess: " + list.get(0).toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e(TAG, "onFailure: " + responseString);
            }
        });
    }


    private void initView() {
        tabhost = this.getTabHost();
        homeTab = tabhost.newTabSpec("home");
        nearbyTab = tabhost.newTabSpec("nearby");
        //servicedemandTab = tabhost.newTabSpec("servicedemand");
        mineTab = tabhost.newTabSpec("mine");

        homeTab.setIndicator(createContent("外卖", R.drawable.main_home_tab));
        nearbyTab.setIndicator(createContent("附近", R.drawable.main_nearby_tab));
        //servicedemandTab.setIndicator(createContent("订单", R.drawable.main_service_demand_tab));
        mineTab.setIndicator(createContent("我的", R.drawable.main_mine_tab));

        // 绑定显示的页面
        homeTab.setContent(new Intent(this, TakeawayMainActivity.class));
        nearbyTab.setContent(new Intent(this,NearbyActivity .class));
      //  servicedemandTab.setContent(new Intent(this, ServiceDemandActivity.class));
        //servicedemandTab.setContent(new Intent(this, OrderManagerActivity.class));
        mineTab.setContent(new Intent(this, MineActivity.class));
        // 将选项卡加进TabHost
        tabhost.addTab(homeTab);
        tabhost.addTab(nearbyTab);
     //   tabhost.addTab(servicedemandTab);
        tabhost.addTab(mineTab);
        tabhost.setCurrentTab(0);
        // 设置tabHost切换时动态更改图标
        tabhost.setOnTabChangedListener(new OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {
                tabChanged(tabId);
            }
        });
    }


    private void tabChanged(String tabId) {
        if (tabId.equals("home")) {
            tabhost.setCurrentTabByTag("首页");
        } else if (tabId.equals("nearby")) {
            tabhost.setCurrentTabByTag("附近");
        }
//        else if (tabId.equals("servicedemand")) {
//            tabhost.setCurrentTabByTag("订单");
//        }
        else if (tabId.equals("mine")) {
            tabhost.setCurrentTabByTag("我的");
        }
    }

    // 返回单个选项
    private View createContent(String text, int resid) {
        View view = LayoutInflater.from(this).inflate(R.layout.tabwidget, null, false);
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        ImageView iv_icon = (ImageView) view.findViewById(R.id.img_name);
        tv_name.setText(text);
        iv_icon.setBackgroundResource(resid);
        return view;
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
           // Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }

    }
}
