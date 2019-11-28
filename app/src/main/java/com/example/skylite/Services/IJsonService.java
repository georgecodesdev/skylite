package com.example.skylite.Services;

import android.content.Context;

import com.google.gson.Gson;

public interface IJsonService {
    String readJsonFromAsset(String assetPath, Context context);
    Gson gson();
}
