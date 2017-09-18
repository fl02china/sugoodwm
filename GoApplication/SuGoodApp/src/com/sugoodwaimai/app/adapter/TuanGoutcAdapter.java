package com.sugoodwaimai.app.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.entity.TuanGouDetailNew;
import com.sugoodwaimai.app.util.FullyLinearLayoutManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/15 0015.
 */

public class TuanGoutcAdapter extends RecyclerView.Adapter<TuanGoutcAdapter.TuanHolder> {
    Context mContext;
    List<TuanGouDetailNew.TuanBean.DetailsBean> mList;

    public TuanGoutcAdapter(Context context, List<TuanGouDetailNew.TuanBean.DetailsBean> list) {
        this.mContext = context;
        this.mList = list;

    }

    @Override
    public TuanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tuan_detail_item, parent, false);
        return new TuanHolder(view);
    }

    @Override
    public void onBindViewHolder(TuanHolder holder, int position) {
        TuanGouDetailNew.TuanBean.DetailsBean l =mList.get(position);
        System.out.println("l.getTuanDetails()"+l.getTuanDetails().toString());
        TuanGoucomAdapter adapter = new TuanGoucomAdapter(mContext, l.getTuanDetails());
        holder.comRecyclerView.setAdapter(adapter);
        //System.out.println("l.getComboName()"+l.getComboName());
        holder.comboName.setText(l.getComboName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class TuanHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.comboName)
        TextView comboName;
        @BindView(R.id.comRecyclerView)
        RecyclerView comRecyclerView;

        public TuanHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            comRecyclerView.setLayoutManager(new FullyLinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        }
//
        View getItemView() {
            return itemView;
        }
    }

}
