package com.example.sharedpreference;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private EditText etusername,etuserpass;
	private CheckBox chk;
	private SharedPreferences pref;
	private Editor editor;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this)
		/*SharedPreferences pref = getSharedPreferences("mypref", MODE_PRIVATE);//第二个参数是这个pref地模式
		Editor editor = pref.edit();
		editor.putString("name", "zhangsan");
		editor.putInt("age", 3);
		editor.putLong("time", System.currentTimeMillis());
		editor.putBoolean("default", true);
		editor.commit();
		editor.remove("default");
		editor.commit();*/
		
		etusername = (EditText) findViewById(R.id.etname);
		etuserpass = (EditText) findViewById(R.id.etpsw);
		chk = (CheckBox) findViewById(R.id.chksave);
		pref = getSharedPreferences("userInfo", MODE_PRIVATE);
		 editor = pref.edit();
		 String name = pref.getString("name", "");
		 if(name == null){
			 chk.setChecked(false);
		 }else{
			 chk.setChecked(true);
			 etusername.setText(name);
		 }
		 
	}
	public void doClick(View v){
		switch(v.getId()){
		case R.id.button:
			String name = etusername.getText().toString();
			String pass = etuserpass.getText().toString();
			if("admi".equals(name)&&"12345".equals(pass)){
				if(chk.isChecked()){
					editor.putString("name", name);
					
					editor.commit();
					
				}else{
					editor.remove(name);
					editor.commit();
				}
				Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(MainActivity.this, "禁止登陆", Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
	}

	
}
