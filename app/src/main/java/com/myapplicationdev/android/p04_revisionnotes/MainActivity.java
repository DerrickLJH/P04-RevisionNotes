package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etContent;
    RadioGroup rg;
    Button btnInsert;
    Button btnShowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etContent = findViewById(R.id.editTextNote);
        rg = findViewById(R.id.radioGroupStars);
        btnInsert = findViewById(R.id.buttonInsertNote);
        btnShowList = findViewById(R.id.buttonShowList);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                int selectedStar = rg.getCheckedRadioButtonId();
                RadioButton rbSelected = findViewById(selectedStar);
                int selected = Integer.parseInt(rbSelected.getText().toString());
                String content = etContent.getText().toString();

                ArrayList<String> nodeContent = db.getNoteContent();
                Boolean match = false;
                for (int i = 0; i < nodeContent.size(); i++ ){
                    if (nodeContent.get(i).equals(etContent.getText().toString())){
                        match = true;

                    }else{
                        match = false;
                    }
                }
                if (!etContent.getText().toString().isEmpty() && match == false){
                    db.insertNote(content,selected);
                    Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(MainActivity.this, "Failed to Insert", Toast.LENGTH_LONG).show();
                }
                db.close();
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });

    }
}
