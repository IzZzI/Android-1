package com.example.notificationtest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button sendNotice;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sendNotice = (Button)findViewById(R.id.send_notice);
		NotificationListener listener = new NotificationListener();
		sendNotice.setOnClickListener(listener);
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
	
	class NotificationListener implements OnClickListener{

		@SuppressWarnings("deprecation")
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.send_notice:
				NotificationManager manager = (NotificationManager)
				getSystemService(NOTIFICATION_SERVICE);
				Notification notification = new Notification(R.drawable.ic_launcher, 
						"This is ticker text", System.currentTimeMillis());
				Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
				PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent,
						PendingIntent.FLAG_CANCEL_CURRENT);
				notification.setLatestEventInfo(MainActivity.this, "This is content title", 
						"This is content text", pi);
				manager.notify(1, notification);
				break;

			default:
				break;
			}
		}
		
	}
}
