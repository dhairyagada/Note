package com.example.dhairya.note1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NoteItemClickActivity extends AppCompatActivity {
    public final static String posnvarkey="listitem.position.key.string";
    DatabaseHelper myDb;
    EditText note_item_title,note_item_content;
    Button btnsaveupdate;
    Button btndelete;
    String stringid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=getIntent();
        String message=intent.getStringExtra(posnvarkey);

        setContentView(R.layout.activity_note_item_click);


        myDb=new DatabaseHelper(this);
        final Cursor data1 = myDb.getAllData();

        btnsaveupdate=(Button)findViewById(R.id.cnic_save_btn);
        btndelete=(Button)findViewById(R.id.cnic_delete_btn);
        int c=Integer.valueOf(message);

        data1.moveToFirst();
        for(int x=0;x<c;x++) data1.moveToNext();
        StringBuffer bufferid=new StringBuffer();
        StringBuffer buffertitle=new StringBuffer();
        StringBuffer buffernotecontent=new StringBuffer();

        bufferid.append(data1.getString(0));
        buffertitle.append(data1.getString(1));
        buffernotecontent.append(data1.getString(2));

        stringid=bufferid.toString();



        note_item_title=(EditText)findViewById(R.id.cnic_title_edtxt);
        note_item_title.setText(buffertitle.toString());
        note_item_content=(EditText)findViewById(R.id.cnic_notecontent_edtxt);
        note_item_content.setText(buffernotecontent.toString());

        UpdateData();
        DeleteData();




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
    public void UpdateData(){
        btnsaveupdate.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public  void onClick(View v)
                    {
                        boolean isUpdate=myDb.updateData(stringid,note_item_title.getText().toString(),
                                note_item_content.getText().toString());
                        if(isUpdate==true) Toast.makeText(NoteItemClickActivity.this,"Data Updated",Toast.LENGTH_LONG).show();
                        else Toast.makeText(NoteItemClickActivity.this,"Data Not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void DeleteData(){
        btndelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Integer deletedRows = myDb.deleteData(stringid);
                        if(deletedRows > 0)
                            Toast.makeText(NoteItemClickActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(NoteItemClickActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(NoteItemClickActivity.this, ViewNotes.class);
                        startActivity(intent);
                    }

                }
        );

    }

    @Override
    public void onBackPressed(){
        Intent intent=new Intent(this,ViewNotes.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBodyText = note_item_title.getText().toString()+"\n"+note_item_content.getText().toString();
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,note_item_title.getText().toString());
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                startActivity(Intent.createChooser(sharingIntent, "Shearing Option"));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
