/*

    Kelsey Osos
This repository manages database access with SQLite using the DAO.
It maintains a local DAO singleton which handles all DB accesses.

*/
package com.example.skylite.Data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ConstellationRepository {
    private ConstellationDao constellationDao;
    private LiveData<List<Constellation>> constellations;

    ConstellationRepository(Application application) {
        ConstellationRoomDatabase db = ConstellationRoomDatabase.getDatabase(application);
        constellationDao = db.constellationDao();
        constellations = constellationDao.getAll();
    }

    LiveData<List<Constellation>> getConstellations() {
        return constellations;
    }

    void insert(Constellation constellation) {
        ConstellationRoomDatabase.databaseWriteExecutor.execute(() -> {
            constellationDao.insert(constellation);
        });
    }
}
