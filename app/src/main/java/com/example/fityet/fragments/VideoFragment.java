package com.example.fityet.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.parse.ParseUser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.fityet.Models.VerticalModel;

import com.example.fityet.Adapters.VerticalRecyclerViewAdapter;
import com.example.fityet.Models.HorizontalModel;
import com.example.fityet.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class VideoFragment extends Fragment {

    TextView userGoalName;
    RecyclerView verticalRecyclerView;
    VerticalRecyclerViewAdapter adapter;
    ArrayList<VerticalModel> arrayListVertical;


    public VideoFragment() {
        // Required empty public constructor
    }

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_video, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        arrayListVertical = new ArrayList<>();
        verticalRecyclerView = view.findViewById(R.id.recyclerview);
        userGoalName = view.findViewById(R.id.tvCustomGoal);

        verticalRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL,false));
        verticalRecyclerView.setHasFixedSize(true);

        adapter = new VerticalRecyclerViewAdapter(getActivity().getApplicationContext(), arrayListVertical);
        verticalRecyclerView.setAdapter(adapter);

        //Retrieve pre-made Horizontal Models
        setData();

        //Populate goal textview with logged-in User's selected goal
        ParseUser currentUser = ParseUser.getCurrentUser();
        userGoalName.setText(currentUser.getString("goal"));

        super.onCreate(savedInstanceState);

    }

    //Less redundant way to populate the Horizontal Model
    private void buildModel(ArrayList<HorizontalModel> arrayList, String exerciseName, String videoId){

        HorizontalModel someModel = new HorizontalModel();
        someModel.setName(exerciseName);
        someModel.setId(videoId);
        arrayList.add(someModel);

    }

    private void setData(){

        //Initialize overall screen to contain horizontal models
        VerticalModel mVerticalModel1 = new VerticalModel();

        //Lose fat/Build muscle model
        ArrayList<HorizontalModel> arrayList1 = new ArrayList<>();

        buildModel(arrayList1, "Sit-ups", "jDwoBqPH0jk");
        buildModel(arrayList1, "Push-ups", "IODxDxX7oi4");
        buildModel(arrayList1, "Planks", "ASdvN_XEl_c");
        buildModel(arrayList1, "Pike Push-ups", "sposDXWEB0A");
        buildModel(arrayList1, "Dips", "2z8JmcrW-As");
        buildModel(arrayList1, "Burpees", "dZgVxmf6jkA");
        buildModel(arrayList1, "Jumping Jacks", "UpH7rm0cYbM");
        buildModel(arrayList1, "Squats", "YaXPRqUwItQ");
        buildModel(arrayList1, "Calf Raises", "ommnfVcLWxQ");
        buildModel(arrayList1, "Pull-ups", "eGo4IYlbE5g");

        mVerticalModel1.setArrayList(arrayList1);

        //Gain flexibility/Maintain balance model
        VerticalModel mVerticalModel2 = new VerticalModel();

        ArrayList<HorizontalModel> arrayList2 = new ArrayList<>();

        buildModel(arrayList2, "Standing Crunch with Clap", "Z30Pac8SmLY");
        buildModel(arrayList2, "Lunge Hip Flexor Stretch", "mGxRdw5IOGs");
        buildModel(arrayList2, "Flamingo Stand", "3N1Arv4C1BE");
        buildModel(arrayList2, "Chair Leg Raises", "fAhHS10FDPU");
        buildModel(arrayList2, "Quad Stretch", "FBO9-8nTbsM");
        buildModel(arrayList2, "Butterfly Stretch", "2I4dNZYVIUU");
        buildModel(arrayList2, "Tree Pose", "Fr5kiIygm0c");
        buildModel(arrayList2, "T-Stand With Side Bend", "lgtW65j_UVI");
        buildModel(arrayList2, "Wall Pushups", "EgU3CbtQTlw");
        buildModel(arrayList2, "Side Kick", "RckjjT-T0ZA");

        mVerticalModel2.setArrayList(arrayList2);

        //For You must display exercises best for logged-in User's goal
        //More must display extra exercises
        //If-else statements helps title and order horizontal models according to logged-in User's goal
        ParseUser currentUser = ParseUser.getCurrentUser();

        String stringGoal = currentUser.getString("goal");

        if(stringGoal.equals("Lose fat/Build muscle")){

            mVerticalModel1.setTitle("For You");

            arrayListVertical.add(mVerticalModel1);

            mVerticalModel2.setTitle("More");

            arrayListVertical.add(mVerticalModel2);

        }

        else{

            mVerticalModel2.setTitle("For You");

            arrayListVertical.add(mVerticalModel2);

            mVerticalModel1.setTitle("More");

            arrayListVertical.add(mVerticalModel1);

        }

        //update adapter to display populated array list
        adapter.notifyDataSetChanged();

    }

}
