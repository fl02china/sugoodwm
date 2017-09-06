package com.sugoodwaimai.app.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.activity.PaySelectActivity;
import com.sugoodwaimai.app.activity.TakeawayShopDetailActivity;
import com.sugoodwaimai.app.adapter.OrderAdapter;
import com.sugoodwaimai.app.application.SugoodApplication;
import com.sugoodwaimai.app.entity.OrderBean;
import com.sugoodwaimai.app.global.Constant;
import com.sugoodwaimai.app.util.HttpUtil;
import com.sugoodwaimai.app.util.JsonUtil;
import com.sugoodwaimai.app.util.MD5Util;
import com.sugoodwaimai.app.util.ToastUtil;
import com.sugoodwaimai.app.view.RecycleViewDivider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class OrderAllFragment extends BaseFragment {
    XRecyclerView mXRecyclerView;

    List<OrderBean> mList;
    Context mContext;
    OrderAdapter adapter;


    @Override
    protected void initLayout() {
        mContext = getActivity();

        mList = new ArrayList<>();
        mXRecyclerView = (XRecyclerView) findViewById(R.id.user_order);

        mXRecyclerView.setHasFixedSize(true);
        mXRecyclerView.addItemDecoration(new RecycleViewDivider(mContext, LinearLayout.VERTICAL, 5, getResources().getColor(R.color.grey)));
        mXRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        adapter = new OrderAdapter(mContext);
        mXRecyclerView.setAdapter(adapter);
        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mXRecyclerView.setNestedScrollingEnabled(true);
                getList();
                mXRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                mXRecyclerView.setNestedScrollingEnabled(true);

                getList();
                mXRecyclerView.loadMoreComplete();

            }
        });
        mXRecyclerView.refresh();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_order_all;
    }

    @Override
    protected int getStatus() {
        return 1;
    }


    private void cancleOrder(final int pos, final String type) {

        final AlertDialog.Builder cancleOrderDialog =
                new AlertDialog.Builder(getActivity());

        cancleOrderDialog.setTitle("取消订单");
        cancleOrderDialog.setMessage("是否继续");
        cancleOrderDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showLoading("");
                        RequestParams params = new RequestParams();
                        params.put("orderId", mList.get(pos).getOrderId());

                        params.put("type",type);
                        HttpUtil.post(Constant.CANCLEORDER, params, new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                super.onSuccess(statusCode, headers, response);
                                Log.e("TUi", "onSuccess: " + response.toString());
                                closeLoading();
                                try {
                                    if (!response.getBoolean("success")) {
                                        ToastUtil.setToast(getActivity(), response.getString("message"));
                                    } else {
                                        ToastUtil.setToast(getActivity(), "取消订单申请成功");
                                        mXRecyclerView.refresh();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                super.onFailure(statusCode, headers, responseString, throwable);
                                Log.e("TUi", "onSuccess: " + responseString);
                                closeLoading();
                                ToastUtil.setToast(getActivity(), "取消订单申请失败");
                            }
                        });
                    }
                });
        cancleOrderDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        cancleOrderDialog.show();


    }

    private void showtuikuan(final int pos, final String type, final String code) {

        final AlertDialog.Builder tuikuanDialog =
                new AlertDialog.Builder(getActivity());

        tuikuanDialog.setTitle("申请退款");
        tuikuanDialog.setMessage("是否继续");
        tuikuanDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tuikuan(pos,type,code);
                    }
                });
        tuikuanDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        tuikuanDialog.show();
    }

    private void tuikuan(int pos,String type,String code) {

        showLoading("");
        RequestParams params = new RequestParams();
        params.put("orderId", mList.get(pos).getOrderId());
        params.put("code", code);
        params.put("type",type);
        params.put("mdKey", MD5Util.getMD5(mList.get(pos).getOrderId()+"goodsolo"));
        HttpUtil.post(Constant.TUIKUAN_URL, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e("TUi", "onSuccess: " + response.toString());
                closeLoading();
                try {
                    if (!response.getBoolean("session")) {
                        ToastUtil.setToast(getActivity(), response.getString("message"));
                    } else {

                        ToastUtil.setToast(getActivity(), "提交退款申请成功");
                        mXRecyclerView.refresh();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e("TUi", "onSuccess: " + responseString);
                closeLoading();
                ToastUtil.setToast(getActivity(), "提交退款申请失败");
            }
        });
    }


    void getList() {

        // String url = "http://test.goodsolo.com/Speed/Speed/My/WaiOrder";//Speed/My/order
        RequestParams params = new RequestParams();
        params.put("userId", SugoodApplication.user.getUserId());


        params.put("status", getStatus());
        params.put("page", "1");
        params.put("pageSize", "100");
        System.out.println("params111:" + params);
        HttpUtil.post(Constant.SUGOODWMORDER, params, new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e("TAG111", "onSuccess: " + response.toString());
                try {
                    mList = JsonUtil.toList(response.getString("list"), OrderBean.class);
                 //   Collections.reverse(mList);
                    adapter.setData(mList);


                    adapter.setOnConnectClickListener(new OrderAdapter.OnConnectClickListener() {

                        @Override
                        public void onOnClick(View view, int position) {
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mList.get(position).getTel()));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    });
                    adapter.setOnLeftClickListener(new OrderAdapter.OnLeftClickListener() {
                        @Override
                        public void onOnClick(View view, int position) {
                            OrderBean order = mList.get(position);
                            switch (order.getStatus()){
                                case 0:
                                    cancleOrder(position,"ele");
                                    tip("取消订单");
                                    break;
                                case 1:
                                    showtuikuan(position,"ele","");

                                    break;
                                case 9:
                                case 2:
                                case 13:
                                    tip("联系骑手");
                                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mList.get(position).getmobile()));
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    break;
                                case 8:
                                    Intent intent2 = new Intent(mContext, TakeawayShopDetailActivity.class);
                                    intent2.putExtra("shopId", String.valueOf(mList.get(position).getShopId()));

                                    mContext.startActivity(intent2);
                                    tip("再来一单");
                                    break;
                            }
                        }
                    });
                    adapter.setOnRightClickListener(new OrderAdapter.OnRightClickListener() {
                        @Override
                        public void onOnClick(View view, int position) {
                            OrderBean order = mList.get(position);
                          switch (order.getStatus()){
                              case 0:
                                  JSONObject body = new JSONObject();
                                  JSONObject json = new JSONObject();
                                  double price= (double) order.getNeedPay()/100;
                                  try {
                                      json.put("needPay",price+"");
                                      body.put("type", "1");
                                      body.put("orderId", order.getOrderId());
                                      body.put("shopName", order.getShopName());
                                      Intent intent = new Intent();
                                      intent.putExtra("shopname", order.getShopName());
                                      intent.putExtra("orderId", order.getOrderId());
                                      intent.putExtra("orderDetails", json.toString());
                                      intent.putExtra("Body", body.toString());
                                      intent.putExtra("type", "1");

                                      intent.putExtra("price",price+"");
                                      //   intent.putExtra("time", price.toString());
                                      intent.setClass(mContext, PaySelectActivity.class);
                                      startActivityForResult(intent, 6);
                                  } catch (JSONException e) {
                                      e.printStackTrace();
                                  }
                                  break;

                              case 8:
                                  tip("评价");
                                  break;

                          }

                        }
                    });
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.e("TAG111", "onFailure: " + errorResponse);
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e("TAG111", "onFailure: " + responseString);
            }
        });


    }
}
