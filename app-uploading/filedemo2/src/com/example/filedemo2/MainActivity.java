package com.example.filedemo2;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	//程序不需要写入全路径，只需要把文件名写上即可，他会默认存入data/data/包名/file/文件名
	private EditText edt;
	private Button btn;
	private TextView contentvalue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edt =  (EditText) findViewById(R.id.editText1);
		btn = (Button) findViewById(R.id.write);
		contentvalue = (TextView) findViewById(R.id.contentvalue);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				WriteFiles(edt.getText().toString());
				contentvalue.setText(readFiles());
				
			}
		});
	}
	//保存文件内容
	private void WriteFiles(String content) {
		// TODO Auto-generated method stub

		try {
			FileOutputStream fos = openFileOutput("a.txt", MODE_PRIVATE);
			fos.write(content.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//读取文件
	public String readFiles(){
		String content = null;
		try {
			FileInputStream fis = openFileInput("a.txt");
			 ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer =  new byte[1024];
			int len = 0;
			while((len = fis.read(buffer))!= -1){
				baos.write(buffer,0,len);
			}
			content = baos.toString();
			fis.close();
			baos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
		
	}

	
}
