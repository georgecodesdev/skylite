package com.starViewer;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

public class LoadImageTask extends AsyncTask<AssetManager, Void, Bitmap> {
    private static final String TAG = "LoadImageTask";

    //TODO: expand map caching to hold multiple maps
    private static WeakReference<Bitmap> lastBitmap = new WeakReference<>(null);
    private static String lastName;

    private String imageName;
    BitmapImageReciver parent;

    public LoadImageTask(BitmapImageReciver parent, String imageName){
        this.parent = parent;
        this.imageName = imageName;
    }

    @Override
    protected Bitmap doInBackground(AssetManager... params) {
        AssetManager assetManager = params[0];

        if (imageName.equals(lastName) && lastBitmap.get() != null) {
            return lastBitmap.get();
        }

        try(InputStream istr = assetManager.open(imageName)) {
            Bitmap b = BitmapFactory.decodeStream(istr);
            lastBitmap = new WeakReference<>(b);
            lastName = imageName;
            return b;
        } catch (IOException e) {
            Log.e(TAG, "Could not decode default bitmap: " + e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap){
        parent.LoadImageFinished(imageName, bitmap);
    }
}
