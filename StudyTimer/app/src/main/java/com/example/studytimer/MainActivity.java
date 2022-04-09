package com.example.studytimer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView timerText;
    TextView timerDetail;
    EditText taskName;
    String lastTask;
    String lastTaskState;
    ImageButton playButton;
    ImageButton pauseButton;
    ImageButton stopButton;
    double time = 0.0;
    String timeState;
    Timer timer;
    TimerTask timerTask;
    boolean  timerStarted = false;
    boolean paused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = new Timer();
        timerDetail = (TextView) findViewById(R.id.timerDetail);
        timerText = (TextView) findViewById(R.id.timerText);
        taskName = (EditText) findViewById(R.id.taskName);
        playButton = (ImageButton) findViewById(R.id.playButton);
        pauseButton = (ImageButton) findViewById(R.id.pauseButton);
        stopButton = (ImageButton) findViewById(R.id.stopButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playTimer();
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseTimer();
            }
        });
        if(savedInstanceState != null) {
            time =  savedInstanceState.getDouble(timeState);
            timerText.setText(getTimerText());
            timerDetail.setText(savedInstanceState.getString("lastTaskState"));
            if(timerStarted == false){
                playTimer();
            }
        }
    }

    public void stopTimer() {
        if(timerStarted == true || paused == true) {
            timerTask.cancel();
            timerText.setText("00:00:00");
            timerStarted = false;
            paused = false;
            setTimerDetail();
            time = 0.0;
        }else {
            if (paused == false)  {
                Toast.makeText(getApplicationContext(), "Please start timer first", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void pauseTimer() {
        if (timerStarted == true) {

            timerTask.cancel();
            timerStarted = false;
            paused = true;
        }
        else {
            Toast.makeText(getApplicationContext(), "Please start timer first", Toast.LENGTH_SHORT).show();
        }
    }
    public void playTimer() {
        if (timerStarted == false) {
            paused = false;
            timerStarted=true;
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            time++;
                            timerText.setText(getTimerText());
                        }
                    });
                }
            };
            timer.scheduleAtFixedRate(timerTask, 0, 1000);
        }
        else {
            Toast.makeText(getApplicationContext(), "Already Started", Toast.LENGTH_SHORT).show();
        }
    }


    private String getTimerText()
    {
        int rounded = (int) Math.round(time);

        int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;
        int hours = ((rounded % 86400) / 3600);

        return String.format("%02d",hours) + ":" + String.format("%02d",minutes) + ":" + String.format("%02d",seconds);

    }

    public void setTimerDetail() {
        String timeSpent = getTimerText();
        String nameTask = taskName.getText().toString();

        if(nameTask.equals("")) {
            lastTask = ("You spent " + timeSpent +" on the last task");
            timerDetail.setText(lastTask);
        }else {
            lastTask = ("You spent "+ timeSpent+" on " + nameTask);
            timerDetail.setText(lastTask);
        }
        taskName.setText("");


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("lastTaskState", timerDetail.getText().toString());
        outState.putDouble(timeState, time);
    }
}