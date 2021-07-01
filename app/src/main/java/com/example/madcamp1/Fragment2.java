package com.example.madcamp1;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.File;

public class Fragment2 extends Fragment {
    private Integer[] flowers = {R.drawable.daisy1, R.drawable.daisy2, R.drawable.daisy3, R.drawable.daisy4, R.drawable.daisy5};

    public String basePath = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        super.onCreateView(inflater, container, savedInstanceState);

        GridView gridView = (GridView) view.findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(getContext()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getContext(), FullImageActivity.class);
                i.putExtra("id", position);
                startActivity(i);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}