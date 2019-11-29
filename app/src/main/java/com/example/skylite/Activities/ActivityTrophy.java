package com.example.skylite.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.skylite.Fragments.FragmentTrophy;
import com.example.skylite.MainActivity;
import com.example.skylite.R;

/**
 * Shows users the trophies that he/she has completed
 */
public class ActivityTrophy extends AppCompatActivity {

    public static FragmentTrophy researcher;
    boolean achievement_complete = ConstellationInDepthView.achievement;

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

        if (achievement_complete == true) {
            achievement_unlocked();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void getFragments() {
        researcher = (FragmentTrophy) getSupportFragmentManager().findFragmentById(R.id.researcherTrophy);
    }

    private void setFragmentAttributes() {
        researcher.setIdentifiers("Constellation researcher", false);
    }

    private void achievement_unlocked() {
        researcher.toggleCompletion(true);
        achievement_complete = false;
    }
}
