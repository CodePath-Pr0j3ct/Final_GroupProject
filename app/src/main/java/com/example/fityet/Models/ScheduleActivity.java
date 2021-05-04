package com.example.fityet.Models;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.fityet.R;
import com.example.fityet.RegisterActivity;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.security.AccessController.getContext;

public class ScheduleActivity extends AppCompatActivity {

    CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7;
    TextView tvSetTime;
    Spinner exerciseSpinner;
    Button btnSave;
    ArrayAdapter<String> adapter;
    ParseUser currentUser;
    CheckBox[] checks;

    int hour, min;
    private final List<String> buildMuscle = new ArrayList<>();
    private final List<String> gainFlex = new ArrayList<>();


    @SuppressLint("ResourceType")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);


        tvSetTime = findViewById(R.id.tvalarm);
        exerciseSpinner = findViewById(R.id.ExerciseSpinner);
        cb1 = findViewById(R.id.cbMon);
        cb2 = findViewById(R.id.cbTue);
        cb3 = findViewById(R.id.cbwed);
        cb4 = findViewById(R.id.cbThu);
        cb5 = findViewById(R.id.cbFri);
        cb6 = findViewById(R.id.cbSat);
        cb7 = findViewById(R.id.cbSun);
        checks = new CheckBox[]{cb1,cb2,cb3,cb4,cb5,cb6,cb7};
        //etHour = findViewById(R.id.etHour);
       // etMin = findViewById(R.id.etMinute);

        buildMuscle.add("Sit-ups");
        buildMuscle.add("Push-ups");
        buildMuscle.add("Planks");
        buildMuscle.add("Pike Push-ups");
        buildMuscle.add("Dips");
        buildMuscle.add("Burpees");
        buildMuscle.add("Jumping Jacks");
        buildMuscle.add("Squats");
        buildMuscle.add("Calf Raises");
        buildMuscle.add("Pull-ups");

        gainFlex.add("Standing Crunch with Clap");
        gainFlex.add("Lunge Hip Flexor Stretch");
        gainFlex.add("Flamingo Stand");
        gainFlex.add("Chair Leg Raises");
        gainFlex.add("Quad Stretch");
        gainFlex.add("Butterfly Stretch");
        gainFlex.add("Tree Pose");
        gainFlex.add("T-Stand With Side Bend");
        gainFlex.add("Wall Pushups");
        gainFlex.add("Side Kick");

        currentUser = ParseUser.getCurrentUser();
        if (currentUser.getString("goal").equals("Build muscle")){
            adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, buildMuscle);
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        }
        else{
            adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, gainFlex);
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        }


        exerciseSpinner.setAdapter(adapter);


        tvSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int hourNow = c.get(Calendar.HOUR_OF_DAY);
                int minuteNow = c.get(Calendar.MINUTE);


                TimePickerDialog timePickerDialog = new TimePickerDialog(ScheduleActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                if (minute <= 9){
                                    tvSetTime.setText(hourOfDay + ":0" + minute);
                                }
                                else {
                                    tvSetTime.setText(hourOfDay + ":" + minute);
                                }
                                hour = hourOfDay;
                                min = minute;
                            }
                        }, hourNow, minuteNow, false);
                timePickerDialog.show();
            }
        });





/*
        // Creating adapter for spinner and also reference the current list from first activity to get right info
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, RegisterActivity.registerActivity.selectedArray);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        exerciseSpinner.setAdapter(dataAdapter);*/

     /*   ArrayAdapter<CharSequence> dataAdapter;
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser.getString("goal").equals("Lose fat/Build Muscle")) {
            dataAdapter = ArrayAdapter.createFromResource(this, R.array.lose_fat_build_muscle, android.R.layout.simple_spinner_item);
        } else {
            dataAdapter = ArrayAdapter.createFromResource(this, R.array.gain_flexibility_maintain_balance, android.R.layout.simple_spinner_item);
        }
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //  exerciseSpinner.setAdapter(dataAdapter);
*/


        //GOTTA FIX THIS BUTTON ARGHHHHH
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String daysOfExercise = getDays();
                String exercise = exerciseSpinner.getSelectedItem().toString();
                Exercise exerciseObj = new Exercise();
                exerciseObj.setExercise(exercise);
                exerciseObj.setHour(hour);
                exerciseObj.setMinutes(min);
                exerciseObj.setDaysOfWeek(daysOfExercise);
                exerciseObj.setUser(currentUser);
                exerciseObj.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null){
                            Log.e("Schedule Activity", "Error while saving", e);
                            return;
                        }
                        Log.i("Schedule Activity",  "Exercise Saved");
                    }
                });
            }
        });


    }

    private String getDays() {
        StringBuilder daysOfWeek = new StringBuilder("0000000");
        for (int i = 0; i < daysOfWeek.length(); i++){
            if(checks[i].isChecked()){
                daysOfWeek.setCharAt(i, '1');
            }
        }
        return daysOfWeek.toString();
    }



}
