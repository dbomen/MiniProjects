package com.example.firstandroidapp;

public class User {

    private int id;
    private String name;
    private int val;

    public User(int id, String name, int val) {

        this.id = id;
        this.name = name;
        this.val = val;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", val=" + val +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}

