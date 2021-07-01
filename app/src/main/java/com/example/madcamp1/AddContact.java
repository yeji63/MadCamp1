package com.example.madcamp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AddContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
    }

    public void onBackButtonClicked(View v) {
        Toast.makeText(getApplicationContext(), "Return", Toast.LENGTH_LONG).show();
        finish();
    }
}