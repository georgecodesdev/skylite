package com.example.skylite.Model;

// A filler model class which will eventually get replaced with a better solution
// Object needs to implement serializable otherwise it cannot be
public class ModelConstellationDateTimeInfo {

    public String date;
    public String eventTitle;
    public String eventDescription;

    public ModelConstellationDateTimeInfo(String date, String eventTitle, String eventDescription){
        this.date = date;
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
    }

}
