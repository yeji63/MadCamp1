package com.example.madcamp1;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public Integer[] mThumbIds={
            R.drawable.daisy5, R.drawable.daisy4, R.drawable.daisy3, R.drawable.daisy2, R.drawable.daisy1,
            R.drawable.dandelion1, R.drawable.dandelion2, R.drawable.dandelion3, R.drawable.dandelion4, R.drawable.dandelion5,
            R.drawable.rose1, R.drawable.rose2, R.drawable.rose3, R.drawable.rose4, R.drawable.rose5,
            R.drawable.sunflower1, R.drawable.sunflower2, R.drawable.sunflower3, R.drawable.sunflower4, R.drawable.sunflower5
    };

    public ImageAdapter(Context c){
        mContext=c;
    }


    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(350, 350));
        return imageView;
    }
}
