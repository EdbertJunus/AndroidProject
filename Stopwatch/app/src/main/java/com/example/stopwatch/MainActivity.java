package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.SystemClock;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {
    Chronometer stopwatch;
    Button startButton, pauseButton, resetButton;
    boolean running = false;
    long offset = 0;
    String OFFSET_KEY = "offset";
    String RUNNING_KEY = "running";
    String BASE_KEY = "base";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        if(savedInstanceState != null){
            offset = savedInstanceState.getLong(OFFSET_KEY);
            running = savedInstanceState.getBoolean(RUNNING_KEY);
            if(running){
                stopwatch.setBase(savedInstanceState.getLong(BASE_KEY));
                stopwatch.start();
            } else setBaseTime();
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putLong(OFFSET_KEY, offset);
        savedInstanceState.putBoolean(RUNNING_KEY, running);
        savedInstanceState.putLong(BASE_KEY, stopwatch.getBase());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onPause(){
        super.onPause();
        System.out.println("OnPause before if");
        if(running){
            System.out.println("OnPause");
            saveOffset();
            stopwatch.stop();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        System.out.println("OnResume before if");
        if(running){
            System.out.println("OnResume");
            setBaseTime();
            stopwatch.start();
            offset = 0;
        }
    }

    private void setBaseTime(){
        stopwatch.setBase(SystemClock.elapsedRealtime() - offset);
    }

    private void saveOffset(){
        offset =  SystemClock.elapsedRealtime() - stopwatch.getBase();
    }

    private void init(){

        stopwatch = findViewById(R.id.stopwatch);
        startButton = findViewById(R.id.start_button);
        pauseButton = findViewById(R.id.stop_button);
        resetButton = findViewById(R.id.reset_button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!running){
                    setBaseTime();
                    stopwatch.start();
                    running = true;
                }
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(running){
                    saveOffset();
                    stopwatch.stop();
                    running = false;
                }
                System.out.println("Pause");
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offset = 0;
                setBaseTime();
            }
        });
    }
}