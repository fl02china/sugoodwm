package com.sugoodwaimai.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.adapter.ProductCollectionAdapter;
import com.sugoodwaimai.app.application.SugoodApplication;
import com.sugoodwaimai.app.entity.Collection;
import com.sugoodwaimai.app.global.Constant;
import com.sugoodwaimai.app.util.HttpUtil;
import com.sugoodwaimai.app.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Package :com.android.supermarket.user.fragment
 * Description :
 * Author :Rc3
 * Created at :2017/3/10 11:08.
 */

public class ProductCollectionFragment extends Fragment {
    private static final String TAG = ProductCollectionFragment.class.getSimpleName();

    XRecyclerView mXRecyclerView;
    List<Collection> mList = new ArrayList<>();
    ProductCollectionAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_collect_fragment, container, false);
        initView(view);
        initData();

        return view;
    }

    private void requestData() {
        RequestParams params = new RequestParams();
        params.put("userId", SugoodApplication.user.getUserId());
        HttpUtil.post(Constant.PRODUCT_COLLECTION_URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                mList.clear();
                String response =  new String(responseBody) ;
                JSONObject myJsonObject = null;
                try {
                    myJsonObject = new JSONObject( response);
                    response = myJsonObject.getString("list");
                    System.out.println("myJsonObjectlist:"+response.toString());
                    mList.addAll(JsonUtil.toList(response, Collection.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //  String List =  myJsonObject.getString("List") ;


                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }

    private void initData() {

        mXRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mXRecyclerView.setHasFixedSize(true);
        mXRecyclerView.setAdapter(adapter = new ProductCollectionAdapter(getActivity(), mList));
        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                requestData();
                mXRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                mXRecyclerView.loadMoreComplete();
            }
        });
        mXRecyclerView.refresh();

    }

    private void initView(View view) {
        mXRecyclerView = (XRecyclerView) view.findViewById(R.id.product_collection_rv);
    }
}
