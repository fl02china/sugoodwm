package com.sugoodwaimai.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sugoodwaimai.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/9/5 0005.
 */

public class ProductCollectionFragment2 extends Fragment {
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @BindView(R.id.textView6)
    TextView textView6;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.takeaway_comment_fragment2, container, false);

        unbinder = ButterKnife.bind(this, view);
        textView6.setText("功能开发中~");
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
