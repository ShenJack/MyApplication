package com.example.pc_100.myapplication.todolist;

import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.pc_100.myapplication.R;
import com.example.pc_100.myapplication.todolist.Data.TaskContract;

public class TodoActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    private RecyclerView mTodolistRecyclerview;
    private FloatingActionButton floatingActionButton;

    private TodoAdapter todoAdapter;
    private CustomCursorAdapter mAdapter;
    private static final int LOADER_ID =746;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_todo);

        mTodolistRecyclerview = (RecyclerView) findViewById(R.id.todolist_recyclerview);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_bar);

        mTodolistRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        todoAdapter = new TodoAdapter(this);

        mAdapter = new CustomCursorAdapter(this);

        mTodolistRecyclerview.setAdapter(mAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewTaskDialog newTaskDialog = new NewTaskDialog(TodoActivity.this);
                newTaskDialog.show();
                newTaskDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        restartLoader();
                    }
                });
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT){

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int id = (int) viewHolder.itemView.getTag();

                String stringId = Integer.toString(id);
                Uri uri = TaskContract.TaskEntry.CONTENT_URI;
                uri = uri.buildUpon().appendPath(stringId).build();

                getContentResolver().delete(uri,null,null);

                getSupportLoaderManager().restartLoader(LOADER_ID, null, TodoActivity.this);
            }
        }).attachToRecyclerView(mTodolistRecyclerview);

        getSupportLoaderManager().initLoader(LOADER_ID,null,this);
        Toast.makeText(this, "start", Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onResume() {
        Log.d("resume","resume");
        super.onResume();
        getSupportLoaderManager().restartLoader(LOADER_ID, null, this);

    }

    void restartLoader(){
        getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<Cursor>(this) {
            Cursor mTaskData = null;

            @Override
            protected void onStartLoading() {
                if(mTaskData!=null){
                    deliverResult(mTaskData);
                }
                else {
                    forceLoad();
                }
            }

            @Override
            public Cursor loadInBackground() {
                try {
                    return getContentResolver().query(TaskContract.TaskEntry.CONTENT_URI
                            ,null
                            ,null
                            ,null
                            , TaskContract.TaskEntry.COLOMN_PRIORITY);
                }catch (Exception e){
                    Log.e(" ", "Failed to asynchronously load data.");
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public void deliverResult(Cursor data) {
                mTaskData = data;
                super.deliverResult(data);
            }
        };
    }
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }


}
