package com.example.skylite.starviewer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.skylite.R;

public class StarViewFragment extends Fragment {
    private static final String TAG = "VideoActivity";
    private static final int READ_EXTERNAL_STORAGE_PERMISSION_ID = 1;
    private MonoscopicView videoView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.video_activity, container, false);

        // Configure the MonoscopicView which will render the video and UI.
        videoView = (MonoscopicView) inflatedView.findViewById(R.id.video_view);
        VideoUiView videoUi = (VideoUiView) inflatedView.findViewById(R.id.video_ui_view);
        videoView.initialize(videoUi);
        videoUi.init(videoView);

        ViewGroup root = (ViewGroup) inflatedView.findViewById(R.id.activity_root);
        for (int i = 0; i < root.getChildCount(); ++i) {
            root.getChildAt(i).setVisibility(View.VISIBLE);
        }
        inflatedView.findViewById(R.id.permission_button).setVisibility(View.GONE);
        videoView.loadMedia();

        return inflatedView;
    }

    @Override
    public void onResume() {
        super.onResume();
        videoView.onResume();
    }

    @Override
    public void onPause() {
        // MonoscopicView is a GLSurfaceView so it needs to pause & resume rendering. It's also
        // important to pause MonoscopicView's sensors & the video player.
        videoView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        videoView.destroy();
        super.onDestroy();
    }
}
