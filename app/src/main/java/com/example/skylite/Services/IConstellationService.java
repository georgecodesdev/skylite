package com.example.skylite.Services;

import com.example.skylite.Data.ConstellationDao;

import org.json.JSONException;

public interface IConstellationService {
    void populateTable(ConstellationDao dao) throws JSONException;
    void get();
    void get(String id);
    void getByName(String name);
}
