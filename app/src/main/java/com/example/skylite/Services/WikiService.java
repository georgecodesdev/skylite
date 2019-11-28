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
