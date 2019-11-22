package com.starViewer;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class LoadImageTask extends AsyncTask<AssetManager, Void, Bitmap> {
    private static final String SYNCTAG = "LoadImageTaskSync";
    private static final String ASYNCTAG = "LoadImageTaskAsync";


    private static Map<String,WeakReference<Bitmap>> mapCache = new HashMap<>();

    private String imageName;
    BitmapImageReciver parent;

    public LoadImageTask(BitmapImageReciver parent, String imageName){
        Log.d(SYNCTAG, "Async image load called for : " + imageName);
        this.parent = parent;
        this.imageName = imageName;
    }

    @Override
    protected Bitmap doInBackground(AssetManager... params) {
        Log.d(ASYNCTAG, "Image load STARTED for : " + imageName);
        AssetManager assetManager = params[0];

        if (mapCache.containsKey(imageName) && mapCache.get(imageName).get() != null) {
            Log.d(ASYNCTAG, "Image load CACHE HIT for : " + imageName);
            return mapCache.get(imageName).get();
        }

        try(InputStream istr = assetManager.open(imageName)) {
            Bitmap b = BitmapFactory.decodeStream(istr);
            mapCache.put(imageName, new WeakReference<>(b));
            Log.d(ASYNCTAG, "Image load FINISHED for : " + imageName);
            return b;
        } catch (IOException e) {
            Log.e(ASYNCTAG, "Could not decode default bitmap: " + e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap){
        parent.LoadImageFinished(imageName, bitmap);
        Log.d(ASYNCTAG, "Image load RETURNED for : " + imageName);
    }
}
