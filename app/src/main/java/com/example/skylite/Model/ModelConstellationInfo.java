package com.example.skylite.Model;

import java.io.Serializable;

// A filler model class which will eventually get replaced with a better solution
// Object needs to implement serializable otherwise it cannot be
public class ModelConstellationInfo implements Serializable {

    public String imageName;
    public String title;
    public String descriptionShort;
    public String descriptionLong;

    public ModelConstellationInfo(String imageName, String title, String descriptionShort, String descriptionLong){
        this.imageName = imageName;
        this.title = title;
        this.descriptionShort = descriptionShort;
        this.descriptionLong = descriptionLong;
    }
}
