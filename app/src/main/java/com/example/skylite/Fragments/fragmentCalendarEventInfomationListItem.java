package com.example.skylite.Fragments;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.skylite.Activities.ActivityCalendar;
import com.example.skylite.Data.Event;
import com.example.skylite.R;

public class fragmentCalendarEventInfomationListItem extends Fragment {

    private String date;
    private String longDescription;
    private String shortDescription;
    private Event event;

    private ImageView moreInfo;
    private ImageView emailShareIcon;
    private ImageView imageIcon;
    private TextView eventDate;
    private TextView eventShortDescription;

    public fragmentCalendarEventInfomationListItem(Event _event) {
        date = _event.getDate();
        longDescription = _event.getLongDescription();
        shortDescription = _event.getShortDescription();
        event = _event;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar_event_infomation_list_item, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mapUIElementsByID();
        setAttributes();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void mapUIElementsByID() {
        eventDate = getView().findViewById(R.id.constellationEventDate);
        eventShortDescription = getView().findViewById(R.id.constellationDescription);
        moreInfo = getView().findViewById(R.id.moreInfoButton);
        imageIcon = getView().findViewById(R.id.constellationImage);
        emailShareIcon = getView().findViewById(R.id.emailShareButton);
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
        imageIcon.setImageDrawable(image);
    }

    private void setAttributes() {
        eventDate.setText(date);
        eventDate.setTypeface(null, Typeface.BOLD);
        eventShortDescription.setText(shortDescription);

        getImageFromDrawable();

        moreInfo.setOnClickListener(view -> ((ActivityCalendar)getActivity()).switchToEventDetailFragment(event));

        emailShareIcon.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("plain/text");
            intent.putExtra(Intent.EXTRA_SUBJECT, event.getDate() + " - " + shortDescription);
            intent.putExtra(Intent.EXTRA_TEXT, longDescription);
            startActivity(Intent.createChooser(intent, ""));
        });
    }
}
