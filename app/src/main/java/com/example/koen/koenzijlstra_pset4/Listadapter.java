package com.example.koen.koenzijlstra_pset4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.CompoundButton;

import java.util.List;

public class Listadapter extends ArrayAdapter {

    // constructor
    public Listadapter(Context context, List alltodos) {
        super(context, 0, alltodos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // data item at position
        final TODOobj chore = (TODOobj) getItem(position);

        // xx comment xx
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listitem, parent, false);
        }

        // find textview of item
        TextView item = (TextView) convertView.findViewById(R.id.item);
        if (chore != null) {
            item.setText((CharSequence) chore.getText());
        }

        // get checkbox via id, set the looks of the checkbox to its state
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.done);
        checkBox.setChecked(chore.ischecked());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                chore.setChecked(isChecked);
            }
        });

        // return the view of the row
        return convertView;
    }
}
