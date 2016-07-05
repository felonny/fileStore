package com.jiahui.sqlite;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import com.jiahui.model.Person;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SQLiteDemoActivity extends Activity {

	private Button btnAdd;
	private Button btnQueryAll;
	private ListView lvpersons;
	private EditText edtname;
	private EditText edtage;

	private List<Person> persons;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			//接到子线程发回来的数据，完成列表的显示
			List<Map<String, Object>> data = (List<Map<String, Object>>) msg.obj;
			SimpleAdapter simpleAdapter = new SimpleAdapter(
					SQLiteDemoActivity.this, data, R.layout.list_item,
					new String[] { "id", "name", "age" }, new int[] {
							R.id.tvid, R.id.tvname, R.id.tvage });

			lvpersons.setAdapter(simpleAdapter);
		}
	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		btnAdd = (Button) this.findViewById(R.id.btnAdd);
		btnQueryAll = (Button) this.findViewById(R.id.btnQueryAll);
		edtname = (EditText) this.findViewById(R.id.edtname);
		edtage = (EditText) this.findViewById(R.id.edtage);
		lvpersons = (ListView) this.findViewById(R.id.lvpersons);

		persons = new ArrayList<Person>();
		DBHelper dbHelper = new DBHelper(this);

		final SQLiteDatabase database = dbHelper.getWritableDatabase();

		btnAdd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				ContentValues values = new ContentValues();
				values.put("name", edtname.getText().toString());
				values.put("age", edtage.getText().toString());
				long result = database.insert("person", null, values);

				if (result != -1) {
					Toast.makeText(SQLiteDemoActivity.this, "增加数据成功",
							Toast.LENGTH_LONG).show();
				}

			}
		});

		btnQueryAll.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				//查询数据可能花费太多时间交给子线程去做，在Handler完成数据的显示
				myThread thread = new myThread(database);
				thread.start();
			}

		});

	}
	class myThread extends Thread {
		private SQLiteDatabase database;
		public myThread(SQLiteDatabase database) {
			this.database = database;
		}
		public void run() {
			System.out.println("----------");
			Cursor cursor = database.query("person", new String[] { "_id",
					"name", "age" }, null, null, null, null, null);
			while (cursor.moveToNext()) {

				Person person = new Person();
				person.setId(cursor.getInt(cursor.getColumnIndex("_id")));
				person.setName(cursor.getString(cursor.getColumnIndex("name")));
				person.setAge(cursor.getInt(cursor.getColumnIndex("age")));
				persons.add(person);

			}
			List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

			for (int i = 0; i < persons.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();

				map.put("id", persons.get(i).getId());
				map.put("name", persons.get(i).getName());
				map.put("age", persons.get(i).getAge());

				data.add(map);
				System.out.println(persons.get(i));

			}
			Message msg = handler.obtainMessage();
			msg.obj = data;
			handler.sendMessage(msg);
		}
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}