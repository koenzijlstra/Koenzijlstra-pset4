package com.example.koen.koenzijlstra_pset4;

import android.os.Parcel;
import android.os.Parcelable;

// create object that consists of an id, a string and a checkbox (checkbox was added later)
public class TODOobj implements Parcelable{
    private int id;
    private String todo;
    // boolean -> checked will be true, unchecked false
    private boolean checked;

    // constructor
    public TODOobj(Integer id, String todo, boolean checked){
        this.id = id;
        this.todo = todo;
        this.checked = checked;
    }

    // returns the todo_ string
    public String getText() {
        return todo;
    }

    // getId returns id of object
    public int getId() {
        return id;
    }

    // sets id of object
    public void setId(int id) {
        this.id = id;
    }

    // true if box is checked, false if unchecked
    public boolean ischecked(){return checked;}

    public void setChecked(boolean checked) {
        this.checked = checked;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.todo);
        dest.writeByte(this.checked ? (byte) 1 : (byte) 0);
    }

    protected TODOobj(Parcel in) {
        this.id = in.readInt();
        this.todo = in.readString();
        this.checked = in.readByte() != 0;
    }

    public static final Creator<TODOobj> CREATOR = new Creator<TODOobj>() {
        @Override
        public TODOobj createFromParcel(Parcel source) {
            return new TODOobj(source);
        }

        @Override
        public TODOobj[] newArray(int size) {
            return new TODOobj[size];
        }
    };
}
