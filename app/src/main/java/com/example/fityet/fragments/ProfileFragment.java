package com.example.fityet.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.Glide;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.fityet.Models.User;
import android.widget.TextView;
import com.parse.ParseFile;
import com.example.fityet.LoginActivity;
import com.parse.ParseQuery;
import com.parse.FindCallback;
import android.content.Intent;
import java.util.List;
import android.widget.Toast;
import com.parse.ParseException;
import android.widget.ImageView;
import com.parse.ParseUser;
import com.example.fityet.R;

/**
 * A simple {@link Fragment} subclass.
 */

public class ProfileFragment extends Fragment {


    private Button btnLogOut;
    private ImageView userPic;
    private TextView tvUsername;
    private static TextView userGoal;
    private TextView userWeight;
    private TextView userHeight;
    private TextView userEmail;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        queryUser();

        btnLogOut = view.findViewById(R.id.btnLogOut);
        tvUsername = view.findViewById(R.id.userName);
        userGoal = view.findViewById(R.id.customGoal);
        userWeight = view.findViewById(R.id.customWeight);
        userHeight = view.findViewById(R.id.customHeight);
        userEmail = view.findViewById(R.id.customEmail);
        userPic = view.findViewById(R.id.userImage);

        ParseUser currentUser = ParseUser.getCurrentUser();
        tvUsername.setText(currentUser.getUsername());
        userHeight.setText(currentUser.getString("height"));
        userWeight.setText(currentUser.getString("weight"));
        userGoal.setText(currentUser.getString("goal"));
        userEmail.setText(currentUser.getEmail());

        ParseFile image = currentUser.getParseFile("profilepic");

        //Taking profile picture from back4app
        if (image != null) {
            String imageURL = image.getUrl();
            Glide.with(getContext())
                    .load(imageURL)
                    .transform(new CircleCrop())
                    .into(userPic);
        }

            btnLogOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ParseUser.logOut();
                    Intent i = new Intent(getContext(), LoginActivity.class);
                    startActivity(i);
                }
            });

        }

        protected void queryUser () {
            ParseQuery<ParseUser> query = ParseUser.getQuery();

            query.whereEqualTo("user", ParseUser.getCurrentUser());

            query.findInBackground(new FindCallback<ParseUser>() {

                public void done(List<ParseUser> objects, ParseException e) {

                    if (e == null) {
                        // The query was successful.
                        Toast.makeText(getActivity(), "User info success", Toast.LENGTH_SHORT).show();

                    } else {
                        // Something went wrong.
                        Toast.makeText(getActivity(), "User info failure", Toast.LENGTH_SHORT).show();
                        return;

                    }
                }
            });
        }
    }
