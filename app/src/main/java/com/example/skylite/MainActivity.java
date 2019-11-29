package com.example.skylite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.skylite.Activities.ActivityCalendar;
import com.example.skylite.Activities.ActivityConstellation;
import com.example.skylite.Activities.ActivityConstellationInfo;
import com.example.skylite.Activities.ActivityTrophy;
import com.example.skylite.Data.Constellation;
import com.example.skylite.Services.ServiceBase;
import com.example.skylite.Model.ModelConstellationInfo;
import com.example.skylite.Model.ModelConstellationList;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SlidingUpPanelLayout slidingLayout;
    public static final List<ModelConstellationInfo> modelConstellationInfo = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slidingLayout = findViewById(R.id.sliding_layout);
        slidingLayout.setAnchorPoint(0.3f);

        ServiceBase.init(new ServiceBase(this.getApplicationContext()));
    }

    public void goToActivity(View v){
        if(v.getId() == R.id.trophiesButton){
            switchToTrophyActivity();
        }
        else if(v.getId() == R.id.wikiButton){
            switchToConstellationListActivity();
            //TODO
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
        ServiceBase.wikiService().getInfo(constellations);
        // TODO: Only loading the first 25 because the full set breaks it
        constellations = constellations.subList(0, ServiceBase.wikiService().getModelConstellationInfo().size());

        for (Constellation Temp  : constellations) {
            ModelConstellationInfo ModelConstellationInfoTemp = new ModelConstellationInfo(Temp.getId()+"_image",Temp.getName(),Temp.getNameOrigin(),Temp.getStory());
            modelConstellationInfo.add(ModelConstellationInfoTemp);

        }
        constellations.clear();

        //modelConstellationList.addConstellationInfo(ServiceBase.wikiService().getInfo(constellations));

        Intent intent = new Intent(this, ActivityConstellationInfo.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST", (Serializable) modelConstellationInfo);
        intent.putExtra("BUNDLE",args);
        startActivity(intent);
    }

    private void switchToCalendarActivity(){
        Intent intent = new Intent(this, ActivityCalendar.class);
        startActivity(intent);
    }
}
