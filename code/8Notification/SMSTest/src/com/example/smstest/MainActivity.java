package com.example.smstest;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.telephony.gsm.SmsMessage;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView sender;
	private TextView content;
	private IntentFilter receiveFilter;
	private MessageReceiver messageReceiver;
	
	private EditText to;
	private EditText msgInput;
	private Button send;
	
	private IntentFilter sendFilter;
	private SendStatusReceiver sendStatusReceiver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sender = (TextView)findViewById(R.id.sender);
		content = (TextView)findViewById(R.id.content);
		receiveFilter = new IntentFilter();
		receiveFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
		messageReceiver = new MessageReceiver();
		registerReceiver(messageReceiver, receiveFilter);
		
		to = (EditText)findViewById(R.id.to);
		msgInput = (EditText)findViewById(R.id.msg_input);
		send = (Button)findViewById(R.id.send);
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage
				(to.getText().toString(), null, msgInput.getText().toString(), null, null);
				Intent sentIntent = new Intent("SENT_SMS_ACTION");
				PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, 0, sentIntent, 0);
				smsManager.sendTextMessage(to.getText().toString(), null, msgInput.getText().toString(), pi, null);
			}
		});
		
		sendFilter = new IntentFilter();
		sendFilter.addAction("SENT_SMS_ACTION");
		sendStatusReceiver = new SendStatusReceiver();
		registerReceiver(sendStatusReceiver, sendFilter);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(messageReceiver);
		unregisterReceiver(sendStatusReceiver);
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
	
	class MessageReceiver extends BroadcastReceiver {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Bundle bundle = intent.getExtras();
			Object[] pdus = (Object[])bundle.get("pdus");//提取短信消息
			SmsMessage[] messages = new SmsMessage[pdus.length];
			for(int i = 0; i < messages.length; i++) {
				messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
			}
			String address = messages[0].getOriginatingAddress();//获取发送方号码
			String fullMessage = "";
			for(SmsMessage message : messages) {
				fullMessage += message.getMessageBody();
			}
			sender.setText(address);
			content.setText(fullMessage);
		}
	}
	
	class SendStatusReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if(getResultCode() == RESULT_OK){
				//短信发送成功
				Toast.makeText(MainActivity.this, "Send succeeded", Toast.LENGTH_LONG).show();
			}else {
				//短信发送失败
				Toast.makeText(MainActivity.this, "Send failed", Toast.LENGTH_LONG).show();
			}
		}
	}
}
