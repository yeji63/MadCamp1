package com.example.madcamp1;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madcamp1.one.OneActivity;

import org.w3c.dom.Text;


public class Fragment3 extends Fragment {

    private static final String TAG = "exception 정수형 바꾸기";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_3, container, false);
        super.onCreateView(inflater, container, savedInstanceState);

        Button btn = (Button) view.findViewById(R.id.gamebtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    EditText et = (EditText)view.findViewById(R.id.et_num);
                    int num = Integer.parseInt(et.getText().toString());
                    Intent i = new Intent(getContext(), OneActivity.class);
                    i.putExtra("n_num", num);
                    startActivity(i);
                } catch (NumberFormatException e){
                    Toast.makeText(getContext(),"정수형으로 입력하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}