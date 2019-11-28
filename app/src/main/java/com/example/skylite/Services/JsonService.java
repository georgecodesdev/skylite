/*

    Kelsey Osos
This class handles access of the local JSON files and parsing them to strings.
It also ensures we only instantiate GSON once.

 */
package com.example.skylite.Services;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JsonService implements IJsonService {
    private Gson _gson;

    JsonService() {
        this._gson = new GsonBuilder().create();
    }

    @Override
    public String readJsonFromAsset(String assetPath, Context context) {
        String json;
        try {
            InputStream is = context.getAssets().open(assetPath);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public Gson gson() {
        return this._gson;
    }
}
