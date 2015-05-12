package edu.uw.aad.mzm.solution.homework4.model;

/**
 * Created by Margaret on 2/23/2015.
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
