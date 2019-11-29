package com.example.skylite.Activities;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.skylite.Data.ConstellationListAdapter;
import com.example.skylite.Model.ModelConstellationInfo;
import com.example.skylite.Model.ModelConstellationList;
import com.example.skylite.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Displays constellation info taken from Data
 */
public class ActivityConstellationInfo extends AbstractActivityTopBar {

    private List<ModelConstellationList> constellationList = new ArrayList<>();
    private ArrayList<ModelConstellationInfo> modelConstellationList = new ArrayList<>();

    private RecyclerView recyclerView;
    private ConstellationListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        modelConstellationList = (ArrayList<ModelConstellationInfo>) args.getSerializable("ARRAYLIST");


        setContentView(R.layout.data_main_layout);

        initIntent(this);
        toolbar = findViewById(R.id.toolbar);
        homeNavigationImage = findViewById(R.id.homeNavigationImage);
        setSupportActionBar(toolbar);
        setToolbarActionListener();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new ConstellationListAdapter(constellationList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        recyclerView.setAdapter(mAdapter);
        populateData(modelConstellationList);


    }

    private void populateData(ArrayList<ModelConstellationInfo> constellationListTemp) {

        for (ModelConstellationInfo Temp : constellationListTemp) {
            ModelConstellationList ModelConstellationInfoForList = new ModelConstellationList(Temp.getImageName(), Temp.getTitle(), Temp.getDescriptionShort());
            constellationList.add(ModelConstellationInfoForList);

        }
        mAdapter.notifyDataSetChanged();
    }
}
