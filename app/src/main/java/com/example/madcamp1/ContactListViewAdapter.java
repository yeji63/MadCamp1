package com.example.madcamp1;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ContactListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    private List<Contact> contactList = null;
    private ArrayList<Contact> dataList;

    public void contactListViewAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
        inflater = LayoutInflater.from(context);
        this.dataList = new ArrayList<Contact>();
        this.dataList.addAll(contactList);
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Contact getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ContactViewHolder holder;
        final Contact contact = contactList.get(position);


        if (view == null) {
            view = inflater.inflate(R.layout.activity_search_contact, null);
            holder = new ContactViewHolder(view);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.newName);
            holder.number = (TextView) view.findViewById(R.id.newNumber);
            view.setTag(holder);
        } else {
            holder = (ContactViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(contact.getName());
        Glide.with(context).load(contact.getImage()).into(holder.imageView);

        // Listen for ListView Item Click
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                Intent intent = new Intent(context, BrewingActivity.class);
//                context.startActivity(intent);
            }
        });

        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        contactList.clear();
        if (charText.length() == 0) {
            contactList.addAll(dataList);
        } else {
            for (Contact contact : dataList) {
                if (contact.getName().toLowerCase().contains(charText)) {
                    contactList.add(contact);
                } else if (contact.getNumber().toLowerCase().contains(charText)) {
                    contactList.add(contact);
                } else if (contact.getEmail().toLowerCase().contains(charText)) {
                    contactList.add(contact);
                }
            }
        }
        notifyDataSetChanged();
    }

}