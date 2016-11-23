package com.example.koen.koenzijlstra_pset4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBmanager {

    private DBhelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    //
    public DBmanager(Context c){
        dbHelper = new DBhelper(c);
        database = dbHelper.getWritableDatabase();
    }

    // close the database
    public void close(){
        database.close();
    }


    // create object with TODOobj, insert into database
    public void insert (String todostring){
        ContentValues contentValues = new ContentValues();
        contentValues.put("todo", todostring);

        // insert into database
        database.insert("dbtodo", null, contentValues);
    }

    // update check todo_update

    // delete todo_object
    public void delete (int todoid){
        database.delete("dbtodo.db", "id = "+ todoid, null);
    }
    // ?, new String[ ] {String.valueOf(id)} );


    // get all the todo_objects
    public List getalltodos (){
        List alltodos = new ArrayList();

        // Name of the columns we want to select
        String[] todocolumns = new String[] {"id","todo"};

        // Query
        Cursor cursor = database.query("dbtodo", todocolumns, null, null, null, null, null);
        cursor.moveToFirst();

        // add all todos to the arraylist
        while (!cursor.isAfterLast()) {
            TODOobj todo = new TODOobj(cursor.getInt(0), cursor.getString(1));

//            // get values from column
//            todo_.setId(cursor.getInt(0));
//            todo_.setText(cursor.getString(1));  niet meer nodig, al in regel hierboven

            // Add to the arraylist "alltodos"
            alltodos.add(todo);

            // todo_ ? get check?

            // Move to the next column
            cursor.moveToNext();
        }
        return alltodos;
    }

}
