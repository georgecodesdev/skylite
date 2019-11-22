/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.starViewer;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.skylite.R;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.util.HashMap;
import java.util.Map;

public class StarViewFragment extends Fragment implements BitmapImageReciver {
    private final String TAG = "StarViewFragment";
    private final String MAINMAP = "starMap2.jpg";

    private VrPanoramaView panoWidgetView;
    private Button swapButton;

    private Map<String,Bitmap> maps;
    private VrPanoramaView.Options viewOptions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.starview_fragment, container,false);
        panoWidgetView = (VrPanoramaView) rootView.findViewById(R.id.pano_view);
        swapButton = rootView.findViewById(R.id.button);
        swapButton.setOnClickListener(new SwapButtonListener());

        maps = new HashMap<>();

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewOptions = new VrPanoramaView.Options();
        viewOptions.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;

        LoadImageAsync(MAINMAP);
        LoadImageAsync("WorldMap.jpeg");
    }

    private void LoadImageAsync(String imageName){
        LoadImageTask task = new LoadImageTask(this, imageName);
        task.execute(getActivity().getAssets());
    }

    @Override
    public void onPause() {
        panoWidgetView.pauseRendering();
        super.onPause();
    }

    @Override
    public void onResume() {
        panoWidgetView.resumeRendering();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        panoWidgetView.shutdown();
        super.onDestroy();
    }

    public synchronized void LoadImageFinished(String name, Bitmap bitmap){
        maps.put(name,bitmap);

        if(name.equals(MAINMAP)){
            setCurrentImage(MAINMAP);
        }
    }

    private void setCurrentImage(String name){
        panoWidgetView.loadImageFromBitmap(maps.get(name), viewOptions);
    }

    ///
    ///Event Liseners
    ///
    private class SwapButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            setCurrentImage("starMap2.jpg");
        }

    }
}
