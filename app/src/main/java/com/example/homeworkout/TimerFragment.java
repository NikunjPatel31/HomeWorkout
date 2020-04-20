package com.example.homeworkout;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimerFragment extends Fragment {

    private static final String TAG = "TimerFragment";

    private TextView exerciseTitleTV, minuteValueTV, secondValueTV, setValueTV;
    private int counter = 59;
    private CountDownTimer countDownTimer;
    private int cycle = 0;
    private int index = 0;
    private long timeLeft;
    private int exerciseCounter = 0;
    private int totalSet = 1;
    private long totalTime;
    private MediaPlayer mediaPlayer;
    private ProgressBar progressBar;

    public TimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeWidgets(view);

        mediaPlayer = MediaPlayer.create(getContext(),R.raw.music);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.start();
            }
        });

        exerciseTitleTV.setText(MainActivity.exerciseList.get(MainActivity.indexList.get(index)).getExerciseTitle());
        totalSet = Integer.parseInt(SetSelectionFragment.setValueTV.getText().toString());
        setValueTV.setText("Set : "+totalSet);

        int setValue = Integer.parseInt(SetSelectionFragment.setValueTV.getText().toString());
        minuteValueTV.setText(Integer.toString(SetSelectionFragment.totalMinute * setValue));
        totalTime = SetSelectionFragment.totalMinute * setValue * 60 * 1000;

        timerStart(totalTime + 1001, true);
    }

    private void timerStart(long duration,boolean flag)
    {
        //this method will start the time.
        countDownTimer = new CountDownTimer(duration - 1001,1000)
        {
            @Override
            public void onTick(long millisUntilFinished) {
                secondValueTV.setText(Integer.toString(counter));

                float percentage = (millisUntilFinished * 100) / totalTime;
                progressBar.setProgress(100 - (int) percentage);
                Log.d(TAG, "onTick: percentage: "+percentage);

                minuteValueTV.setText(""+ ((millisUntilFinished / 1000) / 60));
                timeLeft = millisUntilFinished;
                if(counter == 0)
                {
                    cycle++;
                    counter = 60;

                    if (MainActivity.exerciseList.get(MainActivity.indexList.get(index)).getTime() == cycle)
                    {
                        exerciseCounter++;
                        if (exerciseCounter == 5)
                        {
                            // the set is finished
                            index = -1;
                            totalSet--;
                            setValueTV.setText("Set : "+totalSet);
                        }
                        pauseTimer();
                        index++;
                        exerciseTitleTV.setText(MainActivity.exerciseList.get(MainActivity.indexList.get(index)).getExerciseTitle());
                        cycle = 0;
                    }
                }
                counter--;
            }

            @Override
            public void onFinish() {
                secondValueTV.setText("00");
                //minuteValueTV.setText(""+value);
            }
        };
        countDownTimer.start();
    }

    private void pauseTimer()
    {
        //this method will pause the timer.
        countDownTimer.cancel();
        mediaPlayer.pause();
        new CountDownTimer(10000,1000)
        {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                mediaPlayer.start();
                resumeTimer();
            }
        }.start();
    }

    private void resumeTimer()
    {
        timerStart(timeLeft, false);
    }

    private void initializeWidgets(View view)
    {
        //this method will initialize widgets.
        exerciseTitleTV = view.findViewById(R.id.exercise_text_view);
        minuteValueTV = view.findViewById(R.id.minute_value_text_view);
        secondValueTV = view.findViewById(R.id.second_value_text_view);
        setValueTV = view.findViewById(R.id.set_value_text_view);
        progressBar = view.findViewById(R.id.progress_bar);
    }
}
