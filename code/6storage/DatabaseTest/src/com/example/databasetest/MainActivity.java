package com.example.databasetest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button addData;
	private Button updateData;
	private Button deleteData;
	private Button queryData;
	private Button crudData;
	private Button replaceData;
	
	private MyDatabaseHelper dbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);
		Button createDatabase = (Button)findViewById(R.id.create_database);
		createDatabase.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dbHelper.getWritableDatabase();
			}
		});
		
		addData = (Button)findViewById(R.id.add_data);
		addData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				ContentValues values = new ContentValues();
				values.put("name", "The Da Vinci Code");
				values.put("author", "Dan Brown");
				values.put("pages", 454);
				values.put("price", 16.96);
				db.insert("Book", null, values);
				values.clear(); 
				//组装第二条数据
				values.put("name", "The Lost Symbol");
				values.put("author", "Dan Brown");
				values.put("pages", 510);
				values.put("price", 19.95);
				db.insert("Book", null, values);
			}
		});
		
		updateData = (Button)findViewById(R.id.udpate_data);
		updateData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				ContentValues values = new ContentValues();
				values.put("price", 10.99);
				db.update("Book", values, "name = ?", new String[]{"The Da Vinci Code"});
			}
		});
		
		deleteData = (Button)findViewById(R.id.delete_data);
		deleteData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				db.delete("Book", "pages < ?", new String[]{"500"});
			}
		});
		
		queryData = (Button)findViewById(R.id.query_data);
		queryData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				Cursor cursor = db.query("Book", null, null, null, null, null, null);
				if(cursor.moveToFirst()){
					do {
						String name = cursor.getString(cursor.getColumnIndex("name"));
						String author = cursor.getString(cursor.getColumnIndex("author"));
						int pages = cursor.getInt(cursor.getColumnIndex("pages"));
						double price = cursor.getDouble(cursor.getColumnIndex("price"));
						
						Log.d("MainActivity", "book name is " + name);
						Log.d("MainActivity", "book author is " + author);
						Log.d("MainActivity", "book pages is " + pages);
						Log.d("MainActivity", "book price is " + price);
					}while(cursor.moveToNext());
				}
				cursor.close();
			}
		});
		
		//直接用SQL操作数据库
		crudData = (Button)findViewById(R.id.crud_data);
		crudData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
						new String[] {"The Da Vinci Code", "Dan Brown", "454", "16.96" });
				db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
						new String[] {"The Lost Symbol", "Dan Brown", "510", "19.95"});
				
				/*db.execSQL("update Book set price = ? where = ?", 
						new String[]{"10.99", "The Da Vinci Code"});
				
				db.execSQL("delete from Book where pages > ?", new String[]{"500"});
				
				db.rawQuery("select * from Book", null);*/
			}
		});
		
		replaceData = (Button)findViewById(R.id.replace_data);
		replaceData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				db.beginTransaction(); //开启事务
				try{
					db.delete("Book", null, null);
					/*if(true) {
						//手动抛异常，让事物失败
						throw new NullPointerException();
					}*/
					ContentValues values = new ContentValues();
					values.put("name", "Game of Thrones");
					values.put("author", "George Martin");
					values.put("pages", 720);
					values.put("price", 20.85);
					db.insert("Book", null, values);
					db.setTransactionSuccessful();
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					db.endTransaction(); //结束事物
				}
			}
		});
	}
}
