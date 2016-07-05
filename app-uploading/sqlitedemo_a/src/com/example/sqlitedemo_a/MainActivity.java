package com.example.sqlitedemo_a;

import android.support.v7.app.ActionBarActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SQLiteDatabase db = openOrCreateDatabase("tyc.db", MODE_PRIVATE, null);
		db.execSQL("create table if not exists usertb(_id integer primary key autoincrement,name text not null,age integer not null,sex text not null)");
		db.execSQL("insert into usertb(name,sex,age)values('张三','女','20')");
		db.execSQL("insert into usertb(name,sex,age)values('李四','女','21')");
		db.execSQL("insert into usertb(name,sex,age)values('王五','男','27')");
		db.execSQL("insert into usertb(name,sex,age)values('赵六','男','17')");
		Cursor c = db.rawQuery("select * from usertb", null);
		if(c !=  null){
			while(c.moveToNext()){
				Log.i("info","_id:"+ c.getInt(c.getColumnIndex("_id")));
				Log.i("info","name:"+ c.getString(c.getColumnIndex("name")));
				Log.i("info","age:"+ c.getInt(c.getColumnIndex("age")));
				Log.i("info","sex:"+ c.getString(c.getColumnIndex("sex")));
				Log.i("info","!!!!!!!!!!!!!!!!!!!!!!");
			}
			c.close();
		}
		db.close();
	}
    
	
}
