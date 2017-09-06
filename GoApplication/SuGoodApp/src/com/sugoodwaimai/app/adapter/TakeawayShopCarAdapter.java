package com.sugoodwaimai.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.application.SugoodApplication;
import com.sugoodwaimai.app.entity.ShopCarProduct;


import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Package :com.android.supermarket.takeaway.adapter
 * Description :
 * Author :Rc3
 * Created at :2017/3/2 13:40.
 */

public class TakeawayShopCarAdapter extends RecyclerView.Adapter<TakeawayShopCarAdapter.ShopCarHolder> {
    List<ShopCarProduct> mList;
    TextView mAmount;
    TextView mPrice;
    TextView textView;

    public TakeawayShopCarAdapter(List<ShopCarProduct> l, TextView mAmount, TextView mPrice, TextView textView) {
        this.mList = l;
        this.mAmount = mAmount;
        this.mPrice = mPrice;
        this.textView = textView;
    }

    @Override
    public ShopCarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.takeaway_shop_car_rv, parent, false);

        return new ShopCarHolder(view);
    }

    @Override
    public void onBindViewHolder(final ShopCarHolder holder, final int position) {
        ShopCarProduct product = mList.get(position);
        holder.mProductAmount.setText(product.getFoodAmount() + "");
        holder.mProductName.setText(product.getFoodName());
        holder.mProductPrice.setText(product.getFoodPrice());
        holder.mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int page = Integer.parseInt(holder.mProductAmount.getText().toString());
                page++;
                holder.mProductAmount.setText(page + "");
                double price = (Double.parseDouble(holder.mProductPrice.getText().toString())) / (page - 1);
                DecimalFormat df = new DecimalFormat("0.00");

                holder.mProductPrice.setText(df.format(page * price) + "");
                String dfprice = df.format(Double.parseDouble(mPrice.getText().toString().replace("元", "")) + price); //
                mPrice.setText( dfprice+ "");
                mList.get(position).setFoodPrice( df.format(page * price) + "");
                mList.get(position).setFoodAmount(page);
                SugoodApplication.getInstance().setShopCarProductList(mList);
            }
        });
        holder.mReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int page = Integer.parseInt(holder.mProductAmount.getText().toString());

                if (page > 0) {
                    page--;
                    holder.mProductAmount.setText(page + "");
                    double price = (Double.parseDouble(holder.mProductPrice.getText().toString())) / (page + 1);
                    holder.mProductPrice.setText(page * price + "");
//                    Double a = Double.parseDouble(mPrice.getText().toString().replace("元", ""));
//                    Double b = Double.parseDouble(mPrice.getText().toString().replace("元", ""));
//
//                    try {
//                        mPrice.setText( Arith.div(b,price,4) - price + "");
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    }
                    DecimalFormat df = new DecimalFormat("0.00");
                    String allPrice = df.format(Double.parseDouble(mPrice.getText().toString().replace("元", "")) - price); //
                    mPrice.setText(allPrice);
                    mList.get(position).setFoodPrice( df.format(page * price )+ "");
                    mList.get(position).setFoodAmount(page);
                    SugoodApplication.getInstance().setShopCarProductList(mList);
                }
                if (page == 0) {
                    mList.remove(position);
                    notifyDataSetChanged();
                    if (mList.size() == 0) {
                        textView.setVisibility(View.GONE);
                        mPrice.setText("0.0");
                    } else {
                        textView.setText(mList.size() + "");
                    }

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ShopCarHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.takeaway_shop_car_productname)
        TextView mProductName;
        @BindView(R.id.takeaway_shop_car_productprice)
        TextView mProductPrice;
        @BindView(R.id.takeaway_shop_car_productamount)
        TextView mProductAmount;
        @BindView(R.id.takeaway_shop_car_add)
        Button mAdd;
        @BindView(R.id.takeaway_shop_car_reduce)
        Button mReduce;

        public ShopCarHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
