package com.example.madcamp1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    private ArrayList<Contact> myDataList;

    ContactAdapter(ArrayList<Contact> dataList)
    {
        this.myDataList = dataList;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //전개자(Inflater)를 통해 얻은 참조 객체를 통해 뷰홀더 객체 생성
        View view = inflater.inflate(R.layout.contact_recyclerview, parent, false);
        ContactViewHolder viewHolder = new ContactViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder viewHolder, int position)
    {
        //ViewHolder가 관리하는 View에 position에 해당하는 데이터 바인딩
        viewHolder.imageView.setImageDrawable(myDataList.get(position).getImage());
//        viewHolder.imageView.setImageResource(myDataList.get(position).getImage());
        viewHolder.name.setText(myDataList.get(position).getName());
        viewHolder.number.setText(myDataList.get(position).getNumber());
        viewHolder.email.setText(myDataList.get(position).getEmail());
    }
    
    @Override
    public int getItemCount()
    {
        //Adapter가 관리하는 전체 데이터 개수 반환
        return myDataList.size();
    }
}