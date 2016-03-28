package com.example.choosepictest;

import java.io.File;
import java.io.IOException;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static final int TAKE_PHOTO = 1;
	public static final int CROP_PHOTO = 2;
	public static final int CHOOSE_PHOTO = 3;	
	
	private Button takePhoto;
	private Button chooseFromAlbum;
	private ImageView picture;
	private Uri imageUri;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		takePhoto = (Button)findViewById(R.id.take_photo);
		picture = (ImageView)findViewById(R.id.picture);
		takePhoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				File outputImage = new File
						(Environment.getExternalStorageDirectory(), "output_image.jpg");
				try {
					if(outputImage.exists()){
						outputImage.delete();
					}
					outputImage.createNewFile();
				}catch(IOException e){
					e.printStackTrace();
				}
				imageUri = Uri.fromFile(outputImage);
				Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				startActivityForResult(intent, TAKE_PHOTO);
			}			
		});
		chooseFromAlbum = (Button)findViewById(R.id.choose_from_album);
		chooseFromAlbum.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent("android.intent.action.GET_CONTENT");
				intent.setType("image/*");
				startActivityForResult(intent, CHOOSE_PHOTO);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case TAKE_PHOTO:
			if(resultCode == RESULT_OK){
				Intent intent = new Intent("com.android.camera.action.CROP");
				intent.setDataAndType(imageUri, "image/*");
				intent.putExtra("scale", true);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				startActivityForResult(intent, CROP_PHOTO);
			}
			break;
		case CROP_PHOTO:
			if(resultCode == RESULT_OK){
				try{
					Bitmap bitmap = BitmapFactory.decodeStream
							(getContentResolver().openInputStream(imageUri));
					picture.setImageBitmap(bitmap);
				}catch (IOException e){
					e.printStackTrace();
				}
			}
			break;
		case CHOOSE_PHOTO:
			if(resultCode == RESULT_OK){
				if(Build.VERSION.SDK_INT >= 19){
					handleImageOnKitKat(data);
				}else {
					handleImageBeforeKitKat(data);
				}
			}
			break;
		default:
			break;
		}
	}
	@TargetApi(19)
	private void handleImageOnKitKat(Intent data) {
		String imagePath = null;
		Uri uri = data.getData();
		if(DocumentsContract.isDocumentUri(this, uri)){
			//如果是document类型的uti, 
			String docId = DocumentsContract.getDocumentId(uri);
			if("com.android.providers.media.documents".equals(uri.getAuthority())){
				String id = docId.split(":")[1];
				String selection = MediaStore.Images.Media._ID + "=" + id;
				imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
			}else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
				Uri contentUri = ContentUris.withAppendedId
						(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
				imagePath = getImagePath(contentUri, null);
			}
		}else if("content".equalsIgnoreCase(uri.getScheme())){
			imagePath = getImagePath(uri, null);
		}
		displayImage(imagePath);
	}
	
	private void handleImageBeforeKitKat(Intent data){
		Uri uri = data.getData();
		String imagePath = getImagePath(uri, null);
		displayImage(imagePath);
	}
	
	private String getImagePath(Uri uri, String selection){
		String path = null;
		Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
		if(cursor != null){
			if(cursor.moveToFirst()){
				path = cursor.getString(cursor.getColumnIndex(Media.DATA));
			}
			cursor.close();
		}
		return path;
	}
	
	private void displayImage(String imagePath){
		if(imagePath != null){
			Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
			picture.setImageBitmap(bitmap);
		}else {
			Toast.makeText(this, "failed to get image", Toast.LENGTH_LONG).show();
		}
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
