package com.example.skylite.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.skylite.Fragments.FragmentTrophy;
import com.example.skylite.MainActivity;
import com.example.skylite.R;

public class ActivityTrophy extends AppCompatActivity {

    // The Fragments we are looking for are going to be defined here
    private FragmentTrophy stargazer;
    private FragmentTrophy astronomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy);
    }

    @Override
    public void onStart() {
        super.onStart();
        getFragments();
        setFragmentAttributes();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // gets the UI elements and maps them to the private variables
    private void getFragments(){
        stargazer = (FragmentTrophy) getSupportFragmentManager().findFragmentById(R.id.starTrophy);
        astronomer = (FragmentTrophy) getSupportFragmentManager().findFragmentById(R.id.astronomerTrophy);
    }

    private void setFragmentAttributes(){
        stargazer.setIdentifiers("stargazer", true);
        astronomer.setIdentifiers("astronomer", false);
    }

}
