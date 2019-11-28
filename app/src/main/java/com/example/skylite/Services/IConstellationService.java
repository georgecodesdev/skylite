package com.example.skylite.Services;

import com.example.skylite.Data.Constellation;
import com.example.skylite.Data.ConstellationDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.List;

public interface IConstellationService {
    void populateList();
    void populateList(List<Constellation> constellations);
    void populateTable(ConstellationDao dao) throws JSONException;
    List<Constellation> get();
    String getImageName(Constellation constellation);
}
