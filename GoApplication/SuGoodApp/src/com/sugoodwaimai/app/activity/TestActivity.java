package com.sugoodwaimai.app.activity;

import android.app.Activity;
import android.os.Bundle;

import com.sugoodwaimai.app.R;


public class TestActivity extends Activity {
	//美团开放API接口
	private final String URI="http://www.meituan.com/api/v2/beijing/deals";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
	}
}
