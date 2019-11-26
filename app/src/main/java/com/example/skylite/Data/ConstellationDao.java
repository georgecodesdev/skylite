package com.example.skylite.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ConstellationDao {
    @Query("SELECT * FROM constellation_table")
    LiveData<List<Constellation>> getAll();

    @Query("SELECT * FROM constellation_table WHERE Id == :id")
    LiveData<Constellation> get(String id);
}
