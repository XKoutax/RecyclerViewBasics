package com.asfartz.recyclerviewpoc.view.linear;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asfartz.recyclerviewpoc.Note;
import com.asfartz.recyclerviewpoc.R;
import com.asfartz.recyclerviewpoc.adapters.NoteAdapter;
import com.asfartz.recyclerviewpoc.view.grid.GridHRvActivity;
import com.asfartz.recyclerviewpoc.view.grid.GridVRvActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReverseRvActivity extends AppCompatActivity {

    private static final String TAG = "ReverseRvActivity";

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private NoteAdapter noteAdapter;
    private FloatingActionButton addBtn;
    List<Note> notes;

    private Handler someHandler = new Handler();
    private Runnable someRunnable = new Runnable() {
        @Override
        public void run() {
            noteAdapter.addItem(new Note(UUID.randomUUID().toString().substring(0, 5), UUID.randomUUID().toString().substring(0, 5)));
            recyclerView.smoothScrollToPosition(noteAdapter.getItemCount() - 1);
            Log.d(TAG, "size = " + noteAdapter.getItemCount() + ", smooth scroll to: " + (noteAdapter.getItemCount() - 1));
            someHandler.postDelayed(someRunnable, 1500);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverse_rv);

        addBtn = findViewById(R.id.reverse_rv_add_button);
        recyclerView = findViewById(R.id.reverse_rv);


        initList();
        initAddBtn();
        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, true);
        noteAdapter = new NoteAdapter(notes);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(noteAdapter);

    }


    private void initList() {
        notes = new ArrayList<>();
        notes.add(new Note("Title 1", "Description 1"));
        notes.add(new Note("Title 2", "Description 2"));
        notes.add(new Note("Title 3", "Description 3"));
    }

    private void initAddBtn() {
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
                Intent infiniteRvIntent = new Intent(ReverseRvActivity.this, InfiniteRvActivity.class);
                startActivity(infiniteRvIntent);
                finish();
                return true;

            case R.id.menu_reverse_rv:
                return true;

            case R.id.menu_horizontal_rv:
                Intent horizontalRvIntent = new Intent(ReverseRvActivity.this, HorizontalRvActivity.class);
                startActivity(horizontalRvIntent);
                finish();
                return true;

            case R.id.menu_grid_v_rv:
                Intent gridVIntent = new Intent(ReverseRvActivity.this, GridVRvActivity.class);
                startActivity(gridVIntent);
                finish();
                return true;

            case R.id.menu_grid_h_rv:
                Intent gridHRvIntent = new Intent(ReverseRvActivity.this, GridHRvActivity.class);
                startActivity(gridHRvIntent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
