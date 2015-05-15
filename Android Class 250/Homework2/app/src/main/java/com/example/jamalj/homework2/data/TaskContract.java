package com.example.jamalj.homework2.data;

import android.provider.BaseColumns;

/**
 * Created by JamalJ on 5/13/2015.
 */
public class TaskContract implements BaseColumns {
    //Name of the database
    public static final String DATABASE_NAME="tasks";

    //define "Task" table
    public static final class Task{

        // Define table name
        public static final String TABLE_NAME = "task";
        //define columns
        public static final String ID = BaseColumns._ID;
        public static final String TASK_NAME = "task_name";
        public static final String TASK_DESC = "task_desc";

        //define projection for task table
        public static final String[] PROJECTION = new String[]{
                /*0*/ Task.ID,
                /*1*/ Task.TASK_NAME,
                /*2*/ Task.TASK_DESC
        };
    }
}
