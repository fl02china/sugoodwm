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
public class FenLeiAdapter3 extends BaseAdapter {

	private LayoutInflater inflater;
	private Context context;
	private String [] content ={"全部","电影","KTV","温泉","洗浴/汗蒸","足疗按摩","景点郊游","游乐园","运动健身","滑雪","采摘","游泳","桌游/电玩","密室逃脱","咖啡酒吧","演出赛事","DIY手工","真人CS","4D/5D电影","其他娱乐"};
	public FenLeiAdapter3(Context context) {
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
