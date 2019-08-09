package com.smoothstack.lms.model;

public class Author {

    private String name;
    private String id;


    public Author(){
        this.name = "DNE";
        this.id = "DNE";
    }
    public Author(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}