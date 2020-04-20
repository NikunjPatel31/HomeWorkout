package com.example.homeworkout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homeworkout.UserDefined.ExerciseDetails;
import com.example.homeworkout.recyclerViewAdapter.FeaturesRecyclerView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutSelectionFragment extends Fragment {

    private static final String TAG = "WorkoutSelectionFragmen";

    private ImageView exerciseImage;
    private TextView exerciseTitleTV, minuteValueTV;
    private Button nextBtn, decreaseTimeBtn, increaseTimeBtn;
    private RecyclerView featureRecyclerView;
    private FeaturesRecyclerView adapter;
    private int i = 0;

    public static ArrayList<ExerciseDetails> exerciseList = new ArrayList<>();
    public static ArrayList<Integer> indexList = new ArrayList<>();
    private boolean flag = true;

    NavController navController;

    public WorkoutSelectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_selection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeWidgets(view);
        navController = Navigation.findNavController(view);

        featureRecyclerView.setHasFixedSize(true);
        featureRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        int index = MainActivity.indexList.get(i);

        adapter = new FeaturesRecyclerView(MainActivity.exerciseList.get(index).getFeatures());
        featureRecyclerView.setAdapter(adapter);

        ExerciseDetails exerciseDetails = MainActivity.exerciseList.get(index);
        exerciseImage.setImageResource(exerciseDetails.getImageID());
        exerciseTitleTV.setText(exerciseDetails.getExerciseTitle());
    }

    @Override
    public void onStart() {
        super.onStart();
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = MainActivity.indexList.get(i);
                MainActivity.exerciseList.get(index).setTime(Integer.parseInt(minuteValueTV.getText().toString()));
                minuteValueTV.setText("3");
                if (i < 4)
                {
                    i++;
                    index = MainActivity.indexList.get(i);
                    adapter = new FeaturesRecyclerView(MainActivity.exerciseList.get(index).getFeatures());
                    featureRecyclerView.setAdapter(adapter);
                    ExerciseDetails exerciseDetails = MainActivity.exerciseList.get(index);
                    exerciseImage.setImageResource(exerciseDetails.getImageID());
                    exerciseTitleTV.setText(exerciseDetails.getExerciseTitle());
                }
                else
                {
                    navController.navigate(R.id.action_workoutSelectionFragment_to_setSelectionFragment);
                }
            }
        });

        decreaseTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this will decrease the time...
                if (Integer.parseInt(minuteValueTV.getText().toString()) > 1)
                {
                    int value = Integer.parseInt(minuteValueTV.getText().toString());
                    value--;
                    minuteValueTV.setText(Integer.toString(value));
                }
            }
        });

        increaseTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this will increase the time...
                if (Integer.parseInt(minuteValueTV.getText().toString()) < 60)
                {
                    int value = Integer.parseInt(minuteValueTV.getText().toString());
                    value++;
                    minuteValueTV.setText(Integer.toString(value));
                }
            }
        });
    }

    private void initializeWidgets(View view)
    {
        // all the widgets are initialized in this method.
        exerciseImage = view.findViewById(R.id.exercise_image_view);
        exerciseTitleTV = view.findViewById(R.id.exercise_text_view);
        nextBtn = view.findViewById(R.id.next_button);
        featureRecyclerView = view.findViewById(R.id.features_list_recycler_view);
        decreaseTimeBtn = view.findViewById(R.id.decrease_time_button);
        increaseTimeBtn = view.findViewById(R.id.increase_time_button);
        minuteValueTV = view.findViewById(R.id.minute_value_text_view);
    }
}
