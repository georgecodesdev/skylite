package com.example.skylite.Services;

import android.content.Context;

import com.example.skylite.Data.Event;
import com.example.skylite.R;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

public class EventsService implements IEventsService {
    private Context _context;
    private List<Event> _events;
    private final String ASSET_PATH;
    private final Calendar c;
    private final SimpleDateFormat format;

    EventsService(Context context) {
        this._context = context;
        this.ASSET_PATH = this._context.getResources().getString(R.string.events_asset_path);
        c = Calendar.getInstance();
        format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    }


    @Override
    public void populateList() {
        Type listType = new TypeToken<List<Event>>() {}.getType();
        String json = ServiceBase.jsonService().readJsonFromAsset(ASSET_PATH, this._context);
        this._events = ServiceBase.jsonService().gson().fromJson(json, listType);
    }

    @Override
    public List<Event> getEvents() {
        return this._events;
    }

    @Override
    public Date getDateData(String date) {
        Date result = null;
        try {
            c.setTime(Objects.requireNonNull(format.parse(date)));
            result = c.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Date getDateData(Event event) {
        return getDateData(event.getDate());
    }

    @Override
    public int getYear(String date) {
        Date dateData = getDateData(date);
        int year = -1;

        if (dateData != null) {
            year = c.get(Calendar.YEAR) + 1;
        }
        return year;
    }

    @Override
    public int getYear(Event event) {
        return getYear(event.getDate());
    }

    @Override
    public int getMonth(String date) {
        Date dateData = getDateData(date);
        int month = -1;

        if (dateData != null) {
            month = c.get(Calendar.MONTH);
        }
        return month;
    }

    @Override
    public int getMonth(Event event) {
        return getMonth(event.getDate());
    }

    @Override
    public int getDayOfMonth(String date) {
        Date dateData = getDateData(date);
        int day = -1;

        if (dateData != null) {
            day = c.get(Calendar.DATE);
        }
        return day;
    }

    @Override
    public int getDayOfMonth(Event event) {
        return getDayOfMonth(event.getDate());
    }

    @Override
    public List<Event> getEventsByMonth(int month) {
        return this._events.stream()
                .filter(e -> getMonth(e) == month - 1)
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> getEventsByYear(int year) {
        return this._events.stream()
                .filter(e -> getYear(e) == year + 1)
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> getEventsByYearAndMonth(int year, int month) {
        return this._events.stream()
                .filter(e -> getYear(e) == year + 1)
                .filter(e -> getMonth(e) == month - 1)
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> getEventsByDate(String date){
        return this._events.stream()
                .filter(d -> d.getDate() ==  date)
                .collect(Collectors.toList());
    }

    @Override
    public SimpleDateFormat getDateFormat(){
        return this.format;
    }


}
