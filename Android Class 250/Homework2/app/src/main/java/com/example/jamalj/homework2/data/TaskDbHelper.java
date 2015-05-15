package com.example.jamalj.homework2.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.jamalj.homework2.model.Task;
import java.util.ArrayList;
import java.util.List;

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

    //db create
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TASK_TABLE_CREATE);
        Log.i(LOG_TAG, "Creating table with query: " + TASK_TABLE_CREATE);
    }

    //db upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP " + TASK_TABLE_NAME);
        onCreate(db);
    }

    //add a task to the database
    public void addTask(String taskName, String taskDesc) {

        Log.i(LOG_TAG, "Added a task: " + taskName);

        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // Create ContentValues to add data
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASK_ROW_NAME, taskName);
        contentValues.put(TASK_ROW_DESC, taskDesc);

        // Insert data to table
        db.insert(TASK_TABLE_NAME, // table name
                null,
                contentValues);

        // Remember to close the db
        db.close();
    }

    public List<Task> getTasks() {

        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        List<Task> tasks = new ArrayList<Task>();

        Cursor cursor = db.query(TASK_TABLE_NAME,
                TaskContract.Task.PROJECTION,
                null,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Task task= cursorToTask(cursor);
            tasks.add(task);
            cursor.moveToNext();
        }

        cursor.close();
        db.close();

        return tasks;
    }

    //utilize courser to be converted into task object and be used to retrieve data
    private Task cursorToTask(Cursor cursor) {

        Task task = new Task();
        task.setId(cursor.getLong(0));
        task.setName(cursor.getString(1));
        task.setDesc(cursor.getString(2));

        return task;
    }

    //db delete one record
    public int deleteTask(long id) {

        SQLiteDatabase db = this.getWritableDatabase();
        int count = db.delete(TASK_TABLE_NAME, TASK_ROW_ID + " = " + id, null);
        db.close();
        return count;
    }

    //db delete multi records
    public void deleteTasks(ArrayList<Integer> ids) {

        SQLiteDatabase db = this.getWritableDatabase();
        for (long id: ids) {
            db.delete(TASK_TABLE_NAME, TASK_ROW_ID + " = " + id, null);
        }
        db.close();
    }

}
