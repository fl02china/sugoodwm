package com.sugoodwaimai.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sugoodwaimai.app.R;
/**
 * 未使用 可修改，可删除
 */

public class FenLeiAdapter4 extends BaseAdapter {

	private LayoutInflater inflater;
	private Context context;
	private String [] content ={"全部","母婴亲子","摄影写真","体检保健","汽车服务","照片冲洗","培训课程","鲜花婚庆","服装定制","配镜","商城购物卡","其他生活"};
	public FenLeiAdapter4(Context context) {
		this.inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return content.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return content[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View layout = inflater.inflate(R.layout.adapter_item_nearby_layout, parent, false);
		return layout;
	}

}
