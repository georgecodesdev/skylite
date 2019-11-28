package com.example.skylite.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.skylite.Fragments.FragmentConstellationInfo;
import com.example.skylite.Fragments.FragmentConstellationInfoListItem;
import com.example.skylite.MainActivity;
import com.example.skylite.Model.ModelConstellationInfo;
import com.example.skylite.Model.ModelConstellationList;
import com.example.skylite.R;
import com.example.skylite.Services.ServiceBase;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityConstellationInfo extends AppCompatActivity {

    private ModelConstellationList info;
    private LinearLayout linearLayout;
    private String cachedFragmentTitle = "";

    private ArrayList<FragmentConstellationInfoListItem> fragmentListItems;
    private HashMap<String, FragmentConstellationInfo> fragmentConstellationInfoHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentListItems = new ArrayList<>();
        fragmentConstellationInfoHashMap = new HashMap<>();

        setContentView(R.layout.activity_constellation_info_list);
        info = (ModelConstellationList) getIntent().getExtras().get("ModelList");
        getElementsByID();
    }

    @Override
    public void onStart() {
        super.onStart();
        populateFragments();
        setFragmentAttributes();
    }

    @Override
    public void onBackPressed() {
        if (cachedFragmentTitle.isEmpty()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else{
            removeFragmentWikiItem();
            setFragmentAttributes();
        }
    }

    public void switchToWikiView(String title){
        FragmentConstellationInfo newFragment = fragmentConstellationInfoHashMap.get(title);
        removeFragmentListItems();
        setWikiFragment(newFragment);
    }

    private void removeFragmentListItems(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (FragmentConstellationInfoListItem currentFragment: fragmentListItems) {
            fragmentTransaction.remove(currentFragment);
        }
        fragmentTransaction.commit();
    }

    private void removeFragmentWikiItem(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(fragmentConstellationInfoHashMap.get(cachedFragmentTitle));
        fragmentTransaction.commit();
        cachedFragmentTitle = "";
    }

    private void setWikiFragment(FragmentConstellationInfo wikiFragment){
        cachedFragmentTitle = wikiFragment.getConstellationTitleStr();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(linearLayout.getId(), wikiFragment, wikiFragment.getConstellationTitleStr());
        fragmentTransaction.commit();
    }

    private void setFragmentAttributes(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (FragmentConstellationInfoListItem currentFragment: fragmentListItems) {
            fragmentTransaction.add(linearLayout.getId(), currentFragment, currentFragment.getConstellationTitleStr());
        }
        fragmentTransaction.commit();
    }

    private void populateFragments(){
        if (fragmentListItems.size() == 0){
            for (ModelConstellationInfo currentData : info.getConstellationInfo()) {
                FragmentConstellationInfo wikiInfo = new FragmentConstellationInfo(
                        currentData.imageName,
                        currentData.title,
                        currentData.descriptionLong);
                fragmentConstellationInfoHashMap.put(currentData.title, wikiInfo);

                FragmentConstellationInfoListItem item = new FragmentConstellationInfoListItem(
                        currentData.imageName,
                        currentData.title,
                        currentData.descriptionShort);
                fragmentListItems.add(item);
            }
        }
    }

    private void getElementsByID(){
        linearLayout = findViewById(R.id.fragmentList);
    }
}
