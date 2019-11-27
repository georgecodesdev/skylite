package com.example.skylite.Services;

import android.content.Context;

import com.example.skylite.Data.Constellation;
import com.example.skylite.Data.ConstellationDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ConstellationService implements IConstellationService {
    private Context _context;
    private List<Constellation> constellations;
    private ConstellationDao dao;

    public ConstellationService(Context context) {
        this._context = context;
        constellations = new ArrayList<>();
    }

    public void populateList() {
        Gson gson = new GsonBuilder().create();

        Type listType = new TypeToken<List<Constellation>>() {}.getType();
        constellations = gson.fromJson(readJSONFromAsset(), listType);
    }

    public void populateList(List<Constellation> constellations) {
        this.constellations = constellations;
    }

    @Override
    public void populateTable(ConstellationDao dao) {
        if (constellations.isEmpty()) {
            populateList();
        }
//        Gson gson = new GsonBuilder().create();
//
//        Type listType = new TypeToken<List<Constellation>>() {}.getType();
//        List<Constellation> fromJson = gson.fromJson(readJSONFromAsset(), listType);
//        if (fromJson != null) {
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

    private String readJSONFromAsset() {
        String json;
        try {
            InputStream is = this._context.getAssets().open("constellation_data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
