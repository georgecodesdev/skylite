package com.example.skylite.Services;

import android.content.Context;

import com.example.skylite.Data.Constellation;
import com.example.skylite.Data.ConstellationDao;
import com.example.skylite.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ConstellationService implements IConstellationService {
    private Context _context;
    private List<Constellation> constellations;
    private ConstellationDao dao;
    private final String ASSET_PATH;

    ConstellationService(Context context) {
        this._context = context;
        constellations = new ArrayList<>();
        this.ASSET_PATH = this._context.getResources().getString(R.string.constellation_asset_path);
    }

    public void populateList() {
        Gson gson = new GsonBuilder().create();

        Type listType = new TypeToken<List<Constellation>>() {}.getType();
        String json = ServiceBase.jsonService().readJsonFromAsset(ASSET_PATH, this._context);
        constellations = gson.fromJson(json, listType);
    }

    public void populateList(List<Constellation> constellations) {
        this.constellations = constellations;
    }

    @Override
    public void populateTable(ConstellationDao dao) {
        if (constellations.isEmpty()) {
            populateList();
        }
        this.constellations.forEach(dao::insert);
        this.dao = dao;
    }

    @Override
    public List<Constellation> get() {
        return this.constellations;
    }

    @Override
    public String getImageName(Constellation constellation) {
        return constellation.getId() + "_image";
    }
}
