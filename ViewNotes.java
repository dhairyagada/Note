package com.example.dhairya.note1;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewNotes extends AppCompatActivity {

    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);

        ListView listView = (ListView) findViewById(R.id.listView1);
        mydb = new DatabaseHelper(this);

        ArrayList<String> id_arraylist = new ArrayList<>();
        ArrayList<String> Title_arraylist = new ArrayList<>();
        ArrayList<String> Note_arraylist=new ArrayList<>();

        Cursor data = mydb.getAllData();

        if (data.moveToFirst()) {
            do {
                id_arraylist.add(data.getString(data.getColumnIndex(mydb.COL_1)));

                Title_arraylist.add(data.getString(data.getColumnIndex(mydb.COL_2)));

                Note_arraylist.add(data.getString(data.getColumnIndex(mydb.COL_3)));


            } while (data.moveToNext());
        }

        ListAdapter la = new NoteListAdapter(ViewNotes.this,

                id_arraylist,
                Title_arraylist,
                Note_arraylist

        );

        listView.setAdapter(la);

        data.close();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
