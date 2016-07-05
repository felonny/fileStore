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

	@Override//�״δ������ݿ�ʱ����ã�һ���������ݿ⽨��
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table if not exists usertb(_id integer primary key autoincrement,name text not null,age integer not null,sex text not null)");
		db.execSQL("insert into usertb(name,sex,age)values('����','Ů','20')");
	}

	@Override//�����ݿ�汾�����ı����Զ�ִ��
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
