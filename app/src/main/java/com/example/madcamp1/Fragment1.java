package com.example.madcamp1;

import android.Manifest;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Fragment1 extends Fragment {
    public ArrayList<contact> dataList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle   savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

//        jsonParsing(getJsonString());
        dataList = getContactList();
//        InitializeData();
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager); // LayoutManager 등록
        recyclerView.setAdapter(new MyAdapter(dataList));  // Adapter 등록
        return view;
    }

    private Drawable openPhoto(long contactId) {
        Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,
                contactId);
        InputStream input = ContactsContract.Contacts
                .openContactPhotoInputStream(getContext().getContentResolver(), contactUri);
        if (input != null) {
            Drawable d = new BitmapDrawable(getResources(), BitmapFactory.decodeStream(input));
            return d;
        }
        return null;
    }

    public ArrayList<contact> getContactList() {

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS},1);
        }
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_CONTACTS},1);
        }

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = new String[] {
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
        };

        String[] selectionArgs =null;
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";

        Cursor cursor =  getActivity().getContentResolver().query(uri, projection, null,
                selectionArgs, sortOrder);


        LinkedHashSet<contact> hasList = new LinkedHashSet<>();
        ArrayList<contact> contactsList;

        if (cursor.moveToFirst()) {
            do {
                contact myContact = new contact();
                myContact.setNumber(cursor.getString(0));
                myContact.setName(cursor.getString(1));
                Drawable photo = openPhoto(cursor.getLong(2));
                Resources rsc = getResources();
                myContact.setImage(photo == null ? rsc.getDrawable(rsc.getIdentifier("unknown", "drawable", getActivity().getPackageName())) : photo);

//                if (myContact.getNumber()number.startsWith("01")) {
                    hasList.add(myContact);
                    //contactsList.add(myContact);
                    Log.d("<<CONTACTS>>", "name=" + myContact.getName() + ", phone=" + myContact.getNumber());
//                }

            } while (cursor.moveToNext());
        }

        contactsList = new ArrayList<contact>(hasList);
        for (int i = 0; i < contactsList.size(); i++) {
            contactsList.get(i).setEmail(null);
        }

        if (cursor != null) {
            cursor.close();
        }

        return contactsList;
    }

    private String getJsonString()
    {
        String json = "";

        try {
            InputStream is = getContext().getAssets().open("contact.json");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return json;
    }

    private void jsonParsing(String json)
    {
        try{
            JSONObject jsonObject = new JSONObject(json);
            dataList = new ArrayList<>();
            JSONArray contactArray = jsonObject.getJSONArray("contact");


            for(int i=0; i<contactArray.length(); i++)
            {
                JSONObject contactObject = contactArray.getJSONObject(i);

                contact contact = new contact();

                Resources rsc = getResources();
                contact.setImage(rsc.getDrawable(rsc.getIdentifier(contactObject.getString("image"), "drawable", getActivity().getPackageName())));
                contact.setName(contactObject.getString("name"));
                contact.setNumber(contactObject.getString("number"));
                contact.setEmail(contactObject.getString("email"));
                dataList.add(contact);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

}