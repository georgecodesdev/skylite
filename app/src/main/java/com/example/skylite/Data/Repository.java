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

/**
 * Kelsey Osos
 * This is a singleton repository for persistence in the local storage.
 * It ensures ModelConstellationList and ModelConstellationInfo are not replicated.
 */
public class Repository {
    private static ModelConstellationList modelConstellationList;

    public static void init() {
        modelConstellationList = ServiceBase.wikiService().getModelConstellationList();
    }

    public static ModelConstellationList getModelConstellationList() {
        return modelConstellationList;
    }

    public static void setModelConstellationList(ModelConstellationList input) {
        modelConstellationList = input;
    }
}
