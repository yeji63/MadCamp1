package com.example.madcamp1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class GalleryFullImageActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);

        // get intent data
        Intent i = getIntent();

        // Selected image id
        int position = i.getExtras().getInt("id");
        GalleryImageAdapter imageAdapter = new GalleryImageAdapter(this);

        ImageView imageView = (ImageView) findViewById(R.id.fullimg);
        imageView.setImageResource(imageAdapter.mThumbIds[position]);
    }

}
