package com.example.jamaljamal.homework4.model;

/**
 * Created by jamaljamal on 3/9/15.
 */
// model type for "List" table in "TaskList" DB
public class TaskListList {
    private int id;
    private String list_name;
    private String list_description;

    public TaskListList(){}

    public TaskListList(String list_name, String list_description){
        this.list_name = list_name;
        this.list_description = list_description;
    }

    public void setId(int id){this.id = id;}
    public void setList_name(String list_name){this.list_name = list_name;}
    public void setList_description(String list_description){this.list_description = list_description;}

    public int getId(){return id;}
    public String getList_name(){return list_name;}
    public String getList_description(){return list_description;}

    @Override
    public String toString(){
        return list_name;
    }
}
