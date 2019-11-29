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
 * This Activity class builds the recycler view to display all the wiki information in a linear fashion
 * it does so by creating an object adapter using the class ConstellationListAdapter, and feeding the information it the
 * adapter object which will then populate the recycler view with the right information
 * */
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
        
        /*this gets the array list im passing in,
        but android makes you send an array list by making it Serializable, so i have to cast it back to an array list*/
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

    /*This method is used to take the required information we need to pass into the adapter,
     * it does so by passing a list of objects into mAdapter which is dealt with in ConstellationListAdapter*/
    private void populateData(ArrayList<ModelConstellationInfo> constellationListTemp) {

        for (ModelConstellationInfo Temp : constellationListTemp) {
            ModelConstellationList ModelConstellationInfoForList = new ModelConstellationList(Temp.getImageName(), Temp.getTitle(), Temp.getDescriptionShort());
            constellationList.add(ModelConstellationInfoForList);

        }
        mAdapter.notifyDataSetChanged();
    }
}
