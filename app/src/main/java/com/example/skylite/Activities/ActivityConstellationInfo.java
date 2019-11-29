package com.example.skylite.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.skylite.Data.Constellation;
import com.example.skylite.Data.ConstellationListAdapter;
import com.example.skylite.Fragments.FragmentConstellationInfo;
import com.example.skylite.Fragments.FragmentConstellationInfoListItem;
import com.example.skylite.MainActivity;
import com.example.skylite.Model.ModelConstellationInfo;
import com.example.skylite.Model.ModelConstellationList;
import com.example.skylite.R;
import com.example.skylite.Services.ServiceBase;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityConstellationInfo extends AbstractActivityTopBar {

    private ModelConstellationList info;
    private LinearLayout linearLayout;


    private String cachedFragmentTitle = "";
    private SlidingUpPanelLayout slidingLayout;


    private List<ModelConstellationList> constellationList = new ArrayList<>();

    private ArrayList<ModelConstellationInfo> modelConstellationList = new ArrayList<>();

    private RecyclerView recyclerView;
    private ConstellationListAdapter mAdapter;
    private Context context;

    private ArrayList<FragmentConstellationInfoListItem> fragmentListItems;
    private HashMap<String, FragmentConstellationInfo> fragmentConstellationInfoHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        modelConstellationList = (ArrayList<ModelConstellationInfo>) args.getSerializable("ARRAYLIST");


        setContentView(R.layout.data_main_layout);

        info = (ModelConstellationList) getIntent().getExtras().get("ModelList");
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

        for (ModelConstellationInfo Temp  : constellationListTemp) {
            ModelConstellationList ModelConstellationInfoForList = new ModelConstellationList(Temp.getImageName(),Temp.getTitle(),Temp.getDescriptionShort());
            constellationList.add(ModelConstellationInfoForList);

        }
        mAdapter.notifyDataSetChanged();
    }
}
