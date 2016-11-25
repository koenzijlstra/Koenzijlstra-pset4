package com.example.koen.koenzijlstra_pset4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

// in this class the dbhelper is initialized, crud methods are defined
public class DBmanager {

    private DBhelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    // construct new dbhelper, get writable database
    public DBmanager(Context c){
        dbHelper = new DBhelper(c);
        database = dbHelper.getWritableDatabase();
    }

//    // close the database (not used yet, even necessary??)
//    public void close(){
//        database.close();
//    }

    // create todo_object , insert into database
    public void insert (String todostring){
        ContentValues contentValues = new ContentValues();
        contentValues.put("todo", todostring);
        contentValues.put("checked", Boolean.FALSE);

        // insert into database
        database.insert("dbtodo", null, contentValues);
    }

    // update check todo_update nodig??

    // delete todo_object from database
    public void delete (int todoid){
        database.delete("dbtodo", "id = "+ todoid, null);
    }
    // maybe use: ?, new String[ ] {String.valueOf(id)} );

    // get all the todo_objects
    public List getalltodos (){
        // create new arraylist
        List alltodos = new ArrayList();

        // columns we want to know, used by cursor
        String[] todocolumns = new String[] {"id","todo", "checked"};

        // get values of the defined columns per row, cursor starts at first todo_
        Cursor cursor = database.query("dbtodo", todocolumns, null, null, null, null, null);
        cursor.moveToFirst();

        // get values, create new todo_object, add all todos to the arraylist
        while (!cursor.isAfterLast()) {
            TODOobj todo = new TODOobj(cursor.getInt(0), cursor.getString(1), (cursor.getInt(2) == 1));

            // Add to the arraylist "alltodos"
            alltodos.add(todo);

            // see if todo_ is checked
            todo.ischecked();
            // Move to the next column
            cursor.moveToNext();
        }
        return alltodos;
    }
}
