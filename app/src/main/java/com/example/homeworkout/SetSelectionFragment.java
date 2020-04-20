package com.example.homeworkout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SetSelectionFragment extends Fragment {

    private Button decreaseSetBtn, increaseSetBtn, startBtn;
    public static TextView setValueTV;
    private TextView totalMinuteTV;
    static int totalMinute;

    NavController navController;

    public SetSelectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_selection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeWidgets(view);
        navController = Navigation.findNavController(view);
        totalMinute = getTotalWorkoutMinute();
        totalMinuteTV.setText(Integer.toString(getTotalWorkoutMinute()) + " minutes");

    }

    @Override
    public void onStart() {
        super.onStart();
        decreaseSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this will decrease the set value.
                if (Integer.parseInt(setValueTV.getText().toString()) > 1)
                {
                    int value = Integer.parseInt(setValueTV.getText().toString());
                    value--;
                    totalMinuteTV.setText((totalMinute * value) + " minutes");
                    setValueTV.setText(Integer.toString(value));
                }
            }
        });

        increaseSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this will increase the set value.
                if (Integer.parseInt(setValueTV.getText().toString()) < 40)
                {
                    int value = Integer.parseInt(setValueTV.getText().toString());
                    value++;
                    totalMinuteTV.setText((totalMinute * value) + " minutes");
                    setValueTV.setText(Integer.toString(value));
                }
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this will start the exercise and will take to the TimerFragment.
                navController.navigate(R.id.action_setSelectionFragment_to_timerFragment);
            }
        });
    }

    private void initializeWidgets(View view)
    {
        //this method will initialize all the widgets in the fragment.
        decreaseSetBtn = view.findViewById(R.id.decrease_set_button);
        increaseSetBtn = view.findViewById(R.id.increase_set_button);
        startBtn = view.findViewById(R.id.start_button);
        setValueTV = view.findViewById(R.id.set_value_text_view);
        totalMinuteTV = view.findViewById(R.id.total_minute_value_text_view);
    }
    private int getTotalWorkoutMinute()
    {
        int minute = 0;
        for (int i = 0; i < MainActivity.indexList.size();i++)
        {
            minute += MainActivity.exerciseList.get(MainActivity.indexList.get(i)).getTime();
        }
        return minute;
    }
}
