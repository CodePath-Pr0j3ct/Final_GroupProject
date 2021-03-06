package com.example.fityet.Models;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.fityet.AlarmComponents.Alarm;
import com.example.fityet.R;
import com.example.fityet.fragments.ScheduleFragment;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class ScheduleActivity extends AppCompatActivity {

    CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7;
    TextView tvSetTime;
    Spinner exerciseSpinner;
    Button btnSave;
    Button btnBack;
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
        btnBack = findViewById(R.id.btnBack);

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
        if (currentUser.getString("goal").equals("Lose fat/Build muscle")){
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
                                    if (hourOfDay < 12) {
                                        hourOfDay = (hourOfDay == 0)? 12 : hourOfDay;
                                        tvSetTime.setText(hourOfDay + ":0" + minute + " AM");
                                    }
                                    else{
                                        if (hourOfDay > 12) {
                                            tvSetTime.setText(hourOfDay % 12 + ":0" + minute + " PM");
                                        }
                                        else{
                                            tvSetTime.setText(hourOfDay + ":0" + minute + " PM");
                                        }
                                    }
                                }
                                else {
                                    if (hourOfDay < 12) {
                                        hourOfDay = (hourOfDay == 0)? 12 : hourOfDay;
                                        tvSetTime.setText(hourOfDay + ":" + minute + " AM");
                                    }
                                    else{
                                        if (hourOfDay > 12) {
                                            tvSetTime.setText(hourOfDay % 12 + ":" + minute + " PM");
                                        }
                                        else{
                                            tvSetTime.setText(hourOfDay + ":" + minute + " PM");
                                        }
                                    }
                                }
                                hour = hourOfDay;
                                min = minute;
                            }
                        }, hourNow, minuteNow, false);

                timePickerDialog.show();

            }
        });

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exercise exerciseObj = new Exercise();
                String daysOfExercise = getDays();
                String exercise = exerciseSpinner.getSelectedItem().toString();
                exerciseObj.setExercise(exercise);
                exerciseObj.setHour(hour);
                exerciseObj.setMinutes(min);
                exerciseObj.setDaysOfWeek(daysOfExercise);

                setWeekChecks(exerciseObj);
                exerciseObj.setUser(currentUser);
                exerciseObj.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null){
                            Log.e("Schedule Activity", "Error while saving", e);

                        }

                        Log.i("Schedule Activity",  "Exercise Saved");

                        scheduleAlarm(exerciseObj);

                    }
                });

                clearActivity();

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void onBackPressed(){
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("ScheduleActivity", "Success! popping back stack");
            fm.popBackStack();
        } else {
            Log.i("ScheduleActivity", "nothing on backstack, calling super");
            super.onBackPressed();
        }
        ScheduleFragment.queryExercises();
    }

    private void clearActivity(){

        Toast.makeText(getApplicationContext(), "New alarm set", Toast.LENGTH_SHORT).show();

        tvSetTime.setText(null);

        for (int i = 0; i < 7; i++) {

            checks[i].setChecked(false);

        }

        exerciseSpinner.setSelection(0);

    }

    private void scheduleAlarm(Exercise exerciseObj){

        int alarmId = new Random().nextInt(Integer.MAX_VALUE);

        Alarm alarm = new Alarm(alarmId,
                exerciseObj.getHour(),
                exerciseObj.getMinutes(),
                true,
                exerciseObj.getMondayCheck(),
                exerciseObj.getTuesdayCheck(),
                exerciseObj.getWednesdayCheck(),
                exerciseObj.getThursdayCheck(),
                exerciseObj.getFridayCheck(),
                exerciseObj.getSaturdayCheck(),
                exerciseObj.getSundayCheck()
        );

        alarm.schedule(getApplicationContext());

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

    public void setWeekChecks(Exercise exerciseObj) {

        exerciseObj.setMondayCheck(cb1.isChecked());
        exerciseObj.setTuesdayCheck(cb2.isChecked());
        exerciseObj.setWednesdayCheck(cb3.isChecked());
        exerciseObj.setThursdayCheck(cb4.isChecked());
        exerciseObj.setFridayCheck(cb5.isChecked());
        exerciseObj.setSaturdayCheck(cb6.isChecked());
        exerciseObj.setSundayCheck(cb7.isChecked());

    }

}
