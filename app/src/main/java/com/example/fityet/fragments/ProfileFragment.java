package com.example.fityet.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.Glide;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.fityet.DisplayExercise;
import com.parse.GetCallback;
import com.parse.ParseFile;
import com.example.fityet.LoginActivity;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.FindCallback;
import android.content.Intent;
import java.util.List;
import java.util.Objects;

import com.parse.ParseException;
import android.widget.ImageView;
import com.parse.ParseUser;
import com.example.fityet.R;

/**
 * A simple {@link Fragment} subclass.
 */

public class ProfileFragment extends Fragment {

    private Button btnLogOut;
    private ImageView userPic;
    private TextView tvUsername;
    private static TextView userGoal;
    private TextView userWeight;
    private TextView userHeight;
    private TextView userEmail;
    private ProgressBar userProgress;
    public static final String TAG = "Profile Fragment";
    private TextView tvProgress;
    private ParseUser currentUser;
    private DisplayExercise timerData;
    int boost = 0;

    private final Handler handler = new Handler();

    //new
    int progress;


    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        queryUser();

        userProgress = view.findViewById(R.id.userProgress);
        btnLogOut = view.findViewById(R.id.btnLogOut);
        tvUsername = view.findViewById(R.id.userName);
        userGoal = view.findViewById(R.id.customGoal);
        userWeight = view.findViewById(R.id.customWeight);
        userHeight = view.findViewById(R.id.customHeight);
        userEmail = view.findViewById(R.id.customEmail);
        userPic = view.findViewById(R.id.userImage);
        tvProgress = view.findViewById(R.id.pbProgress);

        currentUser = ParseUser.getCurrentUser();
        tvUsername.setText(currentUser.getUsername());
        userHeight.setText(currentUser.getString("height"));
        userWeight.setText(currentUser.getString("weight"));
        userGoal.setText(currentUser.getString("goal"));
        userEmail.setText(currentUser.getEmail());


        // currentUser.put("Progress", 0);

        //Retrieving the counter value from backend first
        progress = currentUser.getInt("progress");

        //Assigning it to the current user in backend
        currentUser.put("progress", progress);

        //Taking the progress value from backend and assigning it in the progress bar
        userProgress.setProgress(currentUser.getInt("progress"));
        tvProgress.setText(progress + "/" + userProgress.getMax());


        //Checking to see if the progress is greater than the maxValue (=100 )
        if (progress >= 100) {
            //Resetting progress
            progress = 0;
            //Setting the progress bar to 0
            userProgress.setProgress(progress);
            //Saving new progress (=0) in backend
            currentUser.put("progress", progress);
            currentUser.saveInBackground();
            Log.i(TAG, "The progress should have reseetted!");
            tvProgress.setText(progress + "/" + userProgress.getMax());
        }

        // Checking if the timer has stopped,
        // taking timerCancel (boolean value) from DisplayExercise.java
        if (DisplayExercise.timerCancel) {
            progress = progress + 5;
            userProgress.setProgress(currentUser.getInt("progress"));
            userProgress.setProgress(progress);
            currentUser.put("progress", progress);
            currentUser.saveInBackground();
            tvProgress.setText(progress + "/" + userProgress.getMax());
        }


            ParseFile image = currentUser.getParseFile("profilepic");

            //Taking profile picture from back4app
            if (image != null) {
                String imageURL = image.getUrl();
                Glide.with(getContext())
                        .load(imageURL)
                        .transform(new CircleCrop())
                        .into(userPic);
            }

            btnLogOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ParseUser.logOut();
                    Intent i = new Intent(getContext(), LoginActivity.class);
                    startActivity(i);
                }
            });

        }



        protected void queryUser () {
            ParseQuery<ParseUser> query = ParseUser.getQuery();

            query.whereEqualTo("user", ParseUser.getCurrentUser());

            query.findInBackground(new FindCallback<ParseUser>() {

                public void done(List<ParseUser> objects, ParseException e) {

                    if (e == null) {
                        // The query was successful.


                        Log.i(TAG, "User info success");


                    } else {
                        // Something went wrong.
                        Log.e(TAG, "User info failure");
                        return;

                    }
                }
            });
        }
    }
