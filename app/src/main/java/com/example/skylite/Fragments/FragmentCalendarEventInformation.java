package com.example.skylite.Fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.skylite.Activities.ActivityCalendar;
import com.example.skylite.Activities.ActivityConstellationInfo;
import com.example.skylite.R;

public class FragmentCalendarEventInformation extends Fragment {
    private TextView eventTitle;
    private TextView constellationDescription;
    private ImageView backImage;

    private String constellationDescriptionStr;
    private String eventDateStr;

    public FragmentCalendarEventInformation(String constellationDescriptionStr,
                                            String eventDate){
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
        eventTitle = getView().findViewById(R.id.constellationEventTitle);
        constellationDescription = getView().findViewById(R.id.constellationEventDescription);
        backImage = getView().findViewById(R.id.back_button);
    }

    private void setAttributes(){
        eventTitle.setText(eventDateStr);
        eventTitle.setTypeface(null, Typeface.BOLD);
        constellationDescription.setText(constellationDescriptionStr);

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                ((ActivityCalendar)getActivity()).populateFragmentBasedOnDateSelected(
                        ((ActivityCalendar)getActivity()).getCurrentDateSelected());
            }
        });
    }
}
