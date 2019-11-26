package com.example.skylite.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.skylite.R;


public class FragmentConstellationInfo extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_constellation_info, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState){
        super.onInflate(context, attrs, savedInstanceState);
    }


}
