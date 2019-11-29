package com.example.skylite.Data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Kelsey Osos
 * This is the repository abstraction for constellation models.
 * It acts as an abstraction layer that sits atop the SQLite database.
 */
public class ConstellationRepository {
    private ConstellationDao constellationDao;
    private LiveData<List<Constellation>> constellations;

    ConstellationRepository(Application application) {
        ConstellationRoomDatabase db = ConstellationRoomDatabase.getDatabase(application);
        constellationDao = db.constellationDao();
        constellations = constellationDao.getAll();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Constellation>> getConstellations() {
        return constellations;
    }

    void insert(Constellation constellation) {
        ConstellationRoomDatabase.databaseWriteExecutor.execute(() -> {
            constellationDao.insert(constellation);
        });
    }
}
