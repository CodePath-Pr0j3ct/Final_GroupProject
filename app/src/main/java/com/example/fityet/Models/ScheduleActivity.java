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
import com.parse.ParseUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {

    CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7;
    TextView tvAlarm;
    Spinner exerciseSpinner;
    Button btnSave;
  //  EditText etHour;
  //  EditText etMin;

    int hour, min;
    private final List<String> buildMuscle = new ArrayList<>();
    private final List<String> gainFlex = new ArrayList<>();


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        Intent intent = getIntent();
        String value = intent.getStringExtra("key");

        tvAlarm = findViewById(R.id.tvalarm);
        exerciseSpinner = findViewById(R.id.goalsSpinner);
        cb1 = findViewById(R.id.cbMon);
        cb2 = findViewById(R.id.cbTue);
        cb3 = findViewById(R.id.cbwed);
        cb4 = findViewById(R.id.cbThu);
        cb5 = findViewById(R.id.cbFri);
        cb6 = findViewById(R.id.cbSat);
        cb7 = findViewById(R.id.cbSun);
     //   etHour = findViewById(R.id.etHour);
      //  etMin = findViewById(R.id.etMinute);
/*
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
        gainFlex.add("Side Kick");*/

        tvAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        ScheduleActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //Initialise hour and minute
                        hour = hourOfDay;
                        min = minute;

                        //Store hour and minute in String
                        String time = hour + ":" + minute;

                        //Initialize 24 hours time format
                        @SuppressLint("SimpleDateFormat")
                        SimpleDateFormat f24hrs = new SimpleDateFormat(
                                "HH:mm"
                        );
                        try {
                            Date date = f24hrs.parse(time);
                            //Initialize 12 hours format
                            @SuppressLint("SimpleDateFormat")
                            SimpleDateFormat f12hrs = new SimpleDateFormat(
                                    "hh:mm aa"
                            );
                            //Set selected time on text view
                            tvAlarm.setText(f12hrs.format(date));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, 12, 0, false);
                //Set transparent background
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Displayed prev time
                timePickerDialog.updateTime(hour,min);
                //Show dialog
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

      /*  btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               *//* //Instead of this alarm, we need to call our own apps alarm
                Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                i.putExtra(AlarmClock.EXTRA_HOUR, hour);
                i.putExtra(AlarmClock.EXTRA_MINUTES, min);*//*
                Toast.makeText(ScheduleActivity.this, "Schedule saved", Toast.LENGTH_SHORT).show();
                saveTime(hour, min);
            }
        });*/


    }


    //Function to save time in back4app
    private void saveTime(int hour, int minute) {
        ParseUser user = ParseUser.getCurrentUser();

            user.put("hour", hour);
            user.put("minute", minute);

            //Somehow continues
    }
}
