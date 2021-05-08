package com.example.fityet;

import android.app.Application;

import com.example.fityet.Models.Exercise;
import com.parse.Parse;
import com.parse.ParseObject;
import com.example.fityet.Models.User;
import com.example.fityet.Models.Exercise;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;


public class ParseApplication extends Application {

    public static final String CHANNEL_ID = "ALARM_SERVICE_CHANNEL_ID";
    public static final String CHANNEL_NAME = "ALARM_SERVICE_CHANNEL";

    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(User.class);
        ParseObject.registerSubclass(Exercise.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("Uf1nprKg4VOcmHLHlTWyBi149sGS58xhZA5I4obb")
                .clientKey("mN5bB0FIJZYomhiVWuraDyIy9gTjHrPZ9EE6cMl5")
                .server("https://parseapi.back4app.com")
                .build()
        );

    }


    }
