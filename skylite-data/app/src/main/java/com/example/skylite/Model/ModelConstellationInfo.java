package com.example.skylite.Model;

import java.io.Serializable;

// A filler model class which will eventually get replaced with a better solution
// Object needs to implement serializable otherwise it cannot be
public class ModelConstellationInfo implements Serializable {

    public String imageName;
    public String title;
    public String descriptionShort;
    public String descriptionLong;

    public ModelConstellationInfo( String title, String descriptionShort, String descriptionLong,String imageName){

        this.title = title;
        this.descriptionShort = descriptionShort;
        this.descriptionLong = descriptionLong;
        this.imageName = imageName;
    }

    public String getId() {
        return  title;
    }

    public String getDescriptionShort() {
        return descriptionShort;
    }

    public String getDescriptionLong() {
        return descriptionLong;
    }
    public String getImage() {
        return imageName;
    }


}
