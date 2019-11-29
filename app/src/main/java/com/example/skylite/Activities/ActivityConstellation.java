package com.example.skylite.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.skylite.Data.ConstellationListAdapter;
import com.example.skylite.Data.ConstellationViewModel;
import com.example.skylite.R;

public class ActivityConstellation extends AppCompatActivity {
    private ConstellationViewModel constellationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandbox);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        //final ConstellationListAdapter adapter = new ConstellationListAdapter(this);
       // recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        constellationViewModel = new ViewModelProvider(this).get(ConstellationViewModel.class);

        // Update the cached copy of the words in the adapter.
      //  constellationViewModel.getAllConstellations().observe(this, adapter::setConstellations);
    }
}
