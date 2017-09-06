package com.sugoodwaimai.app.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sugoodwaimai.app.entity.OrderBean;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public abstract class BaseOrderViewHolder extends RecyclerView.ViewHolder {
    public BaseOrderViewHolder(View itemView,int type) {
        super(itemView);
    }
    public abstract void bindHolder(OrderBean order);
}
