package com.example.skylite.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.skylite.Fragments.FragmentTrophy;
import com.example.skylite.MainActivity;
import com.example.skylite.R;

public class ActivityTrophy extends AbstractActivityTopBar {

    // The Fragments we are looking for are going to be defined here
    private FragmentTrophy stargazer;
    private FragmentTrophy astronomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy);
        initIntent(this);
        getElementsByID();
    }

    @Override
    public void onStart() {
        super.onStart();
        setFragmentAttributes();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void getElementsByID(){
        stargazer = (FragmentTrophy) getSupportFragmentManager().findFragmentById(R.id.starTrophy);
        astronomer = (FragmentTrophy) getSupportFragmentManager().findFragmentById(R.id.astronomerTrophy);
        toolbar = findViewById(R.id.toolbar);
        homeNavigationImage = findViewById(R.id.homeNavigationImage);
        setSupportActionBar(toolbar);
        setToolbarActionListener();
    }

    private void setFragmentAttributes(){
        stargazer.setIdentifiers("stargazer", true);
        astronomer.setIdentifiers("astronomer", false);
    }

}
