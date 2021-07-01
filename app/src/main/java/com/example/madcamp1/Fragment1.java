package com.example.madcamp1;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Fragment1 extends Fragment {
    private ArrayList<contact> dataList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle   savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        this.InitializeData();
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager); // LayoutManager 등록
        recyclerView.setAdapter(new MyAdapter(dataList));  // Adapter 등록
        return view;
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
//
//            JSONArray contactArray = jsonObject.getJSONArray("contact");
//
//            for(int i=0; i<contactArray.length(); i++)
//            {
//                JSONObject contactObject = contactArray.getJSONObject(i);
//
//                contact contact = new contact();
//
//                contact.setImage(getActivity().getResources().getIdentifier(contactObject.getString("image"), "drawable", getActivity().getPackageName()));
//                /*contact.setImage(contactObject.getInt("image"));*/
//                contact.setName(contactObject.getString("name"));
//                contact.setNumber(contactObject.getString("number"));
//                contact.setEmail(contactObject.getString("email"));
//                dataList.add(contact);
//            }
//        }catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    public void InitializeData()
    {
        dataList = new ArrayList<>();

        dataList.add(new contact(R.drawable.mother,"엄마", "010-1111-1111", "mother@gmail.com"));
        dataList.add(new contact(R.drawable.father,"아빠", "010-2222-2222", "father@gmail.com"));
        dataList.add(new contact(R.drawable.brother,"형", "010-3333-3333", "brother@gmail.com"));
    }
}