package com.sugoodwaimai.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.entity.TuanGouDetailNew;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/15 0015.
 */

public class TuanGoucomAdapter extends RecyclerView.Adapter<TuanGoucomAdapter.TuanComHolder> {
    Context mContext;
    List<TuanGouDetailNew.TuanBean.DetailsBean.TuanDetailsBean> mList;

    public TuanGoucomAdapter(Context context, List<TuanGouDetailNew.TuanBean.DetailsBean.TuanDetailsBean> list) {
        this.mContext = context;
        this.mList = list;

    }

    @Override
    public TuanComHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tuan_detail_item_item, parent, false);
        return new TuanComHolder(view);
    }

    @Override
    public void onBindViewHolder(TuanComHolder holder, int position) {
        TuanGouDetailNew.TuanBean.DetailsBean.TuanDetailsBean TuanDetailsBean= mList.get(position);
     //   holder.tv_price.setText(TuanDetailsBean.getPrice());
        holder.name.setText(TuanDetailsBean.getComnoName());
        holder.num.setText(TuanDetailsBean.getNumber());
        System.out.println("TuanDetailsBean:"+TuanDetailsBean.toString());
        System.out.println("TuanDetailsBeangetPrice:"+TuanDetailsBean.getPrice());
         holder.price.setText(TuanDetailsBean.getPrice()+"元");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class TuanComHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.num)
        TextView num;
        @BindView(R.id.price)
        TextView price;

        public TuanComHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        View getItemView() {
            return itemView;
        }
    }


}
