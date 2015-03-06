package com.example.jamaljamal.homework4.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jamaljamal.homework4.model.TaskListList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jamaljamal on 3/9/15.
 */
public class TaskListDbHelper extends SQLiteOpenHelper {

    // link to DB name in schema class
    private final static String DB_NAME = TaskListContract.DATABASE_NAME;

    // DB version table if needed
    private final static int DB_VERSION = 1;

    // Task List table
    private final static String LIST_TABLE_NAME = TaskListContract.List.TABLE_NAME;
    private final static String LIST_TABLE_ID = TaskListContract.List.ID;
    private final static String LIST_TASK_NAME = TaskListContract.List.TASK_NAME;
    private final static String LIST_TASK_DESCRIPTION = TaskListContract.List.TASK_DESCRIPTION;

    // SQL statement that will be passed to execSQL to create the table
    private final static String LIST_TABLE_CREATE = "CREATE TABLE " +
            LIST_TABLE_NAME + " (" +
            LIST_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            LIST_TASK_NAME + " TEXT, " +
            LIST_TASK_DESCRIPTION + " TEXT" + ");";

    //must override constructor
    public TaskListDbHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create "List" table
        db.execSQL(LIST_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade "List" table
        // drop table if exist
        db.execSQL("DROP TABLE IF EXIST " + LIST_TABLE_NAME);
        // create table again using onCreate
        onCreate(db);
    }

    // insert one record into List table
    public void insertTaskListList(TaskListList taskListList){
        // get reference to writable db
        SQLiteDatabase db = this.getWritableDatabase();

        // create ContentValues to insert data into DB
        ContentValues contentValues = new ContentValues();
        contentValues.put(LIST_TASK_NAME, taskListList.getList_name());
        contentValues.put(LIST_TASK_DESCRIPTION, taskListList.getList_description());

        // insert data to db
        db.insert(LIST_TABLE_NAME, null, contentValues);
        db.close();
    }

    // retrieve all columns with records from List table
    public List<TaskListList> getTaskListList(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<TaskListList> taskListLists = new ArrayList<>();

        Cursor cursor = db.query(LIST_TABLE_NAME,
                TaskListContract.List.PROJECTION,
                null,
                null,
                null,
                null,
                null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            TaskListList taskListList = cursorToTaskListList(cursor);
            taskListLists.add(taskListList);
            cursor.moveToNext();
        }

        cursor.close(); // close cursor always
        db.close();     // close db always

        return taskListLists;
    }

    // transfer cursor to TaskListList object
    public TaskListList cursorToTaskListList(Cursor cursor){
        TaskListList taskListList = new TaskListList();
        taskListList.setId(cursor.getInt(0));
        taskListList.setList_name(cursor.getString(1));
        taskListList.setList_description(cursor.getString(2));

        return taskListList;
    }
}
