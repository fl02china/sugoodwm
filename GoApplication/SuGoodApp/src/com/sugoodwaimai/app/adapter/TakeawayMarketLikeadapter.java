package com.sugoodwaimai.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.activity.TakeawayMarketDetailActivity;
import com.sugoodwaimai.app.entity.ShopList;
import com.sugoodwaimai.app.global.Constant;
import com.sugoodwaimai.app.util.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Package :com.sugoodwaimai.app.adapter
 * Description :
 * Author :Rc3
 * Created at :2017/3/28 17:11.
 */

public class TakeawayMarketLikeadapter extends RecyclerView.Adapter<TakeawayMarketLikeadapter.LikeHolder> {
    List<ShopList> mList;
    Context mContext;

    public TakeawayMarketLikeadapter(List<ShopList> list) {
        this.mList = list;

    }

    @Override
    public LikeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.takeaway_market_shop_like_item, null);


        return new LikeHolder(view);
    }

    @Override
    public void onBindViewHolder(LikeHolder holder, int position) {
        final ShopList like = mList.get(position);
        GlideUtil.displayImage(Constant.PHOTOBASEURL + like.getPhoto(), holder.mImage);
        holder.mTitle.setText(like.getTitle());
        holder.mPrice.setText(like.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TakeawayMarketDetailActivity.class);
                intent.putExtra("goodsId", like.getGoodsId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class LikeHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.takeaway_market_like_image)
        ImageView mImage;

        @BindView(R.id.takeaway_market_like_title)
        TextView mTitle;

        @BindView(R.id.takeaway_market_like_price)
        TextView mPrice;

        public LikeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        View getItemView() {
            return itemView;
        }
    }
}
