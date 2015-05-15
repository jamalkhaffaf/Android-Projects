package com.example.jamalj.homework2.model;

/**
 * Created by JamalJ on 5/14/2015.
 */
public class Task {

    private long id;
    private String name;
    private String desc;

    public Task() {

    }
    public Task(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return name;
    }
}
