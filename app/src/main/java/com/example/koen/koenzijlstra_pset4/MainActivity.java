package com.example.koen.koenzijlstra_pset4;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ListView listView;
    private DBmanager dBmanager;
    private Listadapter listadapter;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.choreinput) ;
        dBmanager = new DBmanager(getApplicationContext());
        listView = (ListView)findViewById(R.id.lvtodo);


        // set content of the listview
        lvcontent(getApplicationContext());

    }

    public void lvcontent (Context context) {
//        String[] todo_ = {"The dishes", "Homework", "Bring Jimmy to school", "Meeting at 21:00"};
//        ListAdapter theadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todo);
//        ListView thelistview = (ListView) findViewById(R.id.lvtodo);
//        thelistview.setAdapter(theadapter);

        List chores = dBmanager.getalltodos();

        ArrayList<TODOobj> allchores = new ArrayList<TODOobj>();

        for (int i = 0; i < chores.size(); i++){
            TODOobj todoobj = (TODOobj) chores.get(i);
            Integer id = todoobj.getId();
            String todo = todoobj.getText();

            allchores.add(new TODOobj(id, todo));
        }

        listadapter = new Listadapter(getApplicationContext(), allchores);
        listView.setAdapter(listadapter);


        // remove gedeelte, komt via lvcontent nu in oncreate
        createlongclicklistener(listView);

//        If the items in your to-do list are stored into an ArrayList, the app’s GUI won’t notice when
//        you add or remove an item from the list. That is, you’ll modify the ArrayList state but the
//        graphical list on the screen won’t update to match. To fix this, you have to call the method
//        notifyDataSetChanged() on your ArrayAdapter to tell it that the underlying array list has
//        changed. To be able to do this, of course, you’ll have to save your ArrayList and your
//        ArrayAdapter as private fields inside your activity. Use a Bundle to save the list’s data
//        during rotation.
    }

    // closes the keyboard that showes up when/after typing. from http://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
    public void closekeys () {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    // when button is clicked, create the chore and close keyboard
    public void createchore (View view) {
        dBmanager.insert(editText.getText().toString());
        lvcontent(getApplicationContext());
        // clear the edittext view
        editText.setText("");
        closekeys();
    }


    public void createlongclicklistener(ListView view) {
        view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                TODOobj chore = (TODOobj) listView.getItemAtPosition(position);

                dBmanager.delete(chore.getId());

                listadapter.remove(chore); // wat gaat hier fout
                listadapter.notifyDataSetChanged();
                return false;
            }
        });

    }
}
