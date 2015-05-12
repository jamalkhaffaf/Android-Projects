package edu.uw.aad.mzm.solution.homework4.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.uw.aad.mzm.solution.homework4.model.Task;

/**
 * Created by Margaret on 2/23/2015.
 */
public class TaskDbHelper extends SQLiteOpenHelper {

    private final static String LOG_TAG = TaskDbHelper.class.getSimpleName();

    // Database name
    private final static String DB_NAME = TasksContract.DATABASE_NAME;

    // Database version
    private final static int DB_VERSION = 1;

    // Task table
    private final static String TASK_TABLE_NAME = TasksContract.Task.TABLE_NAME;
    private final static String TASK_ROW_ID = TasksContract.Task.ID;
    private final static String TASK_ROW_NAME = TasksContract.Task.TASK_NAME;
    private final static String TASK_ROW_DESC = TasksContract.Task.TASK_DESC;

    // SQL statement to create the Task table
    private final static String TASK_TABLE_CREATE =
            "CREATE TABLE " + TASK_TABLE_NAME + " (" +
                    TASK_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    TASK_ROW_NAME + " TEXT, " +
                    TASK_ROW_DESC + " TEXT" + ");";

    public TaskDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create Task table
        db.execSQL(TASK_TABLE_CREATE);
        Log.i(LOG_TAG, "Creating table with query: " + TASK_TABLE_CREATE);

    }

    /**
     * Handle DB upgrade
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TASK_TABLE_NAME);
        onCreate(db);
    }

    /**
     * Add a task to DB
     * @param taskName
     * @param taskDesc
     */
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
                TasksContract.Task.PROJECTION,
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

        cursor.close(); // close the cursor
        db.close();     // close the db

        return tasks;
    }

    /**
     * Convert a cursor to a Task object
     * @param cursor
     * @return
     */
    private Task cursorToTask(Cursor cursor) {

            Task task = new Task();
            task.setId(cursor.getLong(0));
            task.setName(cursor.getString(1));
            task.setDesc(cursor.getString(2));
        
            return task;
    }

    /**
     * Delete a single record based on task Id
     * @param id
     * @return
     */
    public int deleteTask(long id) {

        SQLiteDatabase db = this.getWritableDatabase();
        // Delete task from db and return the count
        int count = db.delete(TASK_TABLE_NAME, TASK_ROW_ID + " = " + id, null);
        db.close();
        return count;
    }

    /**
     * Delete multiple records
     * @param ids
     */
    public void deleteTasks(ArrayList<Integer> ids) {

        SQLiteDatabase db = this.getWritableDatabase();
        // This is OK if there are only a few items to delete
        for (long id: ids) {
            db.delete(TASK_TABLE_NAME, TASK_ROW_ID + " = " + id, null);
        }
        db.close();

        // TODO: Another approach - construct a query with all the ids and use one db transaction to delete the selected records
    }

}
