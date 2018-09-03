package com.example.user.heartdisease.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.heartdisease.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                    sendtoActivity();

                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }

    private void sendtoActivity() {
        //
        Intent intent = new Intent(SplashScreenActivity.this, QuestionActivity.class);
        startActivity(intent);
        finish();
    }
}
