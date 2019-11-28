/*

    Kelsey Osos
This class is the abstraction of the SQLite database.
It manages the instance constellation DAO and versioning/maintenance of the database.

 */
package com.example.skylite.Data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.skylite.Services.ServiceBase;

import org.json.JSONException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Constellation.class}, version = 7, exportSchema = false)
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
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /*
    Refresh constellation info on app start
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                ConstellationDao dao = INSTANCE.constellationDao();
                dao.deleteAll();
                try {
                    ServiceBase.constellationService().populateTable(dao);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
        }
    };
}
