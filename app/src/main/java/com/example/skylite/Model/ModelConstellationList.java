package com.example.skylite.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class ModelConstellationList implements Serializable {

    public ArrayList<ModelConstellationInfo> constellationInfo;

    public ModelConstellationList(){
        constellationInfo = new ArrayList<>();
    }

    public void addConstellationInfo(ModelConstellationInfo info){
        constellationInfo.add(info);
    }
}
