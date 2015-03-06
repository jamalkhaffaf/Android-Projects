package com.example.jamaljamal.homework4;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jamaljamal.homework4.data.TaskListDbHelper;
import com.example.jamaljamal.homework4.model.TaskListList;

import java.util.List;


public class MainActivity extends ActionBarActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }

    private TaskListDbHelper mDbHelper;
    private List<TaskListList> mtaskListLists;
    private ListView mListViewAndroid;
    private ArrayAdapter<TaskListList> mTaskListListArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new TaskListDbHelper(this);

        // Add a few Android versions
        mDbHelper.insertTaskListList(new TaskListList("graucary", "need to buy some milk"));
        mDbHelper.insertTaskListList(new TaskListList("doctor", "check on the results"));

        // Get all the Android Versions data from db
        mtaskListLists = mDbHelper.();

        // Set up UI ListView
        mListViewAndroid = (ListView)findViewById(R.id.);
        mTaskListListArrayAdapter = new ArrayAdapter<TaskListList>(this,  // context
                android.R.layout.simple_list_item_1,                        // UI layout
                mtaskListLists);                                                     // objects
        mListViewAndroid.setAdapter(mTaskListListArrayAdapter);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
