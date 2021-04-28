package com.example.fityet.Models;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fityet.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.parceler.Parcels;

import java.util.List;
import java.util.Vector;

public class DetailActivity extends YouTubeBaseActivity {

    protected static final String YOUTUBE_API_KEY = "AIzaSyBWQ_RUXfBeW6V5O3b61DRCCLJbb1ZLKpY";
    public static final String TAG = "DetailActivity";
    TextView exName;
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        exName = findViewById(R.id.exname);
        youTubePlayerView = findViewById(R.id.player);

        //Exercise exercise = Parcels.unwrap(getIntent().getParcelableExtra("exercise"));
        HorizontalModel exercise = Parcels.unwrap(getIntent().getParcelableExtra("exercise"));
        exName.setText(exercise.getName());
        String youtubeKey = exercise.getId();
        initializeYoutube(youtubeKey);
    }

    private void initializeYoutube(final String youtubeKey) {
        youTubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG, "onInitializationSuccess");
                youTubePlayer.cueVideo(youtubeKey);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "onInitializationFailure");
            }
        });
    }
}