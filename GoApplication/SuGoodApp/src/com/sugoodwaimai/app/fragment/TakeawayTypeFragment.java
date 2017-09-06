package com.sugoodwaimai.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.sugoodwaimai.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Package :com.android.supermarket.takeaway.fragment
 * Description :
 * Author :Rc3
 * Created at :2017/2/27 17:08.
 */

public class TakeawayTypeFragment extends Fragment {


    @BindView(R.id.takeaway_type_2)
    RelativeLayout mMarket;
    OnItemClickListener mListener;
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.takeaway_type_list, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.takeaway_type_1)
    void onFoodClick() {
        mListener.scroll();
        mListener.onClick(4);
    }

    @OnClick(R.id.takeaway_type_2)
    void onMarketClick() {
        mListener.scroll();
        mListener.onClick(1);
    }

    @OnClick(R.id.takeaway_type_3)
    void onFruitClick() {
        mListener.scroll();
        mListener.onClick(3);
    }

    @OnClick(R.id.takeaway_type_4)
    void onLunchteaClick() {
        mListener.scroll();
        mListener.onClick(2);
    }

    @OnClick(R.id.takeaway_type_5)
    void onMeituanClick() {
        mListener.scroll();
        mListener.onClick(5);
    }

    @OnClick(R.id.takeaway_type_6)
    void onDrinkClick() {
        mListener.scroll();
        mListener.onClick(7);
    }

    @OnClick(R.id.takeaway_type_7)
    void onSnackClick() {
        mListener.scroll();
        mListener.onClick(6);
    }

    @OnClick(R.id.takeaway_type_8)
    void onWesternClick() {
        mListener.scroll();
        mListener.onClick(8);
    }


    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    public interface OnItemClickListener {
        void scroll();

        void onClick(int position);
    }
}
