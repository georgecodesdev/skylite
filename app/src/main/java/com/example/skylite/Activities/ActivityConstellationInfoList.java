package com.example.skylite.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.skylite.Fragments.FragmentConstellationInfoListItem;
import com.example.skylite.Model.ModelConstellationInfo;
import com.example.skylite.Model.ModelConstellationList;
import com.example.skylite.R;

import java.util.ArrayList;

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

    private void getElementsByID(){
        linearLayout = findViewById(R.id.fragmentList);
    }

    private void setFragmentAttributes(){
        if (fragments.size() == 0) {
            for (ModelConstellationInfo currentData : info.constellationInfo) {
                FragmentConstellationInfoListItem item = new FragmentConstellationInfoListItem(currentData.imageName, currentData.title, currentData.descriptionShort);
                fragments.add(item);
            }
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        for (ModelConstellationInfo currentData: info.constellationInfo) {
            FragmentConstellationInfoListItem temp = new FragmentConstellationInfoListItem(currentData.imageName, currentData.title, currentData.descriptionShort);
            fragmentTransaction.add(linearLayout.getId(), temp, currentData.title);
        }

        fragmentTransaction.commit();
    }
}
