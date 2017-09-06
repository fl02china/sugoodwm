package com.sugoodwaimai.app.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.activity.LoginActivity;
import com.sugoodwaimai.app.activity.TakeawaySubmitOrderActivity;
import com.sugoodwaimai.app.activity.TakeawaySubmitOrderActivity2;
import com.sugoodwaimai.app.adapter.TakeawayOrderFoodAdapter;
import com.sugoodwaimai.app.adapter.TakeawayShopCarAdapter;
import com.sugoodwaimai.app.adapter.TakeawayShopOrderAdapter;
import com.sugoodwaimai.app.application.SugoodApplication;
import com.sugoodwaimai.app.entity.ShopCarProduct;
import com.sugoodwaimai.app.entity.TakeawayShopInfo;
import com.sugoodwaimai.app.entity.TakeawayShopType;
import com.sugoodwaimai.app.entity.TakeawayShopTypeInfo;
import com.sugoodwaimai.app.global.Constant;
import com.sugoodwaimai.app.listener.ViewdisMissListener;

import com.sugoodwaimai.app.util.AmountUtil;
import com.sugoodwaimai.app.util.HttpUtil;
import com.sugoodwaimai.app.util.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import io.realm.Realm;

import static com.sugoodwaimai.app.util.DensityUtil.dip2px;


/**
 * Package :com.android.supermarket.takeaway.fragment
 * Description :
 * Author :Rc3
 * Created at :2017/2/28 16:29.
 */

public class TakeawayShopcarFragment extends Fragment {

    private static final String TAG = TakeawayShopcarFragment.class.getSimpleName();
    RecyclerView mRecyclerView;


    Context mContext;
    ViewdisMissListener mListner;


    XRecyclerView mXRecyclerView;
    View mHeaderView;
    Button mAccount;
    View mView;
    RelativeLayout mRelativeLayout;
    List<TakeawayShopType> mTypeList = new ArrayList<>();
    List<TakeawayShopTypeInfo> mTypeInfolist = new ArrayList<>();
    TakeawayOrderFoodAdapter foodAdapter;
    @BindView(R.id.takeaway_food_discount_price_tv)
    TextView mPriceSum;
    @BindView(R.id.rl_buyCar)
    RelativeLayout rl_buyCar;
    @BindView(R.id.takeaway_food_amount_tv)
    TextView mFoodAmount;
    @BindView(R.id.takeaway_order_foot_rl)
    RelativeLayout mFootlayout;
    Realm mRealm = Realm.getDefaultInstance();
    double priceAll = 0;
    int AmountAll = 0;
    String shopId;
    TakeawayShopInfo.EleBean shop;

    List<ShopCarProduct> mShopCarList;

    public void setListner(ViewdisMissListener listner) {
        mListner = listner;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.takeaway_shop_order_fragment, container, false);
        mContext = getActivity();
        ButterKnife.bind(this, view);
        initView(view);
        mTypeList.clear();
        mTypeInfolist.clear();
        shop = (TakeawayShopInfo.EleBean) getArguments().getSerializable("shop");
        System.out.println("TakeawayShopcarFragmentShop1111:"+shop);
        shopId = getArguments().getString("shopId");
        requestData(shopId);
        return view;
    }

    private void initData(List<TakeawayShopType> typeList) {

      //  typeList.clear();
        foodAdapter = new TakeawayOrderFoodAdapter(mContext, mTypeInfolist,mPriceSum);
        //recyclerview
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        TakeawayShopOrderAdapter adapter = new TakeawayShopOrderAdapter(mContext, typeList);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnitemClickLitener(new TakeawayShopOrderAdapter.OnitemClickListener() {
            @Override
            public void onClick(int position) {
                mTypeInfolist.clear();
                mTypeInfolist.addAll(mTypeList.get(position).getList());
                foodAdapter.notifyDataSetChanged();

            }
        });

        //xRecyclerview

        mTypeInfolist.addAll(mTypeList.get(0).getList());
//        mXRecyclerView.addHeaderView(mHeaderView);

        mXRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    //    mXRecyclerView.setHasFixedSize(true);
        mXRecyclerView.setAdapter(foodAdapter);
        mXRecyclerView.setLoadingMoreEnabled(false);

        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                mXRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {

                mXRecyclerView.loadMoreComplete();
            }
        });

        if (mFoodAmount.getText().equals("0")) {
            mFoodAmount.setVisibility(View.GONE);
        }
        foodAdapter.setOnShopCarClickListener(new TakeawayOrderFoodAdapter.OnShopCarClickListener() {
            @Override
            public void onClick(String name, double price, int num, String productuId,int position) {
                mTypeInfolist.get(position).setCount(num);
                ShopCarProduct shopCar = new ShopCarProduct();
                priceAll =  priceAll+price;
                mFoodAmount.setVisibility(View.VISIBLE);
                AmountAll = AmountAll + num;
                DecimalFormat df = new DecimalFormat("0.00");
                shopCar.setFoodAmount(num);
                shopCar.setFoodName(name);
                shopCar.setProductId(productuId);
                shopCar.setFoodPrice(df.format(Double.parseDouble(price + "")) + "");//df的作用是为了精度
                mShopCarList = SugoodApplication.getInstance().getShopCarProductList();
                boolean isOne = false;
                boolean isONE = false;
                if (mShopCarList.size() == 0) {
                    mShopCarList.add(shopCar);
                    isONE = true;
                } else {
                    for (int i = 0; i < mShopCarList.size(); i++) {
                        if (mShopCarList.get(i).getFoodName().equals(name) && !isONE) {
                            mShopCarList.get(i).setFoodAmount(mShopCarList.get(i).getFoodAmount() + num);
                            mShopCarList.get(i).setFoodPrice((Double.parseDouble(mShopCarList.get(i).getFoodPrice()) + Double.parseDouble(price + "")) + "");
                            isONE = true;
                        }
                    }

                }
                if (!isONE) {
                    mShopCarList.add(shopCar);
                    isONE = true;
                }
                isONE = false;
                double allprice = 0;

                for (int i = 0; i < mShopCarList.size(); i++) {
                    Double a = Double.parseDouble(mShopCarList.get(i).getFoodPrice());
               //  allprice = allprice +  (a*100)/100.0;
                    allprice = allprice +  a;
                  //  Arith.add(allprice,a);
                    System.out.println("aa:"+allprice);

                }
               /// allprice =(int)(allprice*100)/100.0;

                allprice =   AmountUtil.get2Double(allprice,2);
                System.out.println("bb:"+allprice);

                mFoodAmount.setText(mShopCarList.size() + "");
                mPriceSum.setText( allprice + "");
                SugoodApplication.getInstance().setShopCarProductList(mShopCarList);
            }
        });
        rl_buyCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view = LayoutInflater.from(mContext).inflate(R.layout.takeaway_shopcar_popupview, null);
                PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
                popupWindow.setTouchable(true);
                mView.setVisibility(View.VISIBLE);
                mListner.setViewVisble();
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
                Log.i(TAG, "onClick: " + dip2px(mContext, mAccount.getHeight()));
                popupWindow.showAtLocation(mAccount, Gravity.BOTTOM, 0, (dip2px(mContext, mFootlayout.getMeasuredHeight() + 8 + mFootlayout.getPaddingTop() + mFootlayout.getPaddingBottom())));
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        mView.setVisibility(View.GONE);
                        mListner.setViewGone();
                    }
                });
                List<ShopCarProduct> shopCarProducts = SugoodApplication.getInstance().getShopCarProductList();
                Log.e(TAG, "onClick: " + shopCarProducts.toString());
                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.takeaway_shop_car_rv_final);
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(new TakeawayShopCarAdapter(shopCarProducts, mFoodAmount, mPriceSum, mFoodAmount));

            }
        });

        mAccount.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (SugoodApplication.isLogin) {
                                                if (shop != null && !TextUtils.isEmpty(shopId)) {
                                                    if (mShopCarList != null) {
                                                        if (mShopCarList.size() > 0) {
                                                            if (shop.getSinceMoney() <= Double.parseDouble(mPriceSum.getText().toString())) {

                                                                Intent intent = new Intent();
                                                                intent.putExtra("shopId", shopId);
                                                                intent.putExtra("shop", shop);
                                                                intent.putExtra("type", "waimai");
                                                                intent.setClass(getActivity(), TakeawaySubmitOrderActivity2.class);
                                                                startActivity(intent);
//                            getActivity().finish();
                                                            } else {
                                                                System.out.println("aaa"+ Double.parseDouble(mPriceSum.getText().toString()));
                                                                System.out.println("bbb"+shop.getSinceMoney());
                                                                ToastUtil.setToast(getActivity(), "购买金额未达到起送价");
                                                            }
                                                        }
                                                    } else {
                                                        Toast.makeText(mContext, "购物车空", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Log.e(TAG, "onClick: " + shop.toString());
                                                }

                                            } else

                                            {
                                                startActivity(new Intent(getActivity(), LoginActivity.class));
                                            }
                                        }
                                    }

        );

    }

    @Override
    public void onResume() {

        super.onResume();
    }

    private void initView(View view) {
        mXRecyclerView = (XRecyclerView) view.findViewById(R.id.takeaway_shop_type_xrv);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.takeaway_shop_type_rv);
        mAccount = (Button) view.findViewById(R.id.takeaway_shopcar_account);
        mView = view.findViewById(R.id.takeaway_shop_rvs_alpha);
        mRelativeLayout = (RelativeLayout) view.findViewById(R.id.takeaway_order_foot_rl);
        mHeaderView = LayoutInflater.from(mContext).inflate(R.layout.takeaway_shop_coupon_view, (ViewGroup) view, false);
    }


    private void requestData(String shopId) {


        RequestParams params = new RequestParams();
        params.put("shopId", shopId);

        HttpUtil.post(Constant.TAKEAWAY_MENU_LIST_URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String result = new String(responseBody);
                    Log.i(TAG, "onSuccessresult1: " + result);
                    result = "["+result+"]";
                    try {
                        JSONArray array = new JSONArray(result);
                        JSONObject jsonObject = array.getJSONObject(0);

                        JSONArray type = jsonObject.getJSONArray("type");
                        Iterator<String> iterator = type.getJSONObject(0).keys();

                        while (iterator.hasNext()) {

                            String item = iterator.next();
                            Log.i(TAG, "onSuccess: " + item);
                            TakeawayShopType shopType = new TakeawayShopType();
                            shopType.setTypeName(item);
                            JSONArray typeEle = type.getJSONObject(0).getJSONArray(item);
                            Log.e(TAG, "onSuccesstypeEle: " + typeEle.toString());
                            List<TakeawayShopTypeInfo> list = new ArrayList<TakeawayShopTypeInfo>();
                            for (int i = 0; i < typeEle.length(); i++) {
                                JSONObject j = typeEle.getJSONObject(i);
                                TakeawayShopTypeInfo info = new TakeawayShopTypeInfo();
                                info.setDesc(j.getString("desc"));
                                info.setPrice(j.getInt("price") + "");
                                info.setSoldNum(j.getInt("soldNum"));
                                info.setProductId(j.getInt("productId"));
                                info.setPhoto(j.getString("photo"));
                                info.setProductName(j.getString("productName"));
                                list.add(info);
                            }

                            shopType.setList(list);
                            mTypeList.add(shopType);

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            if (mTypeList.size() > 0) {
                                initData(mTypeList);
                            } else {
                                ToastUtil.setToast(getActivity(), "没有获取到该商店的信息");
                            }

                        }
                    });
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

    private String parsePrice(int price) {
        String thePrice = price + "";
        return thePrice.substring(0, thePrice.length() - 2) + "." + thePrice.substring(thePrice.length() - 2);
    }

    private String parsePrice(String thePrice) {

        return thePrice.substring(0, thePrice.length() - 2) + "." + thePrice.substring(thePrice.length() - 2) + "元";
    }

//    public void setCount(){
//
//    }


}
