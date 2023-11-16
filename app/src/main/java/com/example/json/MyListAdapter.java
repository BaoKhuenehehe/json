package com.example.json;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyListAdapter extends ArrayAdapter<DocData>{

        public MyListAdapter(Context context, int resource, List<DocData> items) {
            super(context, resource, items);
        }
        public View getView(int position, View convertview, ViewGroup parent) {
            View view = convertview;
            if(view == null){
                LayoutInflater inflater=LayoutInflater.from(getContext());
                view =inflater.inflate(R.layout.listview_item, null);
            }
            DocData p = getItem(position);
            if (p!= null){
                TextView txttile = (TextView) view.findViewById(R.id.title);
                txttile.setText(p.title);
                ImageView imageView = (ImageView) view.findViewById(R.id.img);
                Picasso.get().load(p.hinhanh).into(imageView);
                TextView txtphone = (TextView) view.findViewById(R.id.txt_phone);
                txtphone.setText(p.phone);
                TextView txtaddress = (TextView) view.findViewById(R.id.txt_address);
                txtaddress.setText(p.address);

            }

            return view;

        };
    }

