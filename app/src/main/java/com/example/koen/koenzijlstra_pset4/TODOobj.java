package com.example.koen.koenzijlstra_pset4;

// create object that consists of an id and a string
public class TODOobj {
    private int id;
    private String todo;

    public TODOobj(Integer id, String todo){
        this.id = id;
        this.todo = todo;
    }

    public String getText() {
        return todo;
    }

    public void setText(String todo) {
        this.todo = todo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
