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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

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

    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PostsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {

        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){

        super.onViewCreated(view, savedInstanceState);

        queryUser();

        btnLogOut = view.findViewById(R.id.btnLogOut);

        tvUsername = view.findViewById(R.id.userImage);

        userGoal = view.findViewById(R.id.customGoal);

        userWeight = view.findViewById(R.id.customWeight);

        userHeight = view.findViewById(R.id.customHeight);

        userEmail = view.findViewById(R.id.customEmail);

        userPic = view.findViewById(R.id.userImage);

        tvUsername.setText(ParseUser.getCurrentUser().getUsername());
        userGoal.setText(User.getKeyGoal());
        userWeight.setText(User.getWeight());
        userHeight.setText(User.getHeight());
        userEmail.setText(ParseUser.getCurrentUser().getEmail());
        ParseFile image = User.getAvatar();

        if (image != null) {

            Glide.with(getActivity().getApplicationContext()).load(image).transform(new CircleCrop()).into(userPic);

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

    protected void queryUser() {
        ParseQuery<ParseUser> query = ParseUser.getQuery();

        query.whereEqualTo("user", ParseUser.getCurrentUser());

        query.findInBackground(new FindCallback<ParseUser>() {

            public void done(List<ParseUser> objects, ParseException e) {

                if (e == null) {
                    // The query was successful.
                    Toast.makeText(getActivity(),"User info success",Toast.LENGTH_SHORT).show();

                }

                else {
                    // Something went wrong.
                    Toast.makeText(getActivity(),"User info failure",Toast.LENGTH_SHORT).show();
                    return;

                }

            }

        });

    }

}