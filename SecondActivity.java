package com.example.dhairya.note1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



/**
 * Created by dhairya on 31-05-2017
 */

public class SecondActivity extends Activity {

    DatabaseHelper myDb;
    EditText editTitle,editData;
    Button btnSaveData;
    @Override
    protected void onCreate(Bundle savedInstancestate)
    {

        super.onCreate(savedInstancestate);
        setContentView(R.layout.second_layout);
        editTitle=(EditText)findViewById(R.id.EditText_title);
        editData=(EditText)findViewById(R.id.EditText_data);

        btnSaveData=(Button)findViewById(R.id.Button_save);
        myDb=new DatabaseHelper(this);
        AddData();
    }
    public void AddData()
    {
        btnSaveData.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public  void onClick(View v)
                    {
                        boolean isInserted= myDb.insertData(editTitle.getText().toString(),editData.getText().toString());
                        if(isInserted==true) Toast.makeText(SecondActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else Toast.makeText(SecondActivity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void go_back(View view) {
        String button_text;
        button_text = ((Button) view).getText().toString();
        if (button_text.equals("BACK")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }


}