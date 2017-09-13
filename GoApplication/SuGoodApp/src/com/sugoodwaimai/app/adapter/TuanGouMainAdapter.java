package com.sugoodwaimai.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.activity.ShopDetailActivity;
import com.sugoodwaimai.app.activity.TuanGouDetailActivity;
import com.sugoodwaimai.app.entity.TuanGouShop;
import com.sugoodwaimai.app.global.Constant;
import com.sugoodwaimai.app.util.GlideUtil;
import java.text.DecimalFormat;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/9/11 0011.
 */

public class TuanGouMainAdapter  extends RecyclerView.Adapter<TuanGouMainAdapter.TuanGouShopHolder>{
    Context mContext;
    List<TuanGouShop> mList;

    public TuanGouMainAdapter(Context context, List<TuanGouShop> list) {
        this.mContext = context;
        this.mList = list;


    }

    @Override
    public TuanGouShopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tuangou_main_item, parent, false);


        return new TuanGouShopHolder(view);
    }

    @Override
    public void onBindViewHolder(final TuanGouShopHolder holder, int position) {
        final TuanGouShop takeawayShop = mList.get(position);
        GlideUtil.displayImage(Constant.PHOTOBASEURL + takeawayShop.getLogo(), holder.mImage);
        holder.mName.setText(takeawayShop.getShopName());
        holder.mRatingBar.setRating(takeawayShop.getScore());
        DecimalFormat df = new DecimalFormat("0.00");
        if (Integer.parseInt(takeawayShop.getMin()+"")>1000){
            holder.mDistance.setText(df.format(Double.parseDouble(takeawayShop.getMin()+"")/1000) + "公里");
        }else
        {
            holder.mDistance.setText(takeawayShop.getMin() + "米");
        }


        holder.mSoldNum.setText("月售" + takeawayShop.getSoldNum() + "单");

        holder.junjia.setText("￥" + takeawayShop.getPrice()+"/人");



        System.out.println("aaatakeawayShop:"+takeawayShop.toString());


        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, ShopDetailActivity.class);
                intent.putExtra("shopId", String.valueOf(takeawayShop.getShopId()));
                intent.putExtra("shop", takeawayShop);
                mContext.startActivity(intent);

               // Toast.makeText(mContext, "重构中", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class TuanGouShopHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.takeaway_shop_img)
        ImageView mImage;
        @BindView(R.id.takeaway_shop_name)
        TextView mName;
        @BindView(R.id.takeaway_shop_rating)
        RatingBar mRatingBar;
        @BindView(R.id.takeaway_shop_sold_num)
        TextView mSoldNum;
        @BindView(R.id.junjia)
        TextView junjia;


        @BindView(R.id.takeaway_shop_distance)
        TextView mDistance;

//        @BindView(R.id.takeaway_shop_coupon)
//        TextView mCoupon;

//        @BindView(R.id.takeaway_coupon_ll4)
//        LinearLayout mCouponLayout;

        public TuanGouShopHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        View getItemView() {
            return itemView;
        }
    }
}

