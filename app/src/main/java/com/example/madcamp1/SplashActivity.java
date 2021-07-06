package com.example.madcamp1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import static java.lang.Thread.sleep;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        startActivity(new Intent(this, MainActivity.class));
        try{
            sleep(1000);
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        finish();
    }
}