package com.example.listviewtest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	/*private String[] data = {"apple", "banana", "orange", "watermelon", "pear",
			"grape", "pineapple","strawberry", "cherry", "mango"};*/
	private List<Fruit> fruitList = new ArrayList<Fruit>();
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				MainActivity.this, android.R.layout.simple_list_item_1, data);
		ListView listView = (ListView)findViewById(R.id.list_view);
		listView.setAdapter(adapter);*/
		initFruits(); //初始化水果数据
		FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruitList);
		ListView listView = (ListView)findViewById(R.id.list_view);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Fruit fruit = fruitList.get(position);
				Toast.makeText(MainActivity.this, fruit.getName(), Toast.LENGTH_LONG).show();
			}
			
		});
	}

	private void initFruits() {
		// TODO Auto-generated method stub
		Fruit apple = new Fruit("apple", R.drawable.apple);
		fruitList.add(apple);
		Fruit banana = new Fruit("banana", R.drawable.banana);
		fruitList.add(banana);
		Fruit orange = new Fruit("orange", R.drawable.orange);
		fruitList.add(orange);
		Fruit watermelon = new Fruit("watermelon", R.drawable.watermelon);
		fruitList.add(watermelon);
		Fruit pear = new Fruit("pear", R.drawable.pear);
		fruitList.add(pear);
		Fruit grape = new Fruit("grape", R.drawable.grape);
		fruitList.add(grape);
		Fruit pineapple = new Fruit("pineapple", R.drawable.pineapple);
		fruitList.add(pineapple);
		Fruit strawberry = new Fruit("strawberry", R.drawable.strawberry);
		fruitList.add(strawberry);
		Fruit cherry = new Fruit("cherry", R.drawable.cherry);
		fruitList.add(cherry);
		Fruit mango = new Fruit("mango", R.drawable.mango);
		fruitList.add(mango);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
