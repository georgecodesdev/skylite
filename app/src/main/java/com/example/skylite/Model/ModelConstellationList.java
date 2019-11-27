package com.example.skylite.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ModelConstellationList implements Serializable {

    public ArrayList<ModelConstellationInfo> constellationInfo;

    public ModelConstellationList(){
        constellationInfo = new ArrayList<>();
    }

    public void addConstellationInfo(ModelConstellationInfo info){
        constellationInfo.add(info);
    }

    public void addConstellationInfo(List<ModelConstellationInfo> info) {
        info.forEach(this::addConstellationInfo);
    }
}
