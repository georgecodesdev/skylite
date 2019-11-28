package com.example.skylite.Activities;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.skylite.Services.ServiceBase;
import com.example.skylite.Data.Event;
import com.example.skylite.Fragments.FragmentCalendarEventInformation;
import com.example.skylite.Fragments.FragmentNoEventsAvailable;
import com.example.skylite.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;


public class ActivityCalendar extends AppCompatActivity {

    final private String NO_EVENT_FRAGMENT_IDENTIFIER = "noEvent";
    private static final Level LOGGING_LEVEL = Level.OFF;

    private MaterialCalendarView calendarView;
    private LinearLayout constellationEventDescriptionLayout;
    private SimpleDateFormat dateFormat;
    private boolean eventDescriptionDisplaying = false;
    private boolean init = false;

    private FragmentCalendarEventInformation eventInformation;
    private FragmentNoEventsAvailable noEventsAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.getLogger("com.google.api.client").setLevel(LOGGING_LEVEL);

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        noEventsAvailable = new FragmentNoEventsAvailable();

        setContentView(R.layout.activity_calendar);
        grabElementsByID();
//        populateCalenderInfo();
    }

    @Override
    public void onStart() {
        super.onStart();
        init = true;
        grabElementsByID();
        setActionListener();
    //    populateFragmentBasedOnDateSelected(convertCurrentDateSelected());
        init = false;
    }

    private void populateFragmentBasedOnDateSelected(String dateSelected){
        // TODO this will be eventually changed to be a SQL look-up

        List<Event> events = ServiceBase.eventsService().getEventsByDate(dateSelected);
        // we have events for the requested date
        if(events.size() != 0){

        }
     /*   if (constellationEventsLookUp.get(dateSelected) != null){
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
       */
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

    private void populateCalenderInfo(){
        List<Event> events = ServiceBase.eventsService().getEvents();

        for (Event currentEvent: events) {

        }

    }

    private void setActionListener(){
        calendarView.setOnDateChangedListener((widget, date, selected) -> {
            String convertedMonth = date.getMonth() < 10 ? String.format("%01d", date.getMonth()): "" + date.getMonth();
            String convertedDay = String.format("%02d", date.getDay());

            populateFragmentBasedOnDateSelected(date.getYear() + "-" + convertedMonth + "-" + convertedDay);
        });
    }
}
