package com.sugoodwaimai.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sugoodwaimai.app.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/26 0026.
 */

public class TakeawayCommentFragment2  extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.takeaway_comment_fragment2, container, false);

        return view;
    }
}
