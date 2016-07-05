package com.example.sqlitedemo_c;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	public DBOpenHelper(Context context, String name ) {
		super(context, name, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override//首次创建数据库时候调用，一般用于数据库建表
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table if not exists usertb(_id integer primary key autoincrement,name text not null,age integer not null,sex text not null)");
		db.execSQL("insert into usertb(name,sex,age)values('张三','女','20')");
	}

	@Override//当数据库版本发生改变后会自动执行
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
