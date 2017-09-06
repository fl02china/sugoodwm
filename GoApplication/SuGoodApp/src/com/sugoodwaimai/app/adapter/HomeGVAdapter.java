package com.sugoodwaimai.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.activity.FangDaiCalActivity;
import com.sugoodwaimai.app.activity.FunActivity;
import com.sugoodwaimai.app.activity.GoodFoodActivity;
import com.sugoodwaimai.app.activity.H5WebActivity;
import com.sugoodwaimai.app.activity.SameCityActivity;
import com.sugoodwaimai.app.activity.TakeawayMainActivity;
import com.sugoodwaimai.app.activity.TakeawayMarketActivity;
import com.sugoodwaimai.app.activity.TakeawayMarketShopActivity;
import com.sugoodwaimai.app.activity.TakeawayShopDetailActivity;
import com.sugoodwaimai.app.entity.TakeawayShop;
import com.sugoodwaimai.app.util.JsonUtil;
import com.sugoodwaimai.app.util.ToastUtil;

import java.util.List;

/**
 * 首页gridview适配器
 *
 * @author dec
 */
public class HomeGVAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private List<String> dataList;
    private List<Integer> integerList;

    public HomeGVAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public HomeGVAdapter(Context context, List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
        this.inflater = LayoutInflater.from(context);
    }

    public void setDataList(List<Integer> list, List<String> dataList) {
        this.dataList = dataList;
        this.integerList = list;
    }

    @Override
    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.adapter_item_layout_home_gv, parent, false);
            holder.iv = (ImageView) convertView.findViewById(R.id.img_home_gv);
            holder.tv = (TextView) convertView.findViewById(R.id.tv_home_gv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//		holder.iv.setBackgroundResource(resid)
        holder.tv.setText(dataList.get(position));
        holder.iv.setImageResource(integerList.get(position));

        String s = " {\n" +
                "        \"min\": 2028,\n" +
                "        \"logo\": \"/attachs/2017/04/11/thumb_58ec482da2bb4.jpg\",\n" +
                "        \"fanMoney\": 0,\n" +
                "        \"isNew\": 1,\n" +
                "        \"score\": 3,\n" +
                "        \"sinceMoney\": 0,\n" +
                "        \"youTypeid\": \"1\",\n" +
                "        \"photo\": \"/attachs/2017/04/11/thumb_58ec482fdbf2d.jpg\",\n" +
                "        \"distribution\": 30,\n" +
                "        \"sun\": 2,\n" +
                "        \"soldNum\": 16,\n" +
                "        \"discount\": 0,\n" +
                "        \"isOpen\": 1,\n" +
                "        \"amount\": 3,\n" +
                "        \"minAmount\": 100,\n" +
                "        \"monthNum\": 344,\n" +
                "        \"shopName\": \"市场\",\n" +
                "        \"shopId\": 10,\n" +
                "        \"isFan\": 0,\n" +
                "        \"typeId\": \"\",\n" +
                "        \"logistics\": 0,\n" +
                "        \"fullMoney\": 0,\n" +
                "        \"newMoney\": 0,\n" +
                "        \"isPei\": 0\n" +
                "    }";

        final TakeawayShop shop = JsonUtil.toObject(s, TakeawayShop.class);


        // 点击事件
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("房贷计算".equals(dataList.get(position))) {
                    context.startActivity(new Intent(context, FangDaiCalActivity.class));
                } else if ("黄页".equals(dataList.get(position))) {
                    context.startActivity(new Intent(context, SameCityActivity.class));
//						ToastUtil.setToast(context,"正在建设中...");
                } else if ("足疗".equals(dataList.get(position))) {
                    context.startActivity(new Intent(context, FunActivity.class));
                } else if ("美食".equals(dataList.get(position))) {
                    Intent intent = new Intent();
                    intent.putExtra("type", "ms");
                    intent.setClass(context, GoodFoodActivity.class);
                    context.startActivity(intent);
                } else if ("休闲".equals(dataList.get(position))) {
                    Intent intent = new Intent();
                    intent.putExtra("type", "xx");
                    intent.setClass(context, GoodFoodActivity.class);
                    context.startActivity(intent);
                } else if ("婚纱摄影".equals(dataList.get(position))) {
                    Intent intent = new Intent();
                    intent.putExtra("type", "lr");
                    intent.setClass(context, GoodFoodActivity.class);
                    context.startActivity(intent);
                } else if ("超市".equals(dataList.get(position))) {
                    Intent intent = new Intent(context, TakeawayMarketShopActivity.class);
                    intent.putExtra("shopId", "26");
                    context.startActivity(intent);
//					context.startActivity(new Intent(context, SuperMarketMainActivity.class));
//                    ToastUtil.setToast(context, "正在建设中...");
                } else if ("外卖".equals(dataList.get(position))) {
                    context.startActivity(new Intent(context, TakeawayMainActivity.class));
                } else if ("商城".equals(dataList.get(position))) {
                    context.startActivity(new Intent(context, TakeawayMarketActivity.class));
                } else if ("车票".equals(dataList.get(position))) {
//                    Intent intent = new Intent();
//                    intent.putExtra("url", "http://m.elong.com/bus/");
//                    intent.putExtra("title", dataList.get(position));
//                    intent.setClass(context, H5WebActivity.class);
//                    context.startActivity(intent);

                    ToastUtil.setToast(context, "暂未开放");
                } else if ("电影".equals(dataList.get(position))) {
//                    Intent intent = new Intent();
//                    intent.putExtra("url", "http://m.wepiao.com/");
//                    intent.putExtra("title", dataList.get(position));
//                    intent.setClass(context, H5WebActivity.class);
//                    context.startActivity(intent);
                    ToastUtil.setToast(context, "暂未开放");
                } else if ("医疗".equals(dataList.get(position))) {
                    Intent intent = new Intent();
                    intent.putExtra("url", "http://m.haodf.com");
                    intent.putExtra("title", dataList.get(position));
                    intent.setClass(context, H5WebActivity.class);
                    context.startActivity(intent);
                } else if ("酒店".equals(dataList.get(position))) {
                    Intent intent = new Intent();
                    intent.putExtra("url", "http://m.elong.com/");
                    intent.putExtra("title", dataList.get(position));
                    intent.setClass(context, H5WebActivity.class);
                    context.startActivity(intent);
                } else if ("机票".equals(dataList.get(position))) {
                    Intent intent = new Intent();
                    intent.putExtra("url", "http://m.elong.com/flight/");
                    intent.putExtra("title", dataList.get(position));
                    intent.setClass(context, H5WebActivity.class);
                    context.startActivity(intent);
                } else if ("旅游".equals(dataList.get(position))) {
                    Intent intent = new Intent();
                    intent.putExtra("type", "ly");
                    intent.setClass(context, GoodFoodActivity.class);
                    context.startActivity(intent);
                    context.startActivity(intent);
                } else if ("菜市场".equals(dataList.get(position))) {
                    Intent intent = new Intent(context, TakeawayShopDetailActivity.class);
                    intent.putExtra("shopId", "10");
                    intent.putExtra("shop", shop);
                    context.startActivity(intent);
                }else
                {
                    ToastUtil.setToast(context, "暂未开放");
                }
            }
        });


        return convertView;
    }

    static class ViewHolder {
        ImageView iv;
        TextView tv;
    }

}
