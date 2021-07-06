package com.example.madcamp1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailActivity extends AppCompatActivity {
    TextView number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactdetail);

        ImageView photo = (ImageView) findViewById(R.id.oldPhoto);
        TextView name = (TextView) findViewById(R.id.oldName);
        number = (TextView) findViewById(R.id.oldNumber);
        TextView email = (TextView) findViewById(R.id.oldEmail);

        Intent i = getIntent();
        int pos = i.getExtras().getInt("contactpos");
        int type = i.getExtras().getInt("type");

        Contact contact = type == 1? ContactAdapter.myDataList.get(pos) : ContactFilterAdapter.contactList.get(pos);
        photo.setImageDrawable(contact.getImage());
        name.setText(contact.getName());
        number.setText(contact.getNumber());
        email.setText(contact.getEmail());
    }
    public void dialPhone(View view){
        dialPhoneNumber(number.getText().toString());
    }
    public void dialPhoneNumber(String phoneNumber){
        Intent i = new Intent(Intent.ACTION_DIAL);
        i.setData(Uri.parse("tel:"+phoneNumber));

        if(i.resolveActivity(getPackageManager())!=null){
            startActivity(i);
        }
    }
}
