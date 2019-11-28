package com.example.skylite.Fragments;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.skylite.R;


public class FragmentConstellationInfo extends Fragment {

    private ImageView constellationImage;
    private TextView constellationTitle;
    private TextView constellationDescription;

    private String constellationImageName;
    private String constellationTitleStr;
    private String constellationDescriptionStr;

    public FragmentConstellationInfo(String constellationImageName,
                                     String constellationTitleStr,
                                     String constellationDescriptionStr){
        this.constellationImageName = constellationImageName;
        this.constellationTitleStr = constellationTitleStr;
        this.constellationDescriptionStr = constellationDescriptionStr;
    }

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
        mapUIElementsByID();
        setAttributes();
    }

    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState){
        super.onInflate(context, attrs, savedInstanceState);
    }

    public String getConstellationTitleStr(){
        return constellationTitleStr;
    }

    private void mapUIElementsByID(){
        constellationDescription = getView().findViewById(R.id.constellationDescription);
        constellationTitle = getView().findViewById(R.id.constellationTitle);
        constellationImage = getView().findViewById(R.id.constellationImage);
    }

    private void setAttributes(){
        constellationTitle.setText(constellationTitleStr);
        constellationTitle.setTypeface(null, Typeface.BOLD);

        constellationDescription.setText(constellationDescriptionStr);

        Resources res = getResources();
        int resourceId = res.getIdentifier(constellationImageName, "drawable", getActivity().getPackageName());
        Drawable image = res.getDrawable(resourceId, getActivity().getTheme());
        constellationImage.setImageDrawable(image);
    }
}
