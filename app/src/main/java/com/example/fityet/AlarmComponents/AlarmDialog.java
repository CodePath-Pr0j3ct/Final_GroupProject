package com.example.fityet.AlarmComponents;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;

import com.example.fityet.DisplayExercise;
import android.widget.TextView;
import android.widget.ImageView;

import com.example.fityet.Models.HorizontalModel;
import com.example.fityet.R;

import org.parceler.Parcels;

public class AlarmDialog extends AppCompatDialogFragment {

    private ImageView alarmThumbnail;

    private TextView alarmExerciseName;

    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.alarm_dialog, null);

        builder.setView(view).setTitle("Exercise Reminder").setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //stop alarm sound and vibration
                Intent intentService = new Intent(getContext(), AlarmService.class);
                getContext().stopService(intentService);

                dialog.dismiss();

            }

        }).setPositiveButton("Commit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //stop alarm sound and vibration
                Intent intentService = new Intent(getContext(), AlarmService.class);
                getContext().stopService(intentService);

                //When user 'commits' to exercise, they will be taken to video countdown activity
                Intent i = new Intent(getContext(), DisplayExercise.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //i.putExtra("exercise", Parcels.wrap(exercise));
                getContext().startActivity(i);

                dialog.dismiss();

            }
        });

        return builder.create();

    }

}
