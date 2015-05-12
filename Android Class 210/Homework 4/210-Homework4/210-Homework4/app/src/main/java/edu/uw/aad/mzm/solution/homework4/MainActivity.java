package edu.uw.aad.mzm.solution.homework4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import edu.uw.aad.mzm.solution.homework4.data.TaskDbHelper;
import edu.uw.aad.mzm.solution.homework4.model.Task;


/**
 * Created by Margaret on 3/7/2015
 * Android 210 - Winter 2015 - Homework 4 Solution
 *
 * 1. Create a main screen to show list of tasks
 * 2. There are two menu items on ActionBar: Add & Delete
 * 3. Click on Add to go to a screen for adding a new task
 *  - Create AddTaskActivity.java for taking user input
 *  - Clicking on OK button in AddTaskActivity inserts a task to DB
 *  - Back to the main screen to show list of tasks
 * 4. The ListView is set to select multiple choices or a single choice (code commented out)
 * 5. Click on Delete to delete the selected task(s)
 */
public class MainActivity extends ActionBarActivity {

    private TaskDbHelper mTaskDbHelper;
    private ListView mListViewTasks;
    private ArrayAdapter<Task> mTaskArrayAdapter;
    private List<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get task list data from DB
        mTaskDbHelper = new TaskDbHelper(this);
        tasks = mTaskDbHelper.getTasks();

        // Set up UI ListView
        mListViewTasks = (ListView)findViewById(R.id.listViewTasks);
         mTaskArrayAdapter = new ArrayAdapter<Task>(this,  // context
                 android.R.layout.simple_list_item_multiple_choice,                 // UI layout for the list item, multiple choices
//                android.R.layout.simple_list_item_single_choice,                     // UI layout for the list item, single choice
                tasks);                                                              // objects
        mListViewTasks.setAdapter(mTaskArrayAdapter);
//        mListViewTasks.setChoiceMode(ListView.CHOICE_MODE_SINGLE);                // Use this for single choice
        mListViewTasks.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id) {
            case R.id.action_add:
            Intent intent = new Intent(this, AddTaskActivity.class);
            startActivity(intent);
                break;
            case R.id.action_delete:
                // Delete single task
/*                int position = mListViewTasks.getCheckedItemPosition();
                Task task = mTaskArrayAdapter.getItem(position);
                deleteSingleTask(position, task.getId());*/

                // Delete multiple tasks
                ArrayList<Integer> ids = new ArrayList<Integer>();
                SparseBooleanArray checkedItems = mListViewTasks.getCheckedItemPositions();
                if(checkedItems.size()>0) {
                    for (int i = 0; i < checkedItems.size(); i++) {
                        int position = checkedItems.keyAt(i);
                        Task task = mTaskArrayAdapter.getItem(position);
                        ids.add((int)task.getId());
                    }
                    mTaskDbHelper.deleteTasks(ids);
                }
                refreshUI();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Delete a single task from db
     * @param pos
     * @param id
     */
    private void deleteSingleTask(int pos, long id) {

        if (pos>=0) {
            int rowsDeleted = mTaskDbHelper.deleteTask(id);
            if(rowsDeleted >0) {
                refreshUI();
            }
        }
        Toast.makeText(this, "Item Deleted @position #" + pos + " rowId is #" + id, Toast.LENGTH_LONG).show();
    }

    /**
     * A very ugly way to refresh the UI
     */
    private void refreshUI() {

        tasks = mTaskDbHelper.getTasks();           // Get all the tasks from db
        mTaskArrayAdapter.clear();                  // Clear the ArrayAdapter
        mTaskArrayAdapter.addAll(tasks);            // Add all tasks
        mListViewTasks.clearChoices();              // Clear all selections
        mTaskArrayAdapter.notifyDataSetChanged();
    }
}
