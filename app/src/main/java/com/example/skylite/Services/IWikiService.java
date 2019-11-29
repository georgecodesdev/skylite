package com.example.skylite.Services;

import com.example.skylite.Data.Constellation;
import com.example.skylite.Model.ModelConstellationInfo;
import com.example.skylite.Model.ModelConstellationList;

import java.util.List;

/**
 * Kelsey Osos
 * This is the interface contract for the wiki page's services.
 */
public interface IWikiService {
    ModelConstellationInfo getInfo(Constellation constellation);

    List<ModelConstellationInfo> getInfo(List<Constellation> constellations);

    List<ModelConstellationInfo> getModelConstellationInfo();

    ModelConstellationList getModelConstellationList();
}
