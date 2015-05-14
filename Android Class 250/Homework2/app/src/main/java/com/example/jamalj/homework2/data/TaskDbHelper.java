package com.example.jamalj.homework2.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JamalJ on 5/13/2015.
 */
public class TaskDbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = TaskDbHelper.class.getSimpleName();
    public static final String DB_NAME = TaskContract.DATABASE_NAME;
    public static final int DB_VERSION = 1;

    public static final String TASK_TABLE_NAME = TaskContract.Task.TABLE_NAME;
    public static final String TASK_ROW_ID = TaskContract.Task.ID;
    public static final String TASK_ROW_NAME = TaskContract.Task.TASK_NAME;
    public static final String TASK_ROW_DESC = TaskContract.Task.TASK_DESC;

    public static final String TASK_TABLE_CREATE = "CREATE TABLE " + TASK_TABLE_NAME + " (" +
            TASK_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            TASK_ROW_NAME + " TEXT," +
            TASK_ROW_DESC + " TEXT" +
            " )";

    public TaskDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TASK_TABLE_CREATE);
        log.i(LOG_TAG, "Creating table with query: " + TASK_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP " + TASK_TABLE_NAME);
        onCreate(db);
    }

    //add a task to the database
    public void addTask(String taskName, String taskDesc){

    }

}
