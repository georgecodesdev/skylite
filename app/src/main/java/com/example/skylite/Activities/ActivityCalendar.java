package com.example.skylite.Activities;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.skylite.Fragments.FragmentCalendarEventInformationListItem;
import com.example.skylite.Services.ServiceBase;
import com.example.skylite.Data.Event;
import com.example.skylite.Fragments.FragmentCalendarEventInformation;
import com.example.skylite.Fragments.FragmentNoEventsAvailable;
import com.example.skylite.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;


public class ActivityCalendar extends AppCompatActivity {

    final private String NO_EVENT_FRAGMENT_IDENTIFIER = "noEvent";
    private static final Level LOGGING_LEVEL = Level.OFF;

    private MaterialCalendarView calendarView;
    private LinearLayout constellationEventDescriptionLayout;

    private SimpleDateFormat dateFormat;

    private boolean eventDescriptionDisplaying = false;
    private boolean eventListDisplaying = false;

    private ArrayList<FragmentCalendarEventInformationListItem> listItems;
    private FragmentCalendarEventInformation eventInformation;
    private FragmentNoEventsAvailable noEventsAvailable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.getLogger("com.google.api.client").setLevel(LOGGING_LEVEL);

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        noEventsAvailable = new FragmentNoEventsAvailable();
        listItems = new ArrayList<>();

        setContentView(R.layout.activity_calendar);
        grabElementsByID();
    }

    @Override
    public void onStart() {
        super.onStart();
        grabElementsByID();
        setActionListener();
        setSelectedCalenderDate(CalendarDay.today());
        populateFragmentBasedOnDateSelected(convertSelectedDate(calendarView.getSelectedDate()));
    }

    public String getCurrentDateSelected(){
        return convertSelectedDate(calendarView.getSelectedDate());
    }

    public void populateFragmentBasedOnDateSelected(String dateSelected){
        List<Event> events = ServiceBase.eventsService().getEventsByDate(dateSelected);
        if(events.size() != 0){
            removeCurrentFragment();
            makeNotificationToast(events.size());
            switchToEventList(events);
        }
        else if (eventDescriptionDisplaying){
            removeCurrentFragment();
            switchToNoEventFragment();
        }
        else if (!noEventsAvailable.isVisible()) {
            removeCurrentFragment();
            switchToNoEventFragment();
        }
    }

    private void makeNotificationToast(int numEvents){
        String notificationMessage;
        if (numEvents  ==  1)  notificationMessage = " event is occurring";
        else notificationMessage = " events are occurring";
        Toast.makeText(getApplicationContext(), numEvents + notificationMessage, Toast.LENGTH_SHORT).show();
    }

    private void removeCurrentFragment(){
        if (eventDescriptionDisplaying) removeEventFragment();
        else if (eventListDisplaying) removeEventList();
        else removeNoEventFragment();
    }

    public void switchToEventDetailFragment(Event requestedEvent){
        removeCurrentFragment();

        eventDescriptionDisplaying = true;
        eventListDisplaying = false;

        eventInformation = new FragmentCalendarEventInformation(
                requestedEvent.getLongDescription(),
                requestedEvent.getDate());

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(constellationEventDescriptionLayout.getId(), eventInformation, eventInformation.getEventDateStr());
        fragmentTransaction.commit();
    }

    private void switchToEventList(List<Event> events){
        eventListDisplaying = true;
        eventDescriptionDisplaying = false;

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        for (Event currentEvent : events) {
            FragmentCalendarEventInformationListItem newItem = new FragmentCalendarEventInformationListItem(currentEvent);
            listItems.add(newItem);
            fragmentTransaction.add(constellationEventDescriptionLayout.getId(), newItem, currentEvent.getLongDescription());
        }
        fragmentTransaction.commit();
    }

    private void switchToNoEventFragment(){
        eventDescriptionDisplaying = false;
        eventListDisplaying = false;
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

    private void removeEventList(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (FragmentCalendarEventInformationListItem currFragment: listItems) {
            fragmentTransaction.remove(currFragment);
        }
        fragmentTransaction.commit();
        listItems.clear();
    }

    private void grabElementsByID(){
        calendarView = findViewById(R.id.calendar);
        constellationEventDescriptionLayout = findViewById(R.id.constellationEventLayout);
    }

    private void setSelectedCalenderDate(CalendarDay requestedDate){
        calendarView.setDateSelected(requestedDate, true);
    }

    private String convertSelectedDate(CalendarDay calendarDay){
        String convertedMonth = String.format("%02d", calendarDay.getMonth());
        String convertedDay = String.format("%02d", calendarDay.getDay());
        return calendarDay.getYear() + "-" + convertedMonth + "-" + convertedDay;
    }

    private void setActionListener(){
        calendarView.setOnDateChangedListener((widget, date, selected) -> {
            populateFragmentBasedOnDateSelected(convertSelectedDate(date));
        });
    }
}
