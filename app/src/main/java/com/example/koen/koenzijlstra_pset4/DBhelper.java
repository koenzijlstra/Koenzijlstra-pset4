package com.example.koen.koenzijlstra_pset4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

    // table name, database name and database version
    public static final String TABLE_NAME = "dbtodo";
    static final String DB_NAME = "dbtodo.db";
    static final int DATABASE_VERSION = 3;

    // set fields of database schema. table collumns
    public static final String _ID = "id";
    public static final String todo = "todo";
    public static final String CHECKED = "checked";

    // constructor
    public DBhelper(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION); }

    // oncreate
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                todo + " TEXT NOT NULL, " + CHECKED + " BOOLEAN NOT NULL);");
    }

    // onupgrade -> necessary?

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop old table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // create new table
        onCreate(db);
    }
}