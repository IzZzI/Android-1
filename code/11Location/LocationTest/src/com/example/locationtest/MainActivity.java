package com.example.locationtest;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static final int SHOW_LOCATION = 0;
	private TextView positionTextView;
	private LocationManager locationManager;
	private String provider;
	
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_LOCATION:
				String currentPositon = (String)msg.obj;
				positionTextView.setText(currentPositon);
				break;

			default:
				break;
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		positionTextView = (TextView)findViewById(R.id.position_text_view);
		locationManager = (LocationManager)
				getSystemService(Context.LOCATION_SERVICE);
		//��ȡ��ǰ���õ�λ���ṩ��
		List<String> providerList = locationManager.getProviders(true);
		if(providerList.contains(LocationManager.GPS_PROVIDER)){
			provider = LocationManager.GPS_PROVIDER;
		}else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
			provider = LocationManager.NETWORK_PROVIDER;
		}else {
			//��ǰû�п��õ�λ���ṩ����toast����
			Toast.makeText
			(this, "No location provider to use ", Toast.LENGTH_LONG).show();
			return;
		}
		Location location = locationManager.getLastKnownLocation(provider);
		if(location != null){
			showLocation(location);
		}
		locationManager.requestLocationUpdates(provider, 5000, 1, locationListener);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(locationManager != null){
			locationManager.removeUpdates(locationListener);
		}
	}
	
	LocationListener locationListener = new LocationListener() {
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			showLocation(location);
		}
	};
	
	private void showLocation(final Location location){
		/*String currentPosition = "latitude is " + location.getLatitude() + "\n" +
				"longitude is " + location.getLongitude();
		positionTextView.setText(currentPosition);*/
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					//��װ����������Ľӿڵ�ַ
					StringBuilder url = new StringBuilder();
					url.append
					("http://maps.googleapis.com/maps/api/geocode/json?latlng=");
					url.append(location.getLatitude()).append(",");
					url.append((location.getLongitude()));
					url.append("&sensor=false");
					
					HttpClient httpClient = new DefaultHttpClient();
					HttpGet httpGet = new HttpGet(url.toString());
					//��������Ϣ��ָ�����ԣ���֤������������
					httpGet.addHeader("Accept-Language", "zh-CN");
					HttpResponse httpResponse = httpClient.execute(httpGet);
					if(httpResponse.getStatusLine().getStatusCode() == 200){
						HttpEntity entity = httpResponse.getEntity();
						String response = EntityUtils.toString(entity, "utf-8");
						JSONObject jsonObject = new JSONObject(response);
						JSONArray resultArray = jsonObject.getJSONArray("results");
						if(resultArray.length() > 0){
							JSONObject subObject = resultArray.getJSONObject(0);
							String address = subObject.getString("formatted_address");
							Message message = new Message();
							message.what = SHOW_LOCATION;
							message.obj = address;
							handler.sendMessage(message);
						}
					}
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}).start();
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
