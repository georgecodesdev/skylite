package com.starViewer;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

public class LoadImageTask extends AsyncTask<AssetManager, Void, Bitmap> {
    private String TAG = "LoadImageTask";

    private String assetName;
    StarViewFragment parent;

    public LoadImageTask(StarViewFragment parent, String assetName){
        this.parent = parent;
        this.assetName = assetName;
    }

    @Override
    protected Bitmap doInBackground(AssetManager... params) {
        AssetManager assetManager = params[0];

        try(InputStream istr = assetManager.open(assetName)) {
            Bitmap b = BitmapFactory.decodeStream(istr);
            return b;
        } catch (IOException e) {
            Log.e(TAG, "Could not decode default bitmap: " + e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap){
        parent.LoadImageFinished(assetName, bitmap);
    }
}
