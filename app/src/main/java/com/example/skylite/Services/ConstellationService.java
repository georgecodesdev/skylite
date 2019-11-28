/*

    Kelsey Osos
This service handles all repository accesses for Constellation data.
It abstracts the "dirt work" of pulling assets and populating the table, and keeps
a record of the constellation information and needed transformations.

 */
package com.example.skylite.Services;

import android.content.Context;

import com.example.skylite.Data.Constellation;
import com.example.skylite.Data.ConstellationDao;
import com.example.skylite.R;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ConstellationService implements IConstellationService {
    private Context _context;
    private List<Constellation> constellations;
    private final String ASSET_PATH;

    ConstellationService(Context context) {
        this._context = context;
        constellations = new ArrayList<>();
        this.ASSET_PATH = this._context.getResources().getString(R.string.constellation_asset_path);
    }

    // Maintain a local list of constellation data from the local JSON file
    @Override
    public void populateList() {
        Type listType = new TypeToken<List<Constellation>>() {}.getType();
        String json = ServiceBase.jsonService().readJsonFromAsset(ASSET_PATH, this._context);
        constellations = ServiceBase.jsonService().gson().fromJson(json, listType);
    }

    @Override
    public void populateList(List<Constellation> constellations) {
        this.constellations = constellations;
    }

    // Use constellation data in list and the DAO to populate the database
    @Override
    public void populateTable(ConstellationDao dao) {
        if (constellations.isEmpty()) {
            populateList();
        }
        this.constellations.forEach(dao::insert);
    }

    @Override
    public List<Constellation> get() {
        return this.constellations;
    }

    // Abstract details of getting image names for easy assets access
    @Override
    public String getImageName(Constellation constellation) {
        return constellation.getId() + "_image";
    }
}
