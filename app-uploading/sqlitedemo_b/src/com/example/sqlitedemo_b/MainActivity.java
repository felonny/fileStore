package com.example.sqlitedemo_b;

import android.support.v7.app.ActionBarActivity;
import android.content.ContentValues;
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
		ContentValues values = new ContentValues();
		values.put("name", "������");
		values.put("sex", "��");
		values.put("age", 19);
		long rowId  = db.insert("usertb", null, values);
		values.clear();
		values.put("name", "������");
		values.put("sex", "��");
		values.put("age", 19);
		db.insert("usertb", null, values);
		values.clear();
		values.put("name", "������");
		values.put("sex", "��");
		values.put("age", 19);
		db.insert("usertb", null, values);
		values.clear();
		values.put("name", "������");
		values.put("sex", "��");
		values.put("age", 19);
		db.insert("usertb", null, values);
		values.clear();
		values.put("name", "������");
		values.put("sex", "��");
		values.put("age", 19);
		db.insert("usertb", null, values);
		values.clear();
		values.put("sex", "Ů");
		
		db.update("usertb", values, "_id>?", new String[]{"3"});
		db.delete("usertb", "name like ?", new String[]{"%��%"});//ɾ�����������д��з����
		Cursor c = db.query("usertb", null, "id>?", new String[]{"0"}, null, null, "name");//���һ�������ǰ���ʲô������
		if(c != null ){
			String[] coloumns = c.getColumnNames();
			while(c.moveToNext()){
				for(String coloumn:coloumns){
					Log.i("info", c.getString(c.getColumnIndex(coloumn)));
				}
			}
			c.close();
		}
		db.close();
	}

	
}
