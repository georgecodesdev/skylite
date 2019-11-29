package com.example.skylite.Services;

import android.content.Context;

import com.google.gson.Gson;

/**
 * Kelsey Osos
 * This is the interface contract for JSON services.
 */
public interface IJsonService {
    String readJsonFromAsset(String assetPath, Context context);

    Gson gson();
}
