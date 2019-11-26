package com.example.skylite.Fragments;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.skylite.Activities.ActivityConstellationInfo;
import com.example.skylite.R;

public class FragmentConstellationInfoListItem extends Fragment {

    private ImageView constellationImage;
    private TextView constellationTitle;
    private TextView constellationDescription;
    private ImageView moreInfoImage;

    private String constellationImageName;
    private String constellationTitleStr;
    private String constellationDescriptionStr;

    public FragmentConstellationInfoListItem(String constellationImageName,
                                             String constellationTitleStr,
                                             String constellationDescriptionShortStr){
        this.constellationImageName = constellationImageName;
        this.constellationTitleStr = constellationTitleStr;
        this.constellationDescriptionStr = constellationDescriptionShortStr;
    }

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
        setAttributes();
    }

    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
    }

    public String getConstellationTitleStr(){
        return constellationTitleStr;
    }

    private void setAttributes(){
        constellationTitle.setText(constellationTitleStr);
        constellationTitle.setTypeface(null, Typeface.BOLD);

        constellationDescription.setText(constellationDescriptionStr);

        getImageFromDrawable();

        moreInfoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                ((ActivityConstellationInfo)getActivity()).switchToWikiView(constellationTitleStr);
            }
        });
    }

    private void getImageFromDrawable(){
        Resources res = getResources();
        int resourceId = res.getIdentifier(constellationImageName, "drawable", getActivity().getPackageName());
        Drawable image = res.getDrawable(resourceId, getActivity().getTheme());
        constellationImage.setImageDrawable(image);
    }

    private void mapUIElementsByID() {
        constellationDescription = getView().findViewById(R.id.constellationDescription);
        constellationTitle = getView().findViewById(R.id.constellationTitle);
        constellationImage = getView().findViewById(R.id.constellationImage);
        moreInfoImage = getView().findViewById(R.id.moreInfoButton);
    }
}
