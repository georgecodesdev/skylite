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
        //Resources res = getResources();
        //int resourceId = res.getIdentifier(constellationImageName, "drawable", getActivity().getPackageName());
        int min = 1;
        int max = 5;
        int randomImage = min + (int)(Math.random() * ((max - min) + 1));

        Drawable image = null;

        switch (randomImage) {
            case 1:  image = ResourcesCompat.getDrawable(getResources(), R.drawable.constellation_icon, null);
                break;
            case 2:  image = ResourcesCompat.getDrawable(getResources(), R.drawable.constellation_icon2, null);
                break;
            case 3:  image = ResourcesCompat.getDrawable(getResources(), R.drawable.constellation_icon3, null);
                break;
            case 4:  image = ResourcesCompat.getDrawable(getResources(), R.drawable.constellation_icon4, null);
                break;
            case 5:  image = ResourcesCompat.getDrawable(getResources(), R.drawable.constellation_icon5, null);
                break;
        }
        constellationImage.setImageDrawable(image);
    }

    private void mapUIElementsByID() {
        constellationDescription = getView().findViewById(R.id.constellationDescription);
        constellationTitle = getView().findViewById(R.id.constellationTitle);
        constellationImage = getView().findViewById(R.id.constellationImage);
        moreInfoImage = getView().findViewById(R.id.moreInfoButton);
    }
}
