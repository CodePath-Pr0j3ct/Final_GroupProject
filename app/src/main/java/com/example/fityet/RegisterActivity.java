package com.example.fityet;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.text.TextUtils;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.ParseException;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private EditText etUsername, etPassword, etEmailAddress, etWeight, etHeight;
    private Spinner spinnerGoal;
    //the lists that we use in our spinner
    private final List<String> buildMuscle = new ArrayList<>();
    private final List<String> gainFlex = new ArrayList<>();

    //Array to display in spinner based on user pref
    public List<String> selectedArray = new ArrayList<String>();

    public static RegisterActivity registerActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // initializing  edit text and buttons.
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etEmailAddress = findViewById(R.id.etEmailAddress);
        etWeight = findViewById(R.id.etWeight);
        etHeight = findViewById(R.id.etHeight);
        spinnerGoal = findViewById(R.id.goalsSpinner);
        Button registerBtn = findViewById(R.id.btnRegister);

        /*getListInfo(getResources().getStringArray(R.array.lose_fat_build_muscle), buildMuscle);
        getListInfo(getResources().getStringArray(R.array.gain_flexibility_maintain_balance), gainFlex);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();

        //Items in your first spinner;
        categories.add("Lose fat/Build Muscle");
        categories.add("Gain Flex/Maintain balance");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerGoal.setAdapter(dataAdapter);


        spinnerGoal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ParseUser currentUser = ParseUser.getCurrentUser();
                if(currentUser.getString("goal").equals("Lose fat/Build Muscle")) {
                    selectedArray = buildMuscle;
                }
                else
                    selectedArray = gainFlex;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        // adding on click listener for our button.
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are getting data from our edit text.
                String userName = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String userEmail = etEmailAddress.getText().toString();
                String userGoal = spinnerGoal.getSelectedItem().toString();
                String weight = etWeight.getText().toString();
                String height = etHeight.getText().toString();

                if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    // calling a method to register a user.
                    registerUser(userName, password, userEmail, weight, height, userGoal);
                }
            }
        });

    }

    /*private void getListInfo(String[] stringArray, List<String> list) {
        for (int i = 0; i < stringArray.length; i++) {
            list.add(stringArray[i]);
        }
    }*/

    private void registerUser(String userName, String password, String email, String weight, String height, String goal) {

        ParseUser user = new ParseUser();

        // Set the user's username/password/email/goal
        user.setUsername(userName);
        user.setPassword(password);
        user.setEmail(email);

        //Making sure weight and height is only digits
        if(!TextUtils.isDigitsOnly(weight) || !TextUtils.isDigitsOnly(height))  {
            Toast.makeText(RegisterActivity.this, "Please use only numbers", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            user.put("weight", weight);
            user.put("height", height);
        }
        //custom property is goal
        user.put("goal", goal);

        // calling a method to register the user.
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                // on user registration checking if
                // the error is null or not.
                if (e == null) {
                    // if the error is null we are displaying a toast message and
                    // redirecting our user to new activity and passing the user name.
                    Toast.makeText(RegisterActivity.this, "Registration success", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                    i.putExtra("username", userName);
                    startActivity(i);
                } else {
                    // if we get any error then we are logging out
                    // our user and displaying an error message
                    ParseUser.logOut();
                    Toast.makeText(RegisterActivity.this, "Registration error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

        @Override
        public void onItemSelected (AdapterView < ? > adapterView, View view,int position, long id){
            Toast.makeText(getApplicationContext(), adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }

        @Override
        public void onNothingSelected (AdapterView < ? > parent){

        }
    }