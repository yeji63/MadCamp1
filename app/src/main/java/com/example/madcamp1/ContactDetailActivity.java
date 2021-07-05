package com.example.madcamp1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactdetail);

        ImageView photo = (ImageView) findViewById(R.id.oldPhoto);
        TextView name = (TextView) findViewById(R.id.oldName);
        TextView number = (TextView) findViewById(R.id.oldNumber);
        TextView email = (TextView) findViewById(R.id.oldEmail);


        Intent i = getIntent();
        int pos = i.getExtras().getInt("contactpos");

        photo.setImageDrawable(ContactAdapter.myDataList.get(pos).getImage());
        name.setText(ContactAdapter.myDataList.get(pos).getName());
        number.setText(ContactAdapter.myDataList.get(pos).getNumber());
        email.setText(ContactAdapter.myDataList.get(pos).getEmail());
    }
}
