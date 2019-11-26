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
import java.util.List;

public class ConstellationService implements IConstellationService {
    private Context _context;

    public ConstellationService(Context context) {
        this._context = context;
    }

    @Override
    public void populateTable(ConstellationDao dao) {
        Gson gson = new GsonBuilder().create();

        Type listType = new TypeToken<List<Constellation>>() {}.getType();
        List<Constellation> fromJson = gson.fromJson(readJSONFromAsset(), listType);
        if (fromJson != null) {
            fromJson.forEach(dao::insert);
        }
    }

    @Override
    public void get() {

    }

    @Override
    public void get(String id) {

    }

    @Override
    public void getByName(String name) {

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
