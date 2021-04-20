package com.example.fityet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fityet.MainActivity;
import com.example.fityet.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (ParseUser.getCurrentUser() != null){
            goMainActivity();
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick for login");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                //loginUser(username, password);
            }
        });
    }

//    private void loginUser(String username, String password) {
//        Log.i(TAG, "Attempting to login to user: " + username);
//        // TODO: navigate to main activity if user signs in properly
//        ParseUser.logInInBackground(username, password, new LogInCallback() {
//            @Override
//            public void done(ParseUser user, ParseException e) {
//                if (e != null) {
//                    Log.e(TAG, "Issue with login", e);
//                    Toast.makeText(LoginActivity.this, "Issue with login!",
//                            Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                goMainActivity();
//                Toast.makeText(LoginActivity.this, "Successfully logged in.",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}