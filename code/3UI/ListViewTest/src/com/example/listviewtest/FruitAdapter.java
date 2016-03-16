package com.example.listviewtest;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FruitAdapter extends ArrayAdapter<Fruit> {
	
	private int resourceID;
	
	public FruitAdapter(Context context, int textViewResourceId,
			List<Fruit> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		resourceID = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Fruit fruit = getItem(position);
		View view;
		ViewHolder viewHolder;	
		if(convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceID, null);
			viewHolder = new ViewHolder();
			viewHolder.fruitView = (ImageView)view.findViewById(R.id.fruit_image);
			viewHolder.fruitName = (TextView)view.findViewById(R.id.fruit_name);
			view.setTag(viewHolder);
		}else {
			view = convertView;
			viewHolder = (ViewHolder)view.getTag();
		}
		viewHolder.fruitView.setImageResource(fruit.getImageID());
		viewHolder.fruitName.setText(fruit.getName());
		/*ImageView fruitImage = (ImageView)view.findViewById(R.id.fruit_image);
		TextView fruitName = (TextView)view.findViewById(R.id.fruit_name);
		fruitImage.setImageResource(fruit.getImageID());
		fruitName.setText(fruit.getName());*/
		return view;
		
	}
	
	class ViewHolder {
		ImageView fruitView;
		TextView fruitName;
	}
}
