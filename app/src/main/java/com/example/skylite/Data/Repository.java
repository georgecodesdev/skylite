/*

    Kelsey Osos
This Repository manages all ModelConstellationInfo data in the app,
ensuring that we don't have multiple sources.

 */
package com.example.skylite.Data;

import com.example.skylite.Model.ModelConstellationInfo;
import com.example.skylite.Model.ModelConstellationList;
import com.example.skylite.Services.ServiceBase;

import java.util.List;

public class Repository {
    private static ModelConstellationList modelConstellationList;

    public static void init() {
        modelConstellationList = ServiceBase.wikiService().getModelConstellationList();
    }

    public static ModelConstellationList getModelConstellationList() {
        return modelConstellationList;
    }

    public static List<ModelConstellationInfo> getModelConstellationInfos() {
        return modelConstellationList.getConstellationInfo();
    }

    public static void setModelConstellationList(ModelConstellationList input) {
        modelConstellationList = input;
    }
}
