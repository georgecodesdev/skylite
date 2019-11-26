package com.example.skylite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.skylite.Activities.ActivityConstellation;
import com.example.skylite.Activities.ActivityTrophy;
import com.example.skylite.Services.ServiceBase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ServiceBase.init(new ServiceBase(this.getApplicationContext()));

        switchToConstellationActivity();
//                switchToTrophyActivity();
    }

    private void switchToConstellationActivity(){
        Intent intent = new Intent(this, ActivityConstellation.class);
        startActivity(intent);
    }


    private void switchToTrophyActivity(){
        Intent intent = new Intent(this, ActivityTrophy.class);
        startActivity(intent);
    }
}
