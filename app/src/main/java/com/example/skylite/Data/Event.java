package com.example.skylite.Data;

/**
 * Kelsey Osos
 * This is the entity model for SQLite
 */
public class Event {
    private String ShortDescription;
    private String Date;
    private String LongDescription;

    public Event(String shortDescription, String date, String longDescription) {
        ShortDescription = shortDescription;
        Date = date;
        LongDescription = longDescription;
    }

    public String getShortDescription() {
        return ShortDescription;
    }

    public String getDate() {
        return Date;
    }

    public String getLongDescription() {
        return LongDescription;
    }
}
