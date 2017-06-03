package com.example.dhairya.note1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dhairya on 03-06-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper
{
     public static final String DATABASE_NAME="User_Note";
     public static final String TABLE_NAME="User_Note_Table";
    public static  final String COL_1="ID";
    public static final String COL_2="NOTETITLE";
    public static final String COL_3="NOTEDATA";

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NOTETITLE TEXT,NOTEDATA TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String notetitle,String notedata)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,notetitle);
        contentValues.put(COL_3,notedata);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if (result==-1) return false;
        else return true;
    }
}
