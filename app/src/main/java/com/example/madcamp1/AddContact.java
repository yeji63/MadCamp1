package com.example.madcamp1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentTransaction;
import android.content.ContentProviderOperation;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class AddContact extends AppCompatActivity {


    private final int GET_GALLERY_IMAGE = 200;
    private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        imageview = (ImageView)findViewById(R.id.newPhoto);
        imageview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            imageview.setImageURI(selectedImageUri);

        }
    }

    public void writeDisplayPhoto(long rawContactId, byte[] photo) {
        Uri rawContactPhotoUri = Uri.withAppendedPath(
                ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, rawContactId),
                ContactsContract.RawContacts.DisplayPhoto.CONTENT_DIRECTORY);
        try {
            AssetFileDescriptor fd =
                    getContentResolver().openAssetFileDescriptor(rawContactPhotoUri, "rw");
            OutputStream os = fd.createOutputStream();
            os.write(photo);
            os.close();
            fd.close();
        } catch (IOException e) {
            // Handle error cases.
        }
    }

    public void onSaveButtonClicked(View v) {
//        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
//        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        ImageView newPhoto = findViewById(R.id.newPhoto);
        EditText newName = findViewById(R.id.newName);
        EditText newNumber = findViewById(R.id.newNumber);
        EditText newEmail = findViewById(R.id.newEmail);
        Bitmap Thumbnail = Bitmap.createBitmap(newPhoto.getWidth(), newPhoto.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(Thumbnail);
        newPhoto.draw(canvas);
        String DisplayName = newName.getText().toString();
        String MobileNumber = newNumber.getText().toString();
        String emailID = newEmail.getText().toString();

        if (DisplayName.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your name.", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (MobileNumber.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your phone number.", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList< ContentProviderOperation > ops = new ArrayList < ContentProviderOperation > ();

        ops.add(ContentProviderOperation.newInsert(
                ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());

//        //------------------------------------------------------ Photo
//        ContentValues values = new ContentValues();
//        values.put(ContactsContract.RawContacts.ACCOUNT_TYPE, (byte[]) null);
//        values.put(ContactsContract.RawContacts.ACCOUNT_NAME, accountName);
//        Uri rawContactUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);
//        long rawContactId = ContentUris.parseId(rawContactUri);
//        writeDisplayPhoto(rawContactId, photo);

        //------------------------------------------------------ Names
        if (DisplayName != null) {
            ops.add(ContentProviderOperation.newInsert(
                    ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                    .withValue(
                            ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                            DisplayName).build());
        }

        //------------------------------------------------------ Mobile Number
        if (MobileNumber != null) {
            ops.add(ContentProviderOperation.
                    newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, MobileNumber)
                    .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                            ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                    .build());
        }

        //------------------------------------------------------ Email
        if (emailID != null) {
            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Email.DATA, emailID)
                    .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                    .build());
        }

        // Asking the Contact provider to create a new contact
        try {
            getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
            Toast.makeText(getApplicationContext(), "Successfully saved.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void onCancelButtonClicked(View v) {
        finish();
    }

}