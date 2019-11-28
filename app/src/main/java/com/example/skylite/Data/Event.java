/*

    Kelsey Osos
This is the basic Model class for Event information.
It represents event data loaded from JSON through the IEventService.
Information is pulled from in-the-sky.org, detailed in the report.

 */
package com.example.skylite.Data;

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
