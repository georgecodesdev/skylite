package com.example.skylite.Services;

import com.example.skylite.Data.Constellation;
import com.example.skylite.Model.ModelConstellationInfo;

import java.util.ArrayList;
import java.util.List;

public class WikiService implements IWikiService {
    @Override
    public ModelConstellationInfo getInfo(Constellation constellation) {
        String image = ServiceBase.constellationService().getImageName(constellation);
        return new ModelConstellationInfo(
                image,
                constellation.getName(),
                constellation.getMeaning(),
                constellation.getStory()
        );
    }

    @Override
    public List<ModelConstellationInfo> getInfo(List<Constellation> constellations) {
        List<ModelConstellationInfo> result = new ArrayList<>();
        constellations.forEach(c -> result.add(getInfo(c)));

        return result;
    }


}
