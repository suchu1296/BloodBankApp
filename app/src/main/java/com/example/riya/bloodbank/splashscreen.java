package com.example.riya.bloodbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(6000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(splashscreen.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}
