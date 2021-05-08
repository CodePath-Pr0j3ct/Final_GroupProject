package com.example.fityet.AlarmComponents;

import androidx.annotation.NonNull;
import android.content.Context;
import android.app.AlarmManager;
import android.content.Intent;
import android.app.PendingIntent;
import java.util.Calendar;

public class Alarm {
    @NonNull
    private int alarmId;

    private int hour, minute;
    private boolean started;
    private boolean cb1, cb2, cb3, cb4, cb5, cb6, cb7;

    public Alarm(int alarmId, int hour, int minute, boolean started, boolean cb1, boolean cb2, boolean cb3, boolean cb4, boolean cb5, boolean cb6, boolean cb7) {
        this.alarmId = alarmId;
        this.hour = hour;
        this.minute = minute;
        this.started = started;

        this.cb1 = cb1;
        this.cb2 = cb2;
        this.cb3 = cb3;
        this.cb4 = cb4;
        this.cb5 = cb5;
        this.cb6 = cb6;
        this.cb7 = cb7;
    }

    public void schedule(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, AlarmReceiver.class);

        intent.putExtra("MONDAY", cb1);
        intent.putExtra("TUESDAY", cb2);
        intent.putExtra("WEDNESDAY", cb3);
        intent.putExtra("THURSDAY", cb4);
        intent.putExtra("FRIDAY", cb5);
        intent.putExtra("SATURDAY", cb6);
        intent.putExtra("SUNDAY", cb7);

        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, alarmId, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // if alarm time has already passed, increment day by 1
        if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        }

            final long RUN_DAILY = 24 * 60 * 60 * 1000;
            alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(),
                    RUN_DAILY,
                    alarmPendingIntent
            );

        this.started = true;
    }
}