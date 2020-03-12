package com.asfartz.recyclerviewpoc.view.grid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.asfartz.recyclerviewpoc.Note;
import com.asfartz.recyclerviewpoc.R;
import com.asfartz.recyclerviewpoc.adapters.NoteAdapter;
import com.asfartz.recyclerviewpoc.view.linear.HorizontalRvActivity;
import com.asfartz.recyclerviewpoc.view.linear.InfiniteRvActivity;
import com.asfartz.recyclerviewpoc.view.linear.ReverseRvActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class GridHRvActivity extends AppCompatActivity {

    private static final String TAG = "GridHRvActivity";

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private NoteAdapter noteAdapter;
    private FloatingActionButton addBtn;
    List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_h_rv);
        addBtn = findViewById(R.id.grid_h_rv_add_button);
        recyclerView = findViewById(R.id.grid_h_rv);

        initList();
        initAddBtn();

        gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
        noteAdapter = new NoteAdapter(notes);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(noteAdapter);

    }

    private void initList() {
        notes = new ArrayList<>();
        notes.add(new Note("Title 1", "Description 1"));
        notes.add(new Note("Title 2", "Description 2"));
        notes.add(new Note("Title 3", "Description 3"));
        notes.add(new Note("Title 4", "Description 4"));
        notes.add(new Note("Title 5", "Description 5"));
    }

    private void initAddBtn(){
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nr = notes.size() + 1;
                noteAdapter.addItem(new Note("Title " + nr, "Description " + nr));
                recyclerView.smoothScrollToPosition(noteAdapter.getItemCount() - 1);   // " - 1 " because positions start from 0
                Log.d(TAG, "size = " + noteAdapter.getItemCount() + ", smooth scroll to: " + (noteAdapter.getItemCount() - 1));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_recyclerviews, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_infinite_rv:
                Intent infiniteRvIntent = new Intent(GridHRvActivity.this, InfiniteRvActivity.class);
                startActivity(infiniteRvIntent);
                finish();
                return true;

            case R.id.menu_reverse_rv:
                Intent reverseRvIntent = new Intent(GridHRvActivity.this, ReverseRvActivity.class);
                startActivity(reverseRvIntent);
                finish();
                return true;

            case R.id.menu_horizontal_rv:
                Intent horizontalRvIntent = new Intent(GridHRvActivity.this, HorizontalRvActivity.class);
                startActivity(horizontalRvIntent);
                finish();
                return true;

            case R.id.menu_grid_v_rv:
                Intent gridVRvIntent = new Intent(GridHRvActivity.this, GridVRvActivity.class);
                startActivity(gridVRvIntent);
                finish();
                return true;

            case R.id.menu_grid_h_rv:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
