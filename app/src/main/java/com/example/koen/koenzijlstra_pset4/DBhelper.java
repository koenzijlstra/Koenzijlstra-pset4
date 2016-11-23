package com.example.koen.koenzijlstra_pset4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// creating and recreating the database!

public class DBhelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "dbtodo";
    static final String DB_NAME = "dbtodo.db";
    static final int DATABASE_VERSION = 1;

    // set fields of database schema. table collumns
    public static final String _ID = "id";
    public static final String todo = "todo";

//    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + _ID
//            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + todo_ + " TEXT NOT NULL );";


    // constructor
    public DBhelper(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION); }


    // oncreate
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                todo + " TEXT NOT NULL);");
    }

    // onupgrade

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop old table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // create new table
        onCreate(db);
    }

}
