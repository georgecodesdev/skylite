package com.example.skylite.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.skylite.R;


public class FragmentConstellationInfoListItem extends Fragment {

    private ImageView constellationImage;
    private TextView constellationTitle;
    private TextView constellationDescription;

    private String constellationImagePath;
    private String constellationTitleStr;
    private String constellationDescriptionStr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_constellation_info_list_item, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mapUIElementsByID();
    }

    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
    }

    private void mapUIElementsByID() {
        constellationDescription = getView().findViewById(R.id.constellationDescription);
        constellationTitle = getView().findViewById(R.id.constellationTitle);
        constellationImage = getView().findViewById(R.id.constellationImage);
    }
}
