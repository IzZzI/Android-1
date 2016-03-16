package com.example.activitytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class SecondActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		Log.d("SecondActivity", this.toString());
		Log.d("SecondActivity", "Task id is " + getTaskId());
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.second_layout);
		/*Intent intent = getIntent();
		String data = intent.getStringExtra("extra_data");
		Log.d("SecondActivity",data);*/
		
		Button button2 = (Button)findViewById(R.id.button_2);
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/*// TODO Auto-generated method stub
				Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
				startActivity(intent);*/
				/*Intent intent = new Intent();
				intent.putExtra("data_return", "Hello, FirstActivity");
				setResult(RESULT_OK, intent);
				finish();*/
				/*Intent intent = new Intent(SecondActivity.this, FirstActivity.class);
				startActivity(intent);*/
				Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
				startActivity(intent);
			}
		});	
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("SecondActivity", "onDestroy");
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent = new Intent();
		intent.putExtra("data_return", "Hello, FirstActivity");
		setResult(RESULT_OK, intent);
		finish();
	}
}
