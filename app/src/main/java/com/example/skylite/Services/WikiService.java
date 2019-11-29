/*

    Kelsey Osos
Our constellation data contains some attributes unneeded by the current version
of the app, but we want to keep it around.
This service is essentially a mapping profile between the Constellation model and
the
ModelConstellationInfo and List used by the Wiki screen.

 */
package com.example.skylite.Services;

import com.example.skylite.Data.Constellation;
import com.example.skylite.Model.ModelConstellationInfo;
import com.example.skylite.Model.ModelConstellationList;

import java.util.ArrayList;
import java.util.List;

public class WikiService implements IWikiService {
    private List<ModelConstellationInfo> modelConstellationInfo;
    private ModelConstellationList modelConstellationList;

    WikiService() {
        this.modelConstellationInfo = new ArrayList<>();
        this.modelConstellationList = new ModelConstellationList();
    }

    @Override
    public ModelConstellationInfo getInfo(Constellation constellation) {
        // We don't store image names, so the constellationService creates it for us
        String image = ServiceBase.constellationService().getImageName(constellation);
        ModelConstellationInfo model = new ModelConstellationInfo(
                image,
                constellation.getName(),
                constellation.getMeaning(),
                constellation.getStory()
        );
        this.modelConstellationInfo.add(model);
        this.modelConstellationList.addConstellationInfo(model);
        return model;
    }

    @Override
    public List<ModelConstellationInfo> getInfo(List<Constellation> constellations) {
        List<ModelConstellationInfo> result = new ArrayList<>();
        constellations.forEach(c -> result.add(getInfo(c)));

        return result;
    }

    @Override
    public List<ModelConstellationInfo> getModelConstellationInfo() {
        return this.modelConstellationInfo;
    }

    @Override
    public ModelConstellationList getModelConstellationList() {
        return this.modelConstellationList;
    }
}
