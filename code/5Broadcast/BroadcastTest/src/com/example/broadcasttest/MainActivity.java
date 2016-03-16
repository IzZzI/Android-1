package com.example.broadcasttest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private IntentFilter intentFilter;
	private NetworkChangeReceiver networkChangeReceiver;
	
	private LocalReceiver localReceiver;
	private LocalBroadcastManager localBroadcastManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		localBroadcastManager = LocalBroadcastManager.getInstance(this);
		intentFilter = new IntentFilter();
		intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
		networkChangeReceiver = new NetworkChangeReceiver();
		registerReceiver(networkChangeReceiver, intentFilter);
		
		Button button = (Button)findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent("com.example.broadcasttest.LOCAL_BROADCAST");
				/*sendOrderedBroadcast(intent, null);*/
				localBroadcastManager.sendBroadcast(intent);
			}
		});
		intentFilter.addAction("com.example.broadcasttest.LOCAL_BROADCAST");
		localReceiver = new LocalReceiver();
		localBroadcastManager.registerReceiver(localReceiver, intentFilter);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		/*unregisterReceiver(networkChangeReceiver);*/
		localBroadcastManager.unregisterReceiver(localReceiver);
	}
	
	class NetworkChangeReceiver extends BroadcastReceiver {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			ConnectivityManager connectivityManager = 
					(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
			if(networkInfo != null && networkInfo.isAvailable()) {
				Toast.makeText(context, "network is avilable", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(context, "network is unavilable", Toast.LENGTH_LONG).show();
			}
		}
	}
	
	class LocalReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Toast.makeText(context, "received local broadcast", Toast.LENGTH_LONG).show();
		}
	}
}
