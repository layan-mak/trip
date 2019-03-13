package com.example.user.layan;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Custom_adapter2 extends ArrayAdapter<Trip> {
    private int resourceLayout;
    private Context aContext;

    public Custom_adapter2(@NonNull Context context, int resource, @NonNull List<Trip> objects) {
        super(context, resource, objects);
        this.resourceLayout= resource;
        this.aContext= context;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v= convertView;
        if (v== null)
            v= LayoutInflater.from(aContext).inflate(resourceLayout, parent,  false);
        Trip p= getItem(position);

        //  if( p!=null ) {
        TextView tvName= (TextView) v.findViewById(R.id.tripNameTV);
        tvName.setText(p.getName());

        TextView tvCountries= (TextView) v.findViewById(R.id.countriesTV);
        tvCountries.setText(String.join(",", p.getCountries()));
        ImageView imageView2= (ImageView)v.findViewById(R.id.imageView2);
//        Bitmap b = stringToBitmap(p.getImage());
//        e3male getImge() b Trip, copy paste converting to bitmap function, display the image.
        //    }
        return v;
    }
}


