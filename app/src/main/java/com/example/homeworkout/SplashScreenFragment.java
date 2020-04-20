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
import android.widget.ImageView;

import com.example.homeworkout.UserDefined.ExerciseDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class SplashScreenFragment extends Fragment {

    private static final String TAG = "SplashScreenFragment";

    NavController navController;
    ImageView imageView;

    public SplashScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView = view.findViewById(R.id.logo_image_view);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        navController.navigate(R.id.action_splashScreenFragment_to_workoutSelectionFragment);
                    }
                    catch (Exception e)
                    {
                        Log.d(TAG, "run: Exception: "+e.getLocalizedMessage());
                    }
                }
            }, 2000);
        }
        catch (Exception e)
        {
            Log.d(TAG, "onStart: Exception: "+e.getLocalizedMessage());
        }
//
    }
}
