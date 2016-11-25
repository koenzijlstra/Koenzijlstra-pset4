package com.example.koen.koenzijlstra_pset4;

// create object that consists of an id, a string and a checkbox (checkbox was added later)
public class TODOobj {
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
}
