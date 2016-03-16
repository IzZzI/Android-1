package com.example.uibestpractice;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MsgAdapter extends ArrayAdapter<Msg> {

	private int resourceID;
	
	public MsgAdapter(Context context, int textViewResourceId, List<Msg> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		this.resourceID = textViewResourceId;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Msg msg = getItem(position);
		View view;
		ViewHolder viewHolder;
		if(convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceID, null);
			viewHolder = new ViewHolder();
			viewHolder.leftLayout = (LinearLayout)view.findViewById(R.id.left_layout);
			viewHolder.rightLayout = (LinearLayout)view.findViewById(R.id.right_layout);
			viewHolder.leftMsg = (TextView)view.findViewById(R.id.left_msg);
			viewHolder.rightMsg = (TextView)view.findViewById(R.id.right_msg);
			view.setTag(viewHolder);
		}else {
			view = convertView;
			viewHolder = (ViewHolder)view.getTag();
		}
		if(msg.getType() == Msg.TYPE_RECEIVED) {
			//收到消息显示左边布局
			viewHolder.leftLayout.setVisibility(View.VISIBLE);
			viewHolder.rightLayout.setVisibility(View.INVISIBLE);
			viewHolder.leftMsg.setText(msg.getContent());
		}else if(msg.getType() == Msg.TYPE_SENT) {
			viewHolder.leftLayout.setVisibility(View.INVISIBLE);
			viewHolder.rightLayout.setVisibility(View.VISIBLE);
			viewHolder.rightMsg.setText(msg.getContent());
		}
		return view;
	}
	
	private class ViewHolder {
		LinearLayout leftLayout;
		LinearLayout rightLayout;
		
		TextView leftMsg;
		TextView rightMsg;
	}

}
