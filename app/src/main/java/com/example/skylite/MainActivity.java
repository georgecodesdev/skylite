package com.example.skylite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.skylite.Activities.ActivityCalendar;
import com.example.skylite.Activities.ActivityConstellation;
import com.example.skylite.Activities.ActivityConstellationInfo;
import com.example.skylite.Activities.ActivityTrophy;
import com.example.skylite.Data.Constellation;
import com.example.skylite.Data.ConstellationListAdapter;
import com.example.skylite.Data.ConstellationViewModel;
import com.example.skylite.Data.Event;
import com.example.skylite.Data.Repository;
import com.example.skylite.Services.ServiceBase;
import com.example.skylite.Model.ModelConstellationInfo;
import com.example.skylite.Model.ModelConstellationList;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SlidingUpPanelLayout slidingLayout;
    private ConstellationViewModel constellationViewModel;
    private List<Constellation> constellationsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constellationsData = new ArrayList<>();

        slidingLayout = findViewById(R.id.sliding_layout);
        slidingLayout.setAnchorPoint(0.3f);

        ServiceBase.init(new ServiceBase(this.getApplicationContext()));
        Repository.init();
        constellationViewModel = new ViewModelProvider(this).get(ConstellationViewModel.class);
        constellationViewModel.getAllConstellations().observe(this, this.constellationsData::addAll);

//        switchToConstellationListActivity();
//                switchToTrophyActivity();
//        switchToCalendarActivity();
    }

    public void goToActivity(View v){
        if(v.getId() == R.id.trophiesButton){
            switchToTrophyActivity();
        }
        else if(v.getId() == R.id.wikiButton){
            switchToConstellationListActivity();
        }
        else if(v.getId() == R.id.calendarButton){
            switchToCalendarActivity();
        }
    }

    private void switchToTrophyActivity(){
        Intent intent = new Intent(this, ActivityTrophy.class);
        startActivity(intent);
    }

    private void switchToScrollingActivity(){
        //TODO
    }

    private void switchToConstellationListActivity(){
        List<Constellation> constellations = ServiceBase.constellationService().get();
        // TODO: Only loading the first 30 because the full set breaks it
        constellations = constellations.subList(0, 30);

        ModelConstellationList modelConstellationList = new ModelConstellationList();
        modelConstellationList.addConstellationInfo(ServiceBase.wikiService().getInfo(constellations));
        Repository.setModelConstellationList(modelConstellationList);


        Intent intent = new Intent(this, ActivityConstellationInfo.class);
        intent.putExtra("ModelList", modelConstellationList);
        startActivity(intent);
    }

    private void switchToCalendarActivity(){
        Intent intent = new Intent(this, ActivityCalendar.class);
        startActivity(intent);
    }
}
