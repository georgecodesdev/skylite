package com.example.skylite.Activities;

import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Dialog;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.skylite.Fragments.FragmentCalendarEventInformation;
import com.example.skylite.Fragments.FragmentNoEventsAvailable;
import com.example.skylite.Model.ModelConstellationDateTimeInfo;
import com.example.skylite.R;
import com.example.skylite.Services.AsyncLoadTasks;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.tasks.TasksScopes;

public class ActivityCalendar extends AppCompatActivity {

    final private String NO_EVENT_FRAGMENT_IDENTIFIER = "noEvent";
    private static final Level LOGGING_LEVEL = Level.OFF;

    private CalendarView calendarView;
    private LinearLayout constellationEventDescriptionLayout;
    private SimpleDateFormat dateFormat;
    private boolean eventDescriptionDisplaying = false;
    private boolean init = false;

    public static final String PREF_ACCOUNT_NAME = "accountName";
    public static final String TAG = "TasksSample";
    public static final int REQUEST_GOOGLE_PLAY_SERVICES = 0;
    public static final int REQUEST_AUTHORIZATION = 1;
    public static final int REQUEST_ACCOUNT_PICKER = 2;
    public final HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
    public final JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
    public GoogleAccountCredential credential;
    public List<String> tasksList;
    public ArrayAdapter<String> adapter;
    public com.google.api.services.tasks.Tasks service;
    public int numAsyncTasks;
    private ListView listView;

    private FragmentCalendarEventInformation eventInformation;
    private FragmentNoEventsAvailable noEventsAvailable;
    private HashMap<String, ModelConstellationDateTimeInfo> constellationEventsLookUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.getLogger("com.google.api.client").setLevel(LOGGING_LEVEL);

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        constellationEventsLookUp = new HashMap<>();
        noEventsAvailable = new FragmentNoEventsAvailable();

        fillModel();
        setContentView(R.layout.activity_calendar);

        listView = (ListView) findViewById(R.id.list);

        credential =
                GoogleAccountCredential.usingOAuth2(this, Collections.singleton(TasksScopes.TASKS));
        SharedPreferences settings = getPreferences(Context.MODE_PRIVATE);
        credential.setSelectedAccountName(settings.getString(PREF_ACCOUNT_NAME, null));
        service =
                new com.google.api.services.tasks.Tasks.Builder(httpTransport, jsonFactory, credential)
                        .setApplicationName("Google-TasksAndroidSample/1.0").build();
    }

    public void showGooglePlayServicesAvailabilityErrorDialog(final int connectionStatusCode) {
        runOnUiThread(new Runnable() {
            public void run() {
                Dialog dialog =
                        GooglePlayServicesUtil.getErrorDialog(connectionStatusCode, ActivityCalendar.this,
                                REQUEST_GOOGLE_PLAY_SERVICES);
                dialog.show();
            }
        });
    }

    public void refreshView() {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tasksList);
        listView.setAdapter(adapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (checkGooglePlayServicesAvailable()) {
            haveGooglePlayServices();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_GOOGLE_PLAY_SERVICES:
                if (resultCode == Activity.RESULT_OK) {
                    haveGooglePlayServices();
                } else {
                    checkGooglePlayServicesAvailable();
                }
                break;
            case REQUEST_AUTHORIZATION:
                if (resultCode == Activity.RESULT_OK) {
                    AsyncLoadTasks.run(this);
                } else {
                    chooseAccount();
                }
                break;
            case REQUEST_ACCOUNT_PICKER:
                if (resultCode == Activity.RESULT_OK && data != null && data.getExtras() != null) {
                    String accountName = data.getExtras().getString(AccountManager.KEY_ACCOUNT_NAME);
                    if (accountName != null) {
                        credential.setSelectedAccountName(accountName);
                        SharedPreferences settings = getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString(PREF_ACCOUNT_NAME, accountName);
                        editor.commit();
                        AsyncLoadTasks.run(this);
                    }
                }
                break;
        }
    }


    /** Check that Google Play services APK is installed and up to date. */
    private boolean checkGooglePlayServicesAvailable() {
        final int connectionStatusCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (GooglePlayServicesUtil.isUserRecoverableError(connectionStatusCode)) {
            showGooglePlayServicesAvailabilityErrorDialog(connectionStatusCode);
            return false;
        }
        return true;
    }

    private void haveGooglePlayServices() {
        // check if there is already an account selected
        if (credential.getSelectedAccountName() == null) {
            // ask user to choose account
            chooseAccount();
        } else {
            // load calendars
            AsyncLoadTasks.run(this);
        }
    }

    private void chooseAccount() {
        startActivityForResult(credential.newChooseAccountIntent(), REQUEST_ACCOUNT_PICKER);
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
