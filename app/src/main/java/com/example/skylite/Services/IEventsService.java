package com.example.skylite.Services;

import com.example.skylite.Data.Event;

import java.util.Date;
import java.util.List;

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
    List<Event> getEventsByMonth(int month);
    List<Event> getEventsByYear(int year);
    List<Event> getEventsByYearAndMonth(int year, int month);
}
