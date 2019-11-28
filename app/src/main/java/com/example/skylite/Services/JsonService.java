package com.example.skylite.Services;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JsonService implements IJsonService {
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
}
