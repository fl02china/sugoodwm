package com.sugoodwaimai.app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.application.SugoodApplication;
import com.sugoodwaimai.app.entity.ShopCarProduct;
import com.sugoodwaimai.app.entity.TakeawayShopInfo;
import com.sugoodwaimai.app.entity.UserAddress;
import com.sugoodwaimai.app.entity.Wxcont;
import com.sugoodwaimai.app.global.Constant;
import com.sugoodwaimai.app.util.HttpUtil;
import com.sugoodwaimai.app.util.JsonUtil;
import com.sugoodwaimai.app.util.ToastUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public class TakeawaySubmitOrderActivity2 extends BaseActivity {
    UserAddress userAddress;
    String mRemark;
    TakeawayShopInfo.EleBean shop;
    Double price = 0.00;
    JSONObject json;
    RecyclerView mRv;
//    @BindView(R.id.takeaway_submit_order_back)
//    ImageView takeawaySubmitOrderBack;
    @BindView(R.id.takeaway_submit_order_text_tv)
    TextView takeawaySubmitOrderTextTv;
    @BindView(R.id.takeaway_submit_order_header)
    RelativeLayout takeawaySubmitOrderHeader;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.choose_address)
    RelativeLayout chooseAddr;
    @BindView(R.id.address_line)
    LinearLayout addressline;

    @BindView(R.id.show_address)
    LinearLayout showaddress;
    @BindView(R.id.shop_name)
    TextView shopName;
    @BindView(R.id.submit_rv)
    RecyclerView submitRv;
    @BindView(R.id.takeaway_favorable1)
    TextView takeawayFavorable1;
    @BindView(R.id.takeaway_favorable2)
    TextView takeawayFavorable2;
    @BindView(R.id.tv_allprice)
    TextView tvAllprice;
    @BindView(R.id.tv_peisong)
    TextView tvPeisong;
    @BindView(R.id.submit_remark)
    TextView submitRemark;
    @BindView(R.id.takeaway_rl)
    RelativeLayout takeawayRl;
    @BindView(R.id.tv_needPay)
    TextView tvNeedPrice;
    @BindView(R.id.remomey)
    TextView remomey;

    @BindView(R.id.no_favorable)
    TextView no_favorable;

    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private Wxcont wxcont;
    private StringBuffer sb;
    private Map<String, String> resultunifiedorder;
    private PayReq req;
    private final IWXAPI msgApi = WXAPIFactory.createWXAPI(this, null);
    private FinshReceiver mFinsh;
    Context mContext;
    List<ShopCarProduct> shopCarList = new ArrayList<>();
    boolean isnew;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.takeaway_submit_order_activity2);
        ButterKnife.bind(this);
        mContext = this;
        sb = new StringBuffer();
        req = new PayReq();
        json = new JSONObject();
        setDefAddr();//默认地址
        initView();
        initData();

        mFinsh = new FinshReceiver();
        registerReceiver(mFinsh, new IntentFilter("FinishActivity"));
    }

    //广播接收事件
    private class FinshReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    }


    @OnClick(R.id.takeaway_submit_order_text_tv)
    void onBack() {
        finish();
    }
    @OnClick(R.id.btn_submit)
    void onSubmit() {

        if (userAddress == null) {
            ToastUtil.setToast(TakeawaySubmitOrderActivity2.this, "请添加地址");
            return;
        }
        //showLoading("");
        //aliPay();

        goPay();
    }

    private void goPay(){
        try {
            String type = getIntent().getStringExtra("type");
            if (type.equals("shop")) {
                json.put("type", "3");
            } else if (type.equals("waimai")) {
                json.put("type", "1");
            } else {
                json.put("type", "2");
            }
            json.put("sign", "1");
            json.put("message",submitRemark.getText().toString());
            showLoading("");
            RequestParams params = new RequestParams();
            params.put("orderDetails", json.toString());
            params.put("sign", "1");
            Log.e("TAA" +
                    "", "onClick: " + json.toString().toString());


//HttpUtil.post(Constant.SUGOODALIPAY, params, new JsonHttpResponseHandler()

            HttpUtil.post((Constant.SUGOODALIPAY), params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    Log.e("TAA", "onSuccess: " + response.toString());
                    closeLoading();
                    try {
                        if (response.getBoolean("success")) {
                            Log.e("TAG2222", "onSuccess: " + response.toString());
                            String orderid =response.getString("orderId");

                            JSONObject body = new JSONObject();

                            body.put("type",json.getString("type"));
                            body.put("orderId",orderid);
                            body.put("shopName", shop.getShopName());
                            Intent intent = new Intent();
                            intent.putExtra("shopname", shop.getShopName());
                            intent.putExtra("orderId", orderid);
                            intent.putExtra("orderDetails", json.toString());
                            intent.putExtra("Body", body.toString());
                            intent.putExtra("type", json.getString("type"));
                            intent.putExtra("price", price.toString());
                            //   intent.putExtra("time", price.toString());
                            intent.setClass(mContext, PaySelectActivity.class);


                            startActivityForResult(intent, 6);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    Log.e("TAA", "onFailure: " + responseString);
                    closeLoading();
                }
            });



        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    @OnClick(R.id.address_line)
    void onAddr() {
        startActivityForResult(new Intent(mContext, TakeawayAddressActivity.class), 0);
    }
    @OnClick(R.id.takeaway_rl)
    void onRemark() {
        Intent intent = new Intent();
        intent.putExtra("remark", submitRemark.getText().toString());
        intent.setClass(mContext, TakeawayRemarkActivity.class);
        startActivityForResult(intent, 1);
    }
    @Override
    protected void onDestroy() {
        this.unregisterReceiver(mFinsh);
        super.onDestroy();
    }

    private void initData() {
        RequestParams params = new RequestParams();
        params.put("userId", SugoodApplication.user.getUserId());
        params.put("shopId", shop.getShopId());

        HttpUtil.post(Constant.SUGOODISNEW, params, new JsonHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                     isnew = response.getBoolean("success");
                    CountPrice(isnew);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });



    }



    private void setDefAddr() {
        RequestParams params = new RequestParams();
        params.put("userId", SugoodApplication.user.getUserId());
        HttpUtil.post(Constant.SUGOODADDR, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                Log.e("TAG", "onSuccess: " + response.toString());
                List<UserAddress> list;

                list = JsonUtil.toList(response.toString(), UserAddress.class);
                if (list.size()>0){
                    for(int i=0; i<list.size(); i++){
                       if ( list.get(i).getDefaults().equals("1")){
                           userAddress =list.get(i);
                       }
                    }
                    if (userAddress==null){
                        userAddress=list.get(0);
                    }
                    showaddress.setVisibility(View.VISIBLE);
                    chooseAddr.setVisibility(View.GONE);
                    tvAddress.setText("地址："+userAddress.getAreaStr() + userAddress.getInfo());
                    tvName.setText(userAddress.getXm() + "  " + userAddress.getTel());
                    try {
                        json.put("addrId", userAddress.getId());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (resultCode == 111) {

                userAddress = (UserAddress) data.getSerializableExtra("address");
                showaddress.setVisibility(View.VISIBLE);
                chooseAddr.setVisibility(View.GONE);
                tvAddress.setText("地址："+userAddress.getAreaStr() + userAddress.getInfo());
                tvName.setText(userAddress.getXm() + "  " + userAddress.getTel());
                try {
                    json.put("addrId", userAddress.getId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } else if (resultCode == 110) {
                userAddress = null;
                showaddress.setVisibility(View.GONE);
                chooseAddr.setVisibility(View.VISIBLE);
                tvName.setText("");
                tvAddress.setText("");
            }else if(resultCode ==133) {
                finish();
            }

            else {

                mRemark = data.getStringExtra("remark");
                submitRemark.setText(data.getStringExtra("remark"));
            }

        }
    }
    private void CountPrice(boolean isnew) {

        DecimalFormat df = new DecimalFormat("0.00");
        int count = 0;
        double allp = 0.0;
        for (int i = 0; i < shopCarList.size(); i++) {
            price = price + Double.parseDouble(shopCarList.get(i).getFoodPrice());
            allp = allp + Double.parseDouble(shopCarList.get(i).getFoodPrice());
            count = count + shopCarList.get(i).getFoodAmount();
        }
        tvAllprice.setText("￥" + df.format(price));
        if (shop.getFullMoney() > 0&&(allp-Double.parseDouble(shop.getFullMoney()+"")/100>=0)) {
            price = price -Double.parseDouble(shop.getNewMoney()+"")/100;
            takeawayFavorable1.setVisibility(View.VISIBLE);
            no_favorable.setVisibility(View.GONE);
            takeawayFavorable1.setText("满"+Double.parseDouble(shop.getFullMoney()+"")/100+"元减"+ Double.parseDouble(shop.getNewMoney()+"")/100+"元");
        }

        if (shop.getFullMoney()!=0){

        }
        Log.e("aaaaa", "isnew: "+isnew+shop.getJianDuos());
        if (shop.getIsNew()==1){
            if (isnew&&(price>( Double.parseDouble(shop.getJianDuos()+"")/100))) {

                no_favorable.setVisibility(View.GONE);
                takeawayFavorable2.setVisibility(View.VISIBLE);
                takeawayFavorable2.setText("新客户下单立减："+ Double.parseDouble(shop.getJianDuos()+"")/100+"元");

                price = price - Double.parseDouble(shop.getJianDuos()+"")/100;
            }else {
                takeawayFavorable2.setVisibility(View.GONE);
            }

        }

       
//        if (!TextUtils.isEmpty(shop.getTypeId())) {
//            if (shop.getTypeId().equals("1")) {
//                if (price > shop.getMinAmount()) {
//                    price = price - shop.getAmount();
//                }
//            }
//        }


        tvNeedPrice.setText("￥" +  df.format(price + shop.getLogistics()));
     //   allp = allp;
        price = price + shop.getLogistics();
        if ((allp + shop.getLogistics() - price )!=0){

            remomey.setText("已优惠￥"+ df.format(allp - price ));
        }else
        {
            remomey.setText("已优惠￥0元");
        }


        try {
            json.put("userId", SugoodApplication.user.getUserId());
            if (userAddress != null) {
                json.put("addrId", userAddress.getId());
            }
            json.put("logistics", shop.getLogistics());
            json.put("shopId", shop.getShopId());
            json.put("orderNum", count);
            json.put("newMoney",  df.format(allp+ shop.getLogistics() - price) );
            json.put("totalPrice", allp);
            json.put("needPay", price);
            json.put("message", mRemark);

            JSONArray array = new JSONArray();
            for (int i = 0; i < shopCarList.size(); i++) {
                JSONObject json = new JSONObject();
                json.put("productId", shopCarList.get(i).getProductId());
                json.put("num", shopCarList.get(i).getFoodAmount());
                json.put("price", shopCarList.get(i).getFoodPrice());
                array.put(json);
            }
            json.put("details", array);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void initView() {
        shop = (TakeawayShopInfo.EleBean) getIntent().getSerializableExtra("shop");

        shopCarList = SugoodApplication.getInstance().getShopCarProductList();

        shopName.setText(shop.getShopName());
        tvPeisong.setText( ("￥" +shop.getLogistics()));
        submitRv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        submitRv.setAdapter(new SubmitAdapter());


    }

    class SubmitAdapter extends RecyclerView.Adapter<SubmitAdapter.MyHolder> {

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.submit_rv_item, parent, false));
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            for (int i = 0; i < shopCarList.size(); i++) {
                Log.i("TAG1", "initView: " + shopCarList.get(i).getFoodName());
            }
            ShopCarProduct product = shopCarList.get(position);
            holder.mShop.setText(product.getFoodName());
            holder.mProduct.setText("x" + product.getFoodAmount());
            holder.mNum.setText("￥" + product.getFoodPrice());
        }

        @Override
        public int getItemCount() {
            return shopCarList.size();
        }

        public class MyHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.submit_shop)
            TextView mShop;
            @BindView(R.id.submit_product)
            TextView mProduct;
            @BindView(R.id.product_mun)
            TextView mNum;

            public MyHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

}