/*

    Kelsey Osos
ConstellaionViewModel sits atop the repository and acts as a middle man between
it and the UI (the UI shouldn't access the repository directly).
This class is part of the service layer.

 */
package com.example.skylite.Data;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ConstellationViewModel extends AndroidViewModel {
    private ConstellationRepository repository;

    private LiveData<List<Constellation>> constellations;

    public ConstellationViewModel (Application application) {
        super(application);
        repository = new ConstellationRepository(application);
        constellations = repository.getConstellations();
    }

    public LiveData<List<Constellation>> getAllConstellations() { return constellations; }

    public void insert(Constellation constellation) { repository.insert(constellation); }

}
