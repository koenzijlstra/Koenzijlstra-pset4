package com.example.koen.koenzijlstra_pset4;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

    // table name, database name and database version
    public static final String TABLE_NAME = "dbtodo";
    static final String DB_NAME = "dbtodo.db";
    static final int DATABASE_VERSION = 8;

    // set fields of database schema. table collumns
    public static final String _ID = "id";
    public static final String todo = "todo";
    public static final String CHECKED = "checked";

    // constructor
    public DBhelper(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }


    // oncreate
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                todo + " TEXT NOT NULL, " + CHECKED + " BOOLEAN NOT NULL);");

        // ugly written
        ContentValues first = new ContentValues();
        first.put("todo", "First thing to do");
        first.put("checked", Boolean.FALSE);
        db.insert("dbtodo", null, first);

        ContentValues second = new ContentValues();
        second.put("todo", "When done check box to the right");
        second.put("checked", Boolean.FALSE);
        db.insert("dbtodo", null, second);

        ContentValues third = new ContentValues();
        third.put("todo", "Hold a to-do to delete it");
        third.put("checked", Boolean.FALSE);
        db.insert("dbtodo", null, third);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop old table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // create new table
        onCreate(db);
    }
}