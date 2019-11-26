package com.example.skylite.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.skylite.Fragments.FragmentConstellationInfoListItem;
import com.example.skylite.Model.ModelConstellationInfo;
import com.example.skylite.Model.ModelConstellationList;
import com.example.skylite.R;

import java.util.ArrayList;

// This guy is going to display the constellations in a a list, where the user can

public class ActivityConstellationInfoList extends AppCompatActivity {

    private ModelConstellationList info;
    private LinearLayout linearLayout;
    private ArrayList<FragmentConstellationInfoListItem> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragments = new ArrayList<>();
        setContentView(R.layout.activity_constellation_info_list);
        info = (ModelConstellationList) getIntent().getExtras().get("ModelList");
    }

    @Override
    public void onStart() {
        super.onStart();
        getElementsByID();
        setFragmentAttributes();
    }

    // gets the UI elements and maps them to the private variables
    private void getElementsByID(){
        // need to get the ID of the linear layout
        linearLayout = (LinearLayout) findViewById(R.id.fragmentList);
    }

    // using the info, we add the fragments to the list
    private void setFragmentAttributes(){
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);

        ll.setId(View.generateViewId());

        if (fragments.size() == 0) {
            for (ModelConstellationInfo currentData : info.constellationInfo) {
                FragmentConstellationInfoListItem item = new FragmentConstellationInfoListItem(currentData.imageName, currentData.title, currentData.descriptionShort);
                fragments.add(item);
            }
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        for (ModelConstellationInfo currentData: info.constellationInfo) {
            FragmentConstellationInfoListItem temp = new FragmentConstellationInfoListItem(currentData.imageName, currentData.title, currentData.descriptionShort);
            fragmentManager.beginTransaction()
                    .add(linearLayout.getId(), temp)
                    .commit();
        }

        for (int i = 0; i < ll.getChildCount(); i++){
            View currentChild = ll.getChildAt(i);
            linearLayout.addView(currentChild);
        }
    }

}
