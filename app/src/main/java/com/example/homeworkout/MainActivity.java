package com.example.homeworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.homeworkout.UserDefined.ExerciseDetails;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<ExerciseDetails> exerciseList = new ArrayList<>();
    public static ArrayList<Integer> indexList = new ArrayList<>();
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addExercise();
        generateRandomIndex();
    }
    private void addExercise()
    {
        // all the exercise will be added to the exerciseList.
        exerciseList.add(new ExerciseDetails(R.drawable.workout_1, "Pushups",new String[]
                {"Increase Functional Strength via Full Body Activation.",
                        "Muscle Stretching for Health and Vitality.",
                        "Improve Your Posture.",
                        "Prevent Lower Back Injuries"}));

        exerciseList.add(new ExerciseDetails(R.drawable.workout_2,"Unnamed Exercise 2", new String[]
                {"Improve Strength and Balance.",
                    "Increased Stabilization and Muscle Activation.",
                    "Increase energy."}));

        exerciseList.add(new ExerciseDetails(R.drawable.workout_5, "Toes touches ",new String[]
                {"It will stretch the hamstrings and the four muscle groups found in the back of thigh.",
                        "MIt also helps to improve your posture, flexibility and balance."}));

        exerciseList.add(new ExerciseDetails(R.drawable.workout_6,"Commando Rows / Alternating plank",new String[]
                {"improves Core Stability.",
                    "Unilateral Strength and Balance.",
                    "improve Total Body Movement."}));

        exerciseList.add(new ExerciseDetails(R.drawable.workout_7,"dummbell lifting",new String[]
                {"Increased Stabilization and Muscle Activation.",
                    "Lead to muscle growth",
                    "increase Cardio Health",
                    "increase bone health"}));

        exerciseList.add(new ExerciseDetails(R.drawable.workout_8,"Swiss ball knee tuck", new String[]
                {"Increase Strength and Balance.",
                    "Muscle growth increase."}));

        exerciseList.add(new ExerciseDetails(R.drawable.workout_11,"decline barbell bench press", new String[]
                {"help you fix imbalances in your chest.",
                    "Increased Pec Activation.",
                    "Less Stress on the Lower Back."}));

        exerciseList.add(new ExerciseDetails(R.drawable.workout_12,"bench press", new String[]
                {"Increased Upper Body Push Strength.",
                    "Predictor of Upper Body Strength.",
                    "Stronger Pec Minor.",
                    "Improved Bone Health."}));
    }
    private void generateRandomIndex()
    {
        indexList.clear();
        for (int i = 0;i < 5; i++)
        {
            flag = true;
            int randomIndex = getRandomIndex();
            for (int tem : indexList)
            {
                if (tem == randomIndex)
                {
                    flag = false;
                    i--;
                    break;
                }
            }
            if (flag)
            {
                indexList.add(randomIndex);
            }
        }
    }
    private int getRandomIndex()
    {//(max - min) + 1
        return new Random().nextInt((exerciseList.size() - 1) + 1 );
    }
}
