package com.example.skylite.Fragments;

import android.content.Context;
import android.content.res.TypedArray;
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

import com.example.skylite.R;


public class FragmentTrophy extends Fragment {

    private String descriptionStr;
    private String titleStr;
    private boolean hasCompletedTrophy = false;
    private Drawable completedTrophy;
    private Drawable uncompletedTrophy;

    private TextView description;
    private TextView title;
    private ImageView trophyImage;
    private String identifier;

    public FragmentTrophy() {
        // Required empty public constructor
    }

    public void setIdentifiers(String identifier, boolean hasCompletedTrophy){
        this.hasCompletedTrophy = hasCompletedTrophy;
        this.identifier = identifier;
        toggleCompletion(this.hasCompletedTrophy);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trophy, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mapUIElementsByID();

        description.setText(descriptionStr);
        title.setTypeface(null, Typeface.BOLD);
        title.setText(titleStr);
        toggleCompletion(hasCompletedTrophy);
    }

    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState){
        super.onInflate(context, attrs, savedInstanceState);

        if (context != null && attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.FragmentTrophy);
            completedTrophy = context.getDrawable(R.drawable.unlocked_trophy_icon);
            uncompletedTrophy = context.getDrawable(R.drawable.blocked_trophy_icon);

            if (ta.hasValue(R.styleable.FragmentTrophy_TrophyDescription)) {
                descriptionStr = ta.getString(R.styleable.FragmentTrophy_TrophyDescription);
            }
            if (ta.hasValue(R.styleable.FragmentTrophy_TrophyTitle)) {
                titleStr = ta.getString(R.styleable.FragmentTrophy_TrophyTitle);
            }
            ta.recycle();
        }

    }

    public void toggleCompletion(boolean hasCompletedTrophy){
        if (hasCompletedTrophy) trophyImage.setImageDrawable(completedTrophy);
        else trophyImage.setImageDrawable(uncompletedTrophy);
    }

    public String getIdentifier(){
        return identifier;
    }

    public boolean getTroophyCompletion(){
        return hasCompletedTrophy;
    }

    // gets the UI elements and maps them to the private variables
    private void mapUIElementsByID(){
        description = getView().findViewById(R.id.description);
        title = getView().findViewById(R.id.title);
        trophyImage = getView().findViewById(R.id.trophyImage);
    }
}
