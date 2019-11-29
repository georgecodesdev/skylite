package com.example.skylite.Services;

import com.example.skylite.Data.Event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Kelsey Osos
 * This class provides the interface to the EventsService class
 */
public interface IEventsService {
    void populateList();

    List<Event> getEvents();

    Date getDateData(String date);

    Date getDateData(Event event);

    int getYear(String date);

    int getYear(Event event);

    int getMonth(String date);

    int getMonth(Event event);

    int getDayOfMonth(String date);

    int getDayOfMonth(Event event);

    SimpleDateFormat getDateFormat();

    List<Event> getEventsByMonth(int month);

    List<Event> getEventsByYear(int year);

    List<Event> getEventsByYearAndMonth(int year, int month);

    List<Event> getEventsByDate(String date);
}
