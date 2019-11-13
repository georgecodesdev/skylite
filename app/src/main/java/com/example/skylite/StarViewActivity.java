package com.example.skylite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.starViewer.StarViewFragment;

public class StarViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starview);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.starView, new StarViewFragment());
        ft.commit();
    }
}
