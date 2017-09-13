package com.sugoodwaimai.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sugoodwaimai.app.R;
import com.sugoodwaimai.app.application.SugoodApplication;
import com.sugoodwaimai.app.entity.ShopCarProduct;
import com.sugoodwaimai.app.entity.TakeawayShopTypeInfo;
import com.sugoodwaimai.app.global.Constant;
import com.sugoodwaimai.app.util.GlideUtil;
import com.sugoodwaimai.app.view.MaterialDialog;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Package :com.android.supermarket.takeaway.adapter
 * Description :
 * Author :Rc3
 * Created at :2017/3/1 00:54.
 */

public class TakeawayOrderFoodAdapter extends RecyclerView.Adapter<TakeawayOrderFoodAdapter.OrderFoodHolder> {
    private static final String TAG = TakeawayOrderFoodAdapter.class.getSimpleName();
    List<ShopCarProduct> mShopCarProductList;
    List<TakeawayShopTypeInfo> mList;
    Context mContext;
    TagFlowLayout tagFlowLayout1;
    TagFlowLayout tagFlowLayout2;
    String[] attr1 = {"微辣", "特辣", "超级辣", "本身小辣不加辣"};
    String[] attr2 = {"多海鲜酱", "少海鲜酱"};
    String requestA = "";
    String requestB = "";
    int page = 1;
    TextView mPrice;

    public TakeawayOrderFoodAdapter(Context context, List<TakeawayShopTypeInfo> list, TextView mPrice) {
        this.mPrice = mPrice;
        this.mContext = context;
        this.mList = list;
        mShopCarProductList= SugoodApplication.getInstance().getShopCarProductList();
    }

    @Override
    public OrderFoodHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.takeaway_shop_type_item, parent, false);
        return new OrderFoodHolder(view);
    }

    @Override
    public void onBindViewHolder(final OrderFoodHolder holder, final int position) {

        final TakeawayShopTypeInfo info = mList.get(position);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mContext.startActivity(new Intent(mContext, TakeawayFoodDetailActivity.class));
//            }
//        });

        GlideUtil.displayImage(Constant.PHOTOBASEURL + info.getPhoto(), holder.mImageView);
        holder.mFoodName.setText(info.getProductName());
        holder.mDesc.setText(info.getDesc());
        holder.mPrice.setText(info.getPrice());
        holder.mSoldNum.setText(info.getSoldNum());
        holder.takeawayAccount.setText("" + info.getCount());
        holder.takeawayAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page++;
                int count =  mList.get(position).getCount() + 1;

                holder.takeawayAccount.setText("" + count);
                DecimalFormat df = new DecimalFormat("0.00");
                String allPrice = df.format(count * info.getIntPrice()); //6.20   这个是字符串，但已经是我要的两位小数了
                Double price = Double.parseDouble(mPrice.getText().toString().replace("元", "").replace("￥",""))+ info.getIntPrice() ;
                mOnShopCarClickListener.onClick(info.getProductName(), info.getIntPrice(), 1, info.getProductId() + "",position);
                mList.get(position).setCount(count);
//                        String result = allPrice.substring(0, allPrice.length() - 2) + "." + allPrice.substring(allPrice.length() - 2) + "元";
              //  holder.mPrice.setText(allPrice + "元");
                //notifyDataSetChanged();
            }
        });

        holder.takeawayReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mList.get(position).getCount() > 0) {
                    int count = mList.get(position).getCount() - 1;

                    holder.takeawayAccount.setText("" + count);
                    String allPrice = count * info.getIntPrice() + "";
                   // holder.mPrice.setText(allPrice + "元");
                   Double price = Double.parseDouble(mPrice.getText().toString().replace("元", "")) - info.getIntPrice() ;


                    for (int i = 0; i < mShopCarProductList.size(); i++){


                        if ( mShopCarProductList.get(i).getProductId().equals(info.getProductId()+"")){
                            if (count ==0){
                                mShopCarProductList.remove(i);
                                notifyDataSetChanged();
                            }else {
                                mShopCarProductList.get(i).setFoodPrice(allPrice);
                                mShopCarProductList.get(i).setFoodAmount(count);
                            }
                        }

                    }

                    System.out.println("zz:"+mShopCarProductList.toString());
                    SugoodApplication.getInstance().setShopCarProductList(mShopCarProductList);
//                    mShopCarProductList.get(position).setFoodAmount(count);
                    DecimalFormat df = new DecimalFormat("0.00");

                    mPrice.setText( df.format(price) + "");
                    if (mOnShopCarClickListener != null) {
                       // mOnShopCarClickListener.onClick(info.getProductName(), info.getIntPrice(), count, info.getProductId() + "",position);
                    }
                    mList.get(position).setCount(count);
                  //  notifyDataSetChanged();
                }else if (mList.get(position).getCount() == 1){

                }

            }
        });
        //getItemView()
        holder. mImageView .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final LayoutInflater mInflater = LayoutInflater.from(mContext);


                View view = mInflater.inflate(R.layout.takeaway_shopgod_dialog, null);
                final MaterialDialog dialog = new MaterialDialog(mContext)
                        .setContentView(view)
                        .setCanceledOnTouchOutside(true);
                dialog.show();

                final ImageView mImageView = (ImageView) view.findViewById(R.id.imageView_bg);
                GlideUtil.displayImage(Constant.PHOTOBASEURL + info.getPhoto(), mImageView);
                final TextView mPrice = (TextView) view.findViewById(R.id.takeaway_shop_dialog_price);
                final TextView mCount = (TextView) view.findViewById(R.id.takeaway_shop_food_sold_num_tv);
                final TextView mFoodName = (TextView) view.findViewById(R.id.takeaway_shop_dialog_food_name);
                final TextView mDesc = (TextView) view.findViewById(R.id.takeaway_shop_desc);
                final ImageView mAddShopCar = (ImageView) view.findViewById(R.id.takeaway_shop_car_add);
                mDesc.setText(info.getDesc());
                mFoodName.setText(info.getProductName());
                mPrice.setText(info.getPrice());
                mCount.setText("月售"+info.getCount());
                mAddShopCar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnShopCarClickListener.onClick(info.getProductName(), info.getIntPrice(), 1, info.getProductId() + "",position);
                        dialog.dismiss();
                    }
                });

            }
        });
        holder.mChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LayoutInflater mInflater = LayoutInflater.from(mContext);


                View view = mInflater.inflate(R.layout.takeaway_shop_detail_dialog, null);
                final MaterialDialog dialog = new MaterialDialog(mContext)
                        .setContentView(view)
                        .setCanceledOnTouchOutside(true);
                dialog.show();
                page = 1;
                final TextView request = (TextView) view.findViewById(R.id.takeaway_shop_dialog_request_tv);
                final TextView mAmount = (TextView) view.findViewById(R.id.takeaway_amount);
                final TextView mPrice = (TextView) view.findViewById(R.id.takeaway_shop_dialog_price);
                final TextView mFoodName = (TextView) view.findViewById(R.id.takeaway_shop_dialog_food_name);
                final TextView mAdd = (TextView) view.findViewById(R.id.takeaway_add);
                final TextView mReduce = (TextView) view.findViewById(R.id.takeaway_reduce);
                final Button mAddShopCar = (Button) view.findViewById(R.id.takeaway_shop_car_add);
                mAmount.setText("1");
                mFoodName.setText(info.getProductName());
                mPrice.setText(info.getPrice());

                mAddShopCar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnShopCarClickListener.onClick(info.getProductName(), info.getIntPrice() * page, page, info.getProductId() + "",position);
                        dialog.dismiss();
                    }
                });
                mAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        page++;
                        mAmount.setText("" + page);
                        DecimalFormat df = new DecimalFormat("0.00");
                        String allPrice = df.format(page * info.getIntPrice()); //6.20   这个是字符串，但已经是我要的两位小数了
                        // String allPrice = page * info.getIntPrice() + "";


//                        String result = allPrice.substring(0, allPrice.length() - 2) + "." + allPrice.substring(allPrice.length() - 2) + "元";
                        mPrice.setText(allPrice + "元");

                    }
                });

                mReduce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (page > 1) {
                            page--;
                            mAmount.setText("" + page);
                            String allPrice = page * info.getIntPrice() + "";
                            mPrice.setText(allPrice + "元");
                        }

                    }
                });


                tagFlowLayout1 = (TagFlowLayout) view.findViewById(R.id.takeaway_shop_food_tabflowlayout1);
                tagFlowLayout1.setAdapter(new TagAdapter<String>(attr1) {

                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        Log.i(TAG, "getView: " + s);
                        TextView tv = (TextView) mInflater.inflate(R.layout.takeaway_shop_dialog_tab, parent, false);


                        tv.setText(s);
                        return tv;
                    }
                });

                tagFlowLayout1.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {

                        requestA = attr1[position];
                        request.setText(requestA + " " + requestB);
                        return true;
                    }
                });


                tagFlowLayout2 = (TagFlowLayout) view.findViewById(R.id.takeaway_shop_food_tabflowlayout2);
                tagFlowLayout2.setAdapter(new TagAdapter<String>(attr2) {

                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        Log.i(TAG, "getView: " + s);

                        TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.takeaway_shop_dialog_tab, tagFlowLayout2, false);
                        tv.setText(s);

                        return tv;
                    }
                });
                tagFlowLayout2.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
                        requestB = attr2[position];
                        request.setText(requestA + " " + requestB);
                        return true;
                    }
                });


            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class OrderFoodHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.takeaway_reduce)
        Button takeawayReduce;
        @BindView(R.id.takeaway_add)
        Button takeawayAdd;
        @BindView(R.id.takeaway_amount)
        TextView takeawayAccount;
        @BindView(R.id.takeaway_shop_food_img)
        ImageView mImageView;
        @BindView(R.id.takeaway_shop_type_size_btn)
        Button mChoose;

        @BindView(R.id.takeaway_shop_food_name_tv)
        TextView mFoodName;
        @BindView(R.id.takeaway_shop_food_sold_num_tv)
        TextView mSoldNum;
        @BindView(R.id.takeaway_shop_food_price_tv)
        TextView mPrice;
        @BindView(R.id.takeaway_shop_food_description_tv)
        TextView mDesc;
        View view;

        public OrderFoodHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }
        View getItemView() {
            return itemView;
        }
        public View getView() {
            return view;
        }
    }

    public interface OnShopCarClickListener {
        void onClick(String name, double price, int num, String productId,int position);
    }

    OnShopCarClickListener mOnShopCarClickListener;

    public void setOnShopCarClickListener(OnShopCarClickListener onShopCarClickListener) {
        mOnShopCarClickListener = onShopCarClickListener;
    }
}
