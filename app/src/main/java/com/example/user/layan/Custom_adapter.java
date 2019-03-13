package com.example.user.layan;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Custom_adapter extends ArrayAdapter<TripDay> {
    private int resource;
    private Context context;

    public Custom_adapter(@NonNull Context context, int resource, @NonNull List<TripDay> objects) {
        super(context, resource, objects);
        this.resource= resource;
        this.context= context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v= convertView;
        if (v== null)
            v= LayoutInflater.from(context).inflate(resource, parent,  false);
        TripDay p= getItem(position);

      //  if( p!=null ) {
            TextView tvName= (TextView) v.findViewById(R.id.tripDayNumTV);
            tvName.setText(p.getCountry());

            ImageView imageView= (ImageView)v.findViewById(R.id.imageView);
       //     imageView.setImageResource(p.getImage());
    //    }
        return v;
    }
}
