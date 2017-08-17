package com.example.mohit.eventmanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static android.os.Build.VERSION_CODES.M;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        NextActivity();
    }

    public void NextActivity()
    {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent mainIntent = new Intent(BaseActivity.this,AuthenticationActivity.class);
                BaseActivity.this.startActivity(mainIntent);
                BaseActivity.this.finish();
            }
        }, 2000);

    }
}
