package com.example.skylite.Activities;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.skylite.Fragments.FragmentCalendarEventInformation;
import com.example.skylite.Fragments.FragmentNoEventsAvailable;
import com.example.skylite.Model.ModelConstellationDateTimeInfo;
import com.example.skylite.R;

import java.text.SimpleDateFormat;
import java.util.HashMap;

public class ActivityCalendar extends AppCompatActivity {

    final private String NO_EVENT_FRAGMENT_IDENTIFIER = "noEvent";

    private CalendarView calendarView;
    private LinearLayout constellationEventDescriptionLayout;
    private SimpleDateFormat dateFormat;
    private boolean eventDescriptionDisplaying = false;
    private boolean init = false;

    private FragmentCalendarEventInformation eventInformation;
    private FragmentNoEventsAvailable noEventsAvailable;
    private HashMap<String, ModelConstellationDateTimeInfo> constellationEventsLookUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        constellationEventsLookUp = new HashMap<>();
        noEventsAvailable = new FragmentNoEventsAvailable();
        fillModel();
        setContentView(R.layout.activity_calendar);
    }

    @Override
    public void onStart() {
        super.onStart();
        init = true;
        grabElementsByID();
        setActionListners();
        populateFragmentBasedOnDateSelected(grabCurrentDataSelected());
        init = false;
    }

    private String grabCurrentDataSelected(){
        return dateFormat.format(calendarView.getDate());
    }

    private void populateFragmentBasedOnDateSelected(String dateSelected){
        // TODO this will be eventually changed to be a SQL look-up
        if (constellationEventsLookUp.get(dateSelected) != null){
            removeCurrentFragment();
            ModelConstellationDateTimeInfo eventModelInfomation = constellationEventsLookUp.get(dateSelected);
            eventInformation = new FragmentCalendarEventInformation(
                    eventModelInfomation.eventTitle,
                    eventModelInfomation.eventDescription,
                    eventModelInfomation.date);
            switchToEventFragment(eventInformation);
        }
        else if (eventDescriptionDisplaying) {
            removeCurrentFragment();
            switchToNoEventFragment();
        }
        else if (eventInformation == null && !noEventsAvailable.isVisible()) {
            switchToNoEventFragment();
        }
    }

    private void removeCurrentFragment(){
        if (eventDescriptionDisplaying) removeEventFragment();
        else removeNoEventFragment();
    }

    private void switchToEventFragment(FragmentCalendarEventInformation info){
        eventDescriptionDisplaying = true;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(constellationEventDescriptionLayout.getId(), info , info.getEventDateStr());
        fragmentTransaction.commit();
    }

    private void switchToNoEventFragment(){
        eventDescriptionDisplaying = false;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(constellationEventDescriptionLayout.getId(), noEventsAvailable , NO_EVENT_FRAGMENT_IDENTIFIER);
        fragmentTransaction.commit();
    }

    private void removeNoEventFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(noEventsAvailable);
        fragmentTransaction.commit();
    }

    private void removeEventFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(eventInformation);
        fragmentTransaction.commit();
    }

    private void grabElementsByID(){
        calendarView = findViewById(R.id.calendar);
        constellationEventDescriptionLayout = findViewById(R.id.constellationEventLayout);
    }

    //TODO replace this method with proper integation with back-end | this would be a good place to use the DB since this activity shouldnt be storing everything
    private void fillModel(){
        ModelConstellationDateTimeInfo info = new ModelConstellationDateTimeInfo("30/10/2019",
                "Test Title",
                "Test Description");
        constellationEventsLookUp.put(info.date, info);
    }

    private void setActionListners(){
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView arg0, int year, int month, int date) {
                String dateStr = date + "/" + month + "/" + year;
                populateFragmentBasedOnDateSelected(dateStr);
            }
        });
    }
}
