package com.example.skylite.Fragments;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.skylite.R;

public class FragmentCalendarEventInformation extends Fragment {
    private TextView constellationTitle;
    private TextView constellationDescription;

    private String constellationTitleStr;
    private String constellationDescriptionStr;
    private String eventDateStr;

    public FragmentCalendarEventInformation(String constellationTitleStr,
                                            String constellationDescriptionStr,
                                            String eventDate){
        this.constellationTitleStr = constellationTitleStr;
        this.constellationDescriptionStr = constellationDescriptionStr;
        this.eventDateStr = eventDate;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calendar_event_infomation, container, false);
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

    public String getEventDateStr(){ return eventDateStr; }

    private void mapUIElementsByID(){
        constellationTitle = getView().findViewById(R.id.constellationEventTitle);
        constellationDescription = getView().findViewById(R.id.constellationEventDescription);
    }

    private void setAttributes(){
        constellationTitle.setText(constellationTitleStr);
        constellationTitle.setTypeface(null, Typeface.BOLD);
        constellationDescription.setText(constellationDescriptionStr);
    }
}
