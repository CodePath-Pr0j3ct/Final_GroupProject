package com.example.fityet.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fityet.DisplayExercise;
import com.example.fityet.Models.DetailActivity;
import com.example.fityet.AlarmComponents.AlarmDialog;

import com.example.fityet.R;

import org.parceler.Parcels;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class TimerFragment extends Fragment {

    TextView tvCountdown, tvComingUp, tvExercise;
    ImageView tvThumbnail;

    CountDownTimer countdownTimer;
    long milliRemaining = 6000;
    public static boolean timerRunning;

    public TimerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        tvCountdown = view.findViewById(R.id.tvCountdown);

        tvComingUp = view.findViewById(R.id.tvComingUp);

        tvExercise = view.findViewById(R.id.tvExercise);

        tvThumbnail = view.findViewById(R.id.thumbNail);

        startStop();

        updateTimer();

    }

    public void openDialog(){

        AlarmDialog alarmDialog = new AlarmDialog();

        alarmDialog.show(getActivity().getSupportFragmentManager(), "Alarm dialog");


    }

    public void startStop() {

            startTimer();

    }

    public void startTimer() {

        countdownTimer = new CountDownTimer(milliRemaining, 1000) {

            @Override
            public void onTick(long l) {

                milliRemaining = l;

                updateTimer();

            }

            public void onFinish() {

            }

        }.start();

        timerRunning = true;

    }

    public void updateTimer(){

        int minutes = (int) (milliRemaining / 1000) / 60;

        int seconds = (int) (milliRemaining /1000) % 60;

        int hours = (int) (milliRemaining / 3600000);

        String timeRemaining = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);

        tvCountdown.setText(timeRemaining);

        if(hours == 0 && minutes == 0 && seconds == 0){

            openDialog();

        }

        }

    }
