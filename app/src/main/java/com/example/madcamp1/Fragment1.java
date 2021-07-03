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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class Fragment1 extends Fragment {
    public ArrayList<Contact> dataList;
    public SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle   savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
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

        setHasOptionsMenu(true);
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.contact_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_contact:
                Intent intent_search = new Intent(getActivity().getApplicationContext(), ContactListViewAdapter.class);
                startActivity(intent_search);
                return true;
            case R.id.add_contact:
                Intent intent_add = new Intent(getActivity().getApplicationContext(), AddContact.class);
                startActivity(intent_add);
                return true;
            default:
                return super.onOptionsItemSelected(item);
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