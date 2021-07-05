package com.example.madcamp1;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.madcamp1.one.OneActivity;



public class Fragment3 extends Fragment {

    private static final String TAG = "exception 정수형 바꾸기";
    ImageView gameimg;
    int selectimg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_3, container, false);
        super.onCreateView(inflater, container, savedInstanceState);

        //Button select = (Button) view.findViewById(R.id.select);
        Button btn = (Button) view.findViewById(R.id.gamebtn);
        gameimg = (ImageView) view.findViewById(R.id.gameimg);
        gameimg.setImageResource(R.drawable.ggg);

        gameimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), GalleryDetailActivity.class);
                i.putExtra("frag", 2);
                startActivityForResult(i, 2);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    EditText et = (EditText)view.findViewById(R.id.et_num);
                    int num = Integer.parseInt(et.getText().toString());
                    Intent i = new Intent(getContext(), OneActivity.class);
                    i.putExtra("n_num", num);

                    if(selectimg==0){
                        i.putExtra("imgres", R.drawable.ggg);
                        Toast.makeText(getContext(),"기본 이미지로 설정됩니다.", Toast.LENGTH_LONG).show();
                    }else{
                        i.putExtra("imgres", selectimg);
                    }
                    startActivity(i);
                } catch (NumberFormatException e){
                    Toast.makeText(getContext(),"정수형으로 입력하세요.", Toast.LENGTH_SHORT).show();
                }
            }

        });

        // Inflate the layout for this fragment
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2){
            if(data != null){
                selectimg= data.getIntExtra("selectimg", 0);
                //String name = data.getStringExtra("name");
                gameimg.setImageResource(selectimg);
            }
        }
    }
}