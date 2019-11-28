package com.example.skylite.Activities;

//import android.support.v7.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class ActivityConstellationInfo extends AppCompatActivity {

    private ModelConstellationList info;
    private LinearLayout linearLayout;
    private String cachedFragmentTitle = "";
    private SlidingUpPanelLayout slidingLayout;


    private List<ModelConstellationInfo> constalltionList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ConstellationListAdapter mAdapter;
    private Context context;

    private ArrayList<FragmentConstellationInfoListItem> fragmentListItems;
    private HashMap<String, FragmentConstellationInfo> fragmentConstellationInfoHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_main_layout);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new ConstellationListAdapter(constalltionList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        recyclerView.setAdapter(mAdapter);


        populateData();
    }

    private void populateData() {

        String[] tvseries = new String[]{
                "https://wallpapersite.com/images/pages/pic_w/14693.jpg",
                "https://wallpapersite.com/images/pages/pic_w/9678.jpg",
                "https://stmed.net/sites/default/files/the-blacklist-wallpapers-31695-8813388.jpg",
                "https://stmed.net/sites/default/files/styles/1280x720/public/lucifer-wallpapers-31613-6873183.jpg?itok=74jI2BiU",
                "https://stmed.net/sites/default/files/styles/1280x720/public/the-big-bang-theory-wallpapers-31694-2815088.jpg?itok=K_MjM_rv",
                "https://wallpapersite.com/images/pages/pic_w/1658.jpg",
                "https://i.ytimg.com/vi/IsMRioOpZrY/maxresdefault.jpg",
                "https://stmed.net/sites/default/files/styles/1280x720/public/the-100-wallpapers-31691-3965773.jpg?itok=GIFpBqsA",
                "https://wallpapercave.com/wp/wp1865530.jpg",
                "https://wallpapersite.com/images/pages/pic_w/17210.jpg",

        };



        ModelConstellationInfo tv = new ModelConstellationInfo("Arrow","7.5", "bob",tvseries[0]);
        constalltionList.add(tv);

        tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);

        tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);  tv = new ModelConstellationInfo("7.5", "bob","bob", tvseries[0]);
        constalltionList.add(tv);

        mAdapter.notifyDataSetChanged();
    }


}
