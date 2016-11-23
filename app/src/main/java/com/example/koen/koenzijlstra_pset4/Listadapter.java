package com.example.koen.koenzijlstra_pset4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Koen on 22-11-2016.
 */

public class Listadapter extends ArrayAdapter {

//    // context of list
//    private final Context context;
//    // values of list
//    private final List alltodos;

    // constructor
    public Listadapter(Context context, List alltodos) {
        super(context, 0, alltodos);
//        this.context = context;
//        this.alltodos = alltodos;
    }

    private DBmanager dBmanager = new DBmanager(getContext());

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final TODOobj chore = (TODOobj) getItem(position);
        // hier gaat iets fout met type, zou toch moeten kloppen?? zorgt voor chore is null :(

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listitem, parent, false);
        }

        TextView item = (TextView) convertView.findViewById(R.id.item);
        if (chore != null) { // als optie gegeven, is dit handig??
            item.setText((CharSequence) chore.getText());
        }
        // method gettext zorgt wss voor null pointer exception.

//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View row = inflater.inflate(R.layout.activity_main, parent, false);
//
//        ListView lvtodo = (ListView) row.findViewById(R.id.lvtodo);
//        // lvtodo.set(todoList.get(position).getText());
//
//        return rowView;
        return convertView;
    }
}
