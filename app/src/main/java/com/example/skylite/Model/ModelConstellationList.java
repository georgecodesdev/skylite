package com.example.skylite.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ModelConstellationList implements Serializable {
    private String imageName;
    private String title;
    private String descriptionShort;


    public ModelConstellationList(String imageName, String title, String descriptionShort){
        this.imageName = imageName;
        this.title = title;
        this.descriptionShort = descriptionShort;

    }

    public String getImageName() {
        return imageName;
    }

    public String getTitle() {
        return title;
    }

    public String getDescriptionShort() {
        return descriptionShort;
    }


    private ArrayList<ModelConstellationInfo> constellationInfo;

    public ModelConstellationList(){
        constellationInfo = new ArrayList<>();
    }

    public void addConstellationInfo(ModelConstellationInfo info){
        constellationInfo.add(info);
    }

    public void addConstellationInfo(List<ModelConstellationInfo> info) {
        info.forEach(this::addConstellationInfo);
    }

    public List<ModelConstellationInfo> getConstellationInfo() {
        return this.constellationInfo;
    }
}
