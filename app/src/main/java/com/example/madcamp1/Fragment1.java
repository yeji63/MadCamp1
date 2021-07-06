package com.example.madcamp1;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

public class Fragment1 extends Fragment implements View.OnClickListener{
    static ArrayList<Contact> dataList;
    public SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton fab_main, fab_sub1, fab_sub2;
    private Animation fab_main_open, fab_main_close, fab_sub1_open, fab_sub1_close, fab_sub2_open, fab_sub2_close;
    private boolean isFabOpen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_1, container, false);
        view.findViewById(R.id.swiperefreshlayout).bringToFront();
        view.findViewById(R.id.fab_sub2).bringToFront();
        view.findViewById(R.id.fab_sub1).bringToFront();
        view.findViewById(R.id.fab_main).bringToFront();
        Fragment fragment1 = this;
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ft.detach(fragment1).attach(fragment1).commit();
                Toast.makeText(getContext(), "Successfully loaded.", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        isFabOpen = false;
        fab_sub1_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_sub1_open);
        fab_sub1_close = AnimationUtils.loadAnimation(getContext(), R.anim.fab_sub1_close);
        fab_sub2_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_sub2_open);
        fab_sub2_close = AnimationUtils.loadAnimation(getContext(), R.anim.fab_sub2_close);
        fab_main_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_main_open);
        fab_main_close = AnimationUtils.loadAnimation(getContext(), R.anim.fab_main_close);

        fab_main = (FloatingActionButton) view.findViewById(R.id.fab_main);
        fab_sub1 = (FloatingActionButton) view.findViewById(R.id.fab_sub1);
        fab_sub2 = (FloatingActionButton) view.findViewById(R.id.fab_sub2);

        fab_main.setOnClickListener(this);
        fab_sub1.setOnClickListener(this);
        fab_sub2.setOnClickListener(this);


//        setHasOptionsMenu(true);
//        jsonParsing(getJsonString());
        dataList = getContactList();
//        InitializeData();
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager); // LayoutManager 등록
        recyclerView.setAdapter(new ContactAdapter(dataList));  // Adapter 등록
        return view;
    }

    @Override

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_main:
                toggleFab();
                break;
            case R.id.fab_sub1:
                toggleFab();
                Intent intent_search = new Intent(getActivity().getApplicationContext(), SearchContact.class);
                startActivity(intent_search);
                break;

            case R.id.fab_sub2:
                toggleFab();
                Intent intent_add = new Intent(getActivity().getApplicationContext(), AddContact.class);
                startActivity(intent_add);
                break;
        }
    }

    private void toggleFab() {
        if (isFabOpen) {
//            fab_main.setImageResource(R.drawable.ic_more);
            fab_main.startAnimation(fab_main_close);
            fab_sub1.startAnimation(fab_sub1_close);
            fab_sub2.startAnimation(fab_sub2_close);
            fab_sub1.setClickable(false);
            fab_sub2.setClickable(false);
            isFabOpen = false;
        } else {
//            fab_main.setImageResource(R.drawable.ic_more);
            fab_main.startAnimation(fab_main_open);
            fab_sub1.startAnimation(fab_sub1_open);
            fab_sub2.startAnimation(fab_sub2_open);
            fab_sub1.setClickable(true);
            fab_sub2.setClickable(true);
            isFabOpen = true;
        }
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

    public ArrayList<Contact> getContactList() {

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
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID
        };

        String[] selectionArgs =null;
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";

        Cursor cursor =  getActivity().getContentResolver().query(uri, projection, null,
                selectionArgs, sortOrder);
        Cursor cursor2 = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, null, null, null);

        HashMap nameEmailMap = new HashMap<>();
        while (cursor2.moveToNext()) {

            String name = cursor2.getString(cursor2.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String email = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));

            // Enter Into Hash Map
            nameEmailMap.put(name, email);
            Log.d("<<CONTACTS>>", "name=" + name + ", email=" + email);
        }


        LinkedHashSet<Contact> hasList = new LinkedHashSet<>();
        ArrayList<Contact> contactsList;

        if (cursor.moveToFirst()) {
            do {
                Contact myContact = new Contact();
                myContact.setNumber(cursor.getString(0));
                String name = cursor.getString(1);
                myContact.setName(name);
                myContact.setEmail((String) nameEmailMap.get(name));
                Drawable photo = openPhoto(cursor.getLong(2));
                Resources rsc = getResources();
                myContact.setImage(photo == null ? rsc.getDrawable(rsc.getIdentifier("unknown", "drawable", getActivity().getPackageName())) : photo);
                hasList.add(myContact);
                Log.d("<<CONTACTS>>", "name=" + myContact.getName() + ", phone=" + myContact.getNumber() + ", email=" + myContact.getEmail());
            } while (cursor.moveToNext());
        }

        contactsList = new ArrayList<Contact>(hasList);
        if (cursor != null) {
            cursor.close();
        }

        return contactsList;
    }

    public ArrayList<Contact> getDataList() {
        return dataList;
    }

    //    private String getJsonString()
//    {
//        String json = "";
//
//        try {
//            InputStream is = getContext().getAssets().open("contact.json");
//            int fileSize = is.available();
//
//            byte[] buffer = new byte[fileSize];
//            is.read(buffer);
//            is.close();
//
//            json = new String(buffer, "UTF-8");
//        }
//        catch (IOException ex)
//        {
//            ex.printStackTrace();
//        }
//        return json;
//    }
//
//    private void jsonParsing(String json)
//    {
//        try{
//            JSONObject jsonObject = new JSONObject(json);
//            dataList = new ArrayList<>();
//            JSONArray contactArray = jsonObject.getJSONArray("contact");
//
//
//            for(int i=0; i<contactArray.length(); i++)
//            {
//                JSONObject contactObject = contactArray.getJSONObject(i);
//
//                contact contact = new contact();
//
//                Resources rsc = getResources();
//                contact.setImage(rsc.getDrawable(rsc.getIdentifier(contactObject.getString("image"), "drawable", getActivity().getPackageName())));
//                contact.setName(contactObject.getString("name"));
//                contact.setNumber(contactObject.getString("number"));
//                contact.setEmail(contactObject.getString("email"));
//                dataList.add(contact);
//            }
//        }catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

}