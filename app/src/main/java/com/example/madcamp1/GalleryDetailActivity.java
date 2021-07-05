package com.example.madcamp1;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class GalleryDetailActivity extends AppCompatActivity {
    int frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallerydetail);

        GridView gridView_d = (GridView) findViewById(R.id.gridview_detail);


        //album 값으로 배열 설정하기
        Integer[] all={
                R.drawable.daisy1, R.drawable.daisy2, R.drawable.daisy3, R.drawable.daisy4, R.drawable.daisy5,
                R.drawable.dandelion1, R.drawable.dandelion2, R.drawable.dandelion3, R.drawable.dandelion4, R.drawable.dandelion5,
                R.drawable.rose1, R.drawable.rose2, R.drawable.rose3, R.drawable.rose4, R.drawable.rose5,
                R.drawable.sunflower1, R.drawable.sunflower2, R.drawable.sunflower3, R.drawable.sunflower4, R.drawable.sunflower5,
                R.drawable.tulip1, R.drawable.tulip2, R.drawable.tulip3, R.drawable.tulip4, R.drawable.tulip5
        };
        Integer[] daisy={
                R.drawable.daisy1, R.drawable.daisy2, R.drawable.daisy3, R.drawable.daisy4, R.drawable.daisy5
        };
        Integer[] dandelion={
                R.drawable.dandelion1, R.drawable.dandelion2, R.drawable.dandelion3, R.drawable.dandelion4, R.drawable.dandelion5
        };
        Integer[] rose={
                R.drawable.rose1, R.drawable.rose2, R.drawable.rose3, R.drawable.rose4, R.drawable.rose5, R.drawable.rose6
        };
        Integer[] sunflower={
                R.drawable.sunflower1, R.drawable.sunflower2, R.drawable.sunflower3, R.drawable.sunflower4, R.drawable.sunflower5
        };
        Integer[] tulip={
                R.drawable.tulip1, R.drawable.tulip2, R.drawable.tulip3, R.drawable.tulip4, R.drawable.tulip5, R.drawable.tulip6, R.drawable.tulip7
        };


        Intent i = getIntent();
        int album = i.getExtras().getInt("album");
        frag = i.getExtras().getInt("frag");
        switch (album){
            case 0: //all
                gridView_d.setAdapter(new GalleryDetailAdapter(this, all));
                break;
            case 1: //daisy
                gridView_d.setAdapter(new GalleryDetailAdapter(this, daisy));
                break;
            case 2: //dandelion
                gridView_d.setAdapter(new GalleryDetailAdapter(this, dandelion));
                break;
            case 3: //rose
                gridView_d.setAdapter(new GalleryDetailAdapter(this, rose));
                break;
            case 4: //sunflower
                gridView_d.setAdapter(new GalleryDetailAdapter(this, sunflower));
                break;
            case 5: //tulip
                gridView_d.setAdapter(new GalleryDetailAdapter(this, tulip));
                break;

        }


        gridView_d.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(frag==1){
                    Intent i = new Intent(getApplicationContext(), FullImageActivity.class);
                    i.putExtra("id", position);
                    //Log.d(TAG, "갤러리 포지션 값 "+position);
                    startActivity(i);
                }
                else{
                    Intent i = new Intent();
                    i.putExtra("selectimg", all[position]);
                    setResult(2, i);
                    finish();
                }
            }
        });

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }
}