package edu.uw.aad.mzm.solution.homework4.data;

import android.provider.BaseColumns;

/**
 * Created by Margaret on 2/23/2015.
 */
public class TasksContract implements BaseColumns {

    public static final String DATABASE_NAME = "tasks";

    /**
     * Define the Task table
     */
    public static final class Task {


        // Define table name
        public static final String TABLE_NAME = "task";

        // Define table columns
        public static final String ID = BaseColumns._ID;
        public static final String TASK_NAME = "task_name";
        public static final String TASK_DESC = "task_desc";

        // Define projection for Task table
        public static final String[] PROJECTION = new String[] {
                /*0*/ TasksContract.Task.ID,
                /*1*/ TasksContract.Task.TASK_NAME,
                /*2*/ TasksContract.Task.TASK_DESC
        };
    }

}
