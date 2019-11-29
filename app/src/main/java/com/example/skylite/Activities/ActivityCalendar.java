package com.example.skylite.Activities;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

import com.example.skylite.Fragments.FragmentCalendarEventInformationListItem;
import com.example.skylite.Services.ServiceBase;
import com.example.skylite.Data.Event;
import com.example.skylite.Fragments.FragmentCalendarEventInformation;
import com.example.skylite.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

/**
 * Activity allows for user to select events which contain constellation infomation
 */
public class ActivityCalendar extends AbstractActivityTopBar {

    private MaterialCalendarView calendarView;
    private LinearLayout constellationEventDescriptionLayout;

    private SimpleDateFormat dateFormat;
    private boolean eventDescriptionDisplaying = false;
    private boolean eventListDisplaying = false;
    private boolean init = false;

    private ArrayList<FragmentCalendarEventInformationListItem> listItems;
    private FragmentCalendarEventInformation eventInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init = true;
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        listItems = new ArrayList<>();
        setContentView(R.layout.activity_calendar);
        grabElementsByID();
        initIntent(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        setSelectedCalenderDate(CalendarDay.today());
        populateFragmentBasedOnDateSelected(convertSelectedDate(calendarView.getSelectedDate()));
        init = false;
    }

    public String getCurrentDateSelected() {
        return convertSelectedDate(calendarView.getSelectedDate());
    }

    // Swaps out the fragment based on date selected
    public void populateFragmentBasedOnDateSelected(String dateSelected) {
        List<Event> events = ServiceBase.eventsService().getEventsByDate(dateSelected);
        removeCurrentFragment();
        if (events.size() != 0) {
            if (!init) makeNotificationToast(events.size());
            switchToEventList(events);
        } else if (eventDescriptionDisplaying) {
            if (!init) makeNoDatesToast();
            switchToNoEventFragment();
        } else {
            if (!init) makeNoDatesToast();
        }
    }

    private void makeNotificationToast(int numEvents) {
        String notificationMessage;
        if (numEvents == 1) notificationMessage = "Event predicted";
        else notificationMessage = "Events predicted, scroll down to discover more";
        Toast.makeText(getApplicationContext(), notificationMessage, Toast.LENGTH_SHORT).show();
    }

    private void makeInfoToast() {
        Toast.makeText(getApplicationContext(), "Pick a date to see constellation events", Toast.LENGTH_LONG).show();
    }

    private void makeNoDatesToast() {
        Toast.makeText(getApplicationContext(), "No events predicted", Toast.LENGTH_SHORT).show();
    }

    private void removeCurrentFragment() {
        if (eventDescriptionDisplaying) removeEventFragment();
        else if (eventListDisplaying) removeEventList();
    }

    public void switchToEventDetailFragment(Event requestedEvent) {
        removeCurrentFragment();

        eventDescriptionDisplaying = true;
        eventListDisplaying = false;

        eventInformation = new FragmentCalendarEventInformation(
                requestedEvent.getLongDescription(),
                requestedEvent.getShortDescription());

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(constellationEventDescriptionLayout.getId(), eventInformation, eventInformation.getEventDateStr());
        fragmentTransaction.commit();
    }

    private void switchToEventList(List<Event> events) {
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

    private void switchToNoEventFragment() {
        eventDescriptionDisplaying = false;
        eventListDisplaying = false;
    }

    private void removeEventFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(eventInformation);
        fragmentTransaction.commit();
    }

    private void removeEventList() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (FragmentCalendarEventInformationListItem currFragment : listItems) {
            fragmentTransaction.remove(currFragment);
        }
        fragmentTransaction.commit();
        listItems.clear();
    }

    private void grabElementsByID() {
        calendarView = findViewById(R.id.calendar);
        constellationEventDescriptionLayout = findViewById(R.id.constellationEventLayout);
        toolbar = findViewById(R.id.toolbar);
        homeNavigationImage = findViewById(R.id.homeNavigationImage);
        setSupportActionBar(toolbar);
        setActionListener();
        makeInfoToast();
    }

    private void setSelectedCalenderDate(CalendarDay requestedDate) {
        calendarView.setDateSelected(requestedDate, true);
    }

    private String convertSelectedDate(CalendarDay calendarDay) {
        String convertedMonth = String.format("%02d", calendarDay.getMonth());
        String convertedDay = String.format("%02d", calendarDay.getDay());
        return calendarDay.getYear() + "-" + convertedMonth + "-" + convertedDay;
    }

    private void setActionListener() {
        calendarView.setOnDateChangedListener((widget, date, selected) -> {
            populateFragmentBasedOnDateSelected(convertSelectedDate(date));
        });
        setToolbarActionListener();
    }
}
