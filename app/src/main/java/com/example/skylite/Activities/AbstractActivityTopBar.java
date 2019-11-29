package com.example.skylite.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.skylite.MainActivity;

/**
 * Abstract implementation for activities which use custom TopBar
 */
public abstract class AbstractActivityTopBar extends AppCompatActivity {

    public Toolbar toolbar;
    public ImageView homeNavigationImage;
    public Intent homeIntent;

    public void initIntent(AppCompatActivity activity) {
        homeIntent = new Intent(activity, MainActivity.class);
    }

    public void setToolbarActionListener() {
        homeNavigationImage.setOnClickListener(view -> {
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            startActivity(homeIntent);
        });
    }
}
