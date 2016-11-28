package com.example.koen.koenzijlstra_pset4;

        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Parcelable;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.EditText;
        import android.widget.ListAdapter;
        import android.widget.ListView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private DBmanager dBmanager;
    private Listadapter listadapter;
    private EditText editText;
    private ArrayList<TODOobj> allchores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find the edittext where user writes his chore. create new database manager, find listview that displays all todos
        editText = (EditText) findViewById(R.id.choreinput);
        dBmanager = new DBmanager(getApplicationContext());
        listView = (ListView) findViewById(R.id.lvtodo);

        if (savedInstanceState != null) {
            allchores = savedInstanceState.getParcelableArrayList("alkey");
            listadapter = new Listadapter(getApplicationContext(), allchores);
            listView.setAdapter(listadapter);
        } else {
            // set content of the listview
            lvcontent(getApplicationContext());
        }
        // start function that deletes item when item is hold
        createlongclicklistener(listView);
    }

//    @Override
//    public void onSavedIntanceState(Bundle savedInstanceState){
//    savedInstanceState.putBoolean("CHECKBOX_STATE", mCheckbox.isChecked());
//    super.onSaveInstanceState(savedInstanceState);

    public void lvcontent (Context context) {
        // retrieve list of all todos
        List chores = dBmanager.getalltodos();

        // new arraylist todo_ objects
        allchores = new ArrayList<>();

        // loop over list, get objects, remember id, string and checked. add to arraylist
        for (int i = 0; i < chores.size(); i++){
            TODOobj todoobj = (TODOobj) chores.get(i);
            boolean checked = todoobj.ischecked();
            Integer id = todoobj.getId();
            String todo = todoobj.getText();
            allchores.add(new TODOobj(id, todo, checked));
        }

        // create a new Listadapter, set as adapter for listview
        listadapter = new Listadapter(getApplicationContext(), allchores);
        listView.setAdapter(listadapter);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelableArrayList("alkey", allchores);
    }

    // when button is clicked, create the chore and close keyboard
    public void createchore (View view) {
        if (editText.getText().toString().isEmpty()){
            // toast
            Toast.makeText(this, "Please enter to-do", Toast.LENGTH_SHORT).show();
        }

        else{
            dBmanager.insert(editText.getText().toString());
            lvcontent(getApplicationContext());
            // clear the edittext view -> the hint is shown again
            editText.setText("");
            closekeys();
        }
    }
    // closes the keyboard that showes up when/after typing. from http://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
    public void closekeys () {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    // set longclicklistner, delete item that was clicked from the database and from the listview
    public void createlongclicklistener(ListView view) {
        view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                TODOobj chore = (TODOobj) listView.getItemAtPosition(position);
                dBmanager.delete(chore.getId());
                listadapter.remove(chore); // still unchecked call :(
                // notify the listadapter that the dataset has been changed
                listadapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}
