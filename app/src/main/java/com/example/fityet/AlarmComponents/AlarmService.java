package com.example.fityet.AlarmComponents;

import com.example.fityet.R;
import android.app.Service;
import android.os.Vibrator;
import android.media.MediaPlayer;
import android.content.Intent;
import android.content.Context;
import android.app.PendingIntent;
import android.app.Notification;
import androidx.annotation.Nullable;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import com.example.fityet.MainActivity;

public class AlarmService extends Service {
    private MediaPlayer mediaPlayer;
    private Vibrator vibrator;
    public static final String CHANNEL_ID = "ALARM_SERVICE_CHANNEL";

    @Override
    public void onCreate() {
        super.onCreate();

        mediaPlayer = MediaPlayer.create(this, R.raw.alarm);
        mediaPlayer.setLooping(true);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle("FitYet Alarm").setContentText("Exercise alarm time").setSmallIcon(R.drawable.ic_baseline_fitness_center_24).setContentIntent(pendingIntent).build();

        mediaPlayer.start();

        long[] pattern = { 0, 500, 1000 };
        vibrator.vibrate(pattern, 0);

        startForeground(1, notification);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mediaPlayer.stop();
        vibrator.cancel();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}