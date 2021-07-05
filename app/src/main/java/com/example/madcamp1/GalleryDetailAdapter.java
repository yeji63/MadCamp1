package com.example.madcamp1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GalleryDetailAdapter extends BaseAdapter {

    private Context imgContext;
    public static Integer[] imglist;

    public GalleryDetailAdapter(Context c, Integer[] list){
        imgContext=c;
        imglist=list;
    }

    @Override
    public int getCount() {
        return imglist.length;
    }

    @Override
    public Object getItem(int position) {
        return imglist[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(imgContext);
        imageView.setImageResource(imglist[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(350, 350));
        return imageView;
    }
}
