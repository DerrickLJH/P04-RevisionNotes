package com.myapplicationdev.android.p04_revisionnotes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import javax.crypto.SealedObject;

public class SecondActivity extends AppCompatActivity {

	ArrayAdapter adapter;
	ListView lv;
	ArrayList<Note> data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		//TODO implement the Custom ListView
		lv = findViewById(R.id.lv);

		DBHelper db = new DBHelper(SecondActivity.this);
		data = db.getAllNotes();
		db.close();

		adapter = new RevisionNotesArrayAdapter(SecondActivity.this, R.layout.row, data);
		lv.setAdapter(adapter);
	}


}
