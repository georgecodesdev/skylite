package com.example.skylite.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.skylite.Fragments.fragmentCalendarEventInfomationListItem;
import com.example.skylite.MainActivity;
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
    private Toolbar toolbar;
    private ImageView homeNavigationImage;

    private SimpleDateFormat dateFormat;
    private Intent mainIntent;

    private boolean eventDescriptionDisplaying = false;
    private boolean eventListDisplaying = false;

    private ArrayList<fragmentCalendarEventInfomationListItem> listItems;
    private FragmentCalendarEventInformation eventInformation;
    private FragmentNoEventsAvailable noEventsAvailable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.getLogger("com.google.api.client").setLevel(LOGGING_LEVEL);

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        noEventsAvailable = new FragmentNoEventsAvailable();
        listItems = new ArrayList<>();

        mainIntent = new Intent(this, MainActivity.class);

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

    private void populateFragmentBasedOnDateSelected(String dateSelected){
        List<Event> events = ServiceBase.eventsService().getEventsByDate(dateSelected);
        if(events.size() != 0){
            removeCurrentFragment();
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
            fragmentCalendarEventInfomationListItem newItem = new fragmentCalendarEventInfomationListItem(currentEvent);
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
        for (fragmentCalendarEventInfomationListItem currFragment: listItems) {
            fragmentTransaction.remove(currFragment);
        }
        fragmentTransaction.commit();
        listItems.clear();
    }

    private void grabElementsByID(){
        calendarView = findViewById(R.id.calendar);
        constellationEventDescriptionLayout = findViewById(R.id.constellationEventLayout);
        toolbar = findViewById(R.id.toolbar);
        homeNavigationImage = findViewById(R.id.homeNavigationImage);
        setSupportActionBar(toolbar);
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
        homeNavigationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(mainIntent);
            }
        });
    }
}
