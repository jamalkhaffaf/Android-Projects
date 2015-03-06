package com.example.jamaljamal.homework4.data;

import android.provider.BaseColumns;

/**
 * Created by jamaljamal on 3/9/15.
 */
public class TaskListContract implements BaseColumns {
    // database name
    public static final String DATABASE_NAME = "task_list";

    // create tables for the task_list database
    public static final class List{
        // define table name
        public static final String TABLE_NAME = "list";

        // define table columns
        public static final String ID = BaseColumns._ID;
        public static final String TASK_NAME = "task_name";
        public static final String TASK_DESCRIPTION = "task_description";

        public static final String[] PROJECTION = new String[]{
                TaskListContract.List.ID,
                TaskListContract.List.TASK_NAME,
                TaskListContract.List.TASK_DESCRIPTION
        };
    }

}
