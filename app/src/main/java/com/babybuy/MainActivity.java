package com.babybuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import com.babybuy.Models.Item;
import com.babybuy.adapter.ItemAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ItemAdapter adapter;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Item> options =
                new FirebaseRecyclerOptions.Builder<Item>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("items"), Item.class)
                        .build();

        adapter=new ItemAdapter(options);
        recyclerView.setAdapter(adapter);

        floatingActionButton= (FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(view -> startActivity(AddItems.getIntent(getApplicationContext())));

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}