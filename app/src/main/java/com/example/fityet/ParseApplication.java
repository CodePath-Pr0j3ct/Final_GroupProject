package com.example.fityet;

import android.app.Application;

import com.example.fityet.Models.Exercise;
import com.parse.Parse;
import com.parse.ParseObject;
import com.example.fityet.Models.User;
import com.example.fityet.Models.Exercise;


public class ParseApplication extends Application {

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
