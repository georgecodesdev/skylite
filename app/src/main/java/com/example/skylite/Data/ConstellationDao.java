package com.example.skylite.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * Kelsey Osos
 * This is the data access handler for the Constellation model
 */
@Dao
public interface ConstellationDao {
    @Query("SELECT * FROM constellation_table")
    LiveData<List<Constellation>> getAll();

    @Query("SELECT * FROM constellation_table WHERE Id == :id")
    LiveData<Constellation> get(String id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Constellation constellation);

    @Query("DELETE FROM constellation_table")
    void deleteAll();
}
