package com.example.skylite.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Constellation.class}, version = 1, exportSchema = false)
public abstract class ConstellationRoomDatabase extends RoomDatabase {
    public abstract ConstellationDao constellationDao();

    private static volatile ConstellationRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ConstellationRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ConstellationRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ConstellationRoomDatabase.class, "constellation_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
