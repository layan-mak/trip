package com.example.user.layan;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter2 extends ArrayAdapter {
    //the list values in the List of type hero
    List<Trip> tripList;

    //activity context
    Context context;

    //the layout resource file for the list items
    int resource;

    //constructor initializing the values
    public CustomAdapter2(Context context, int resource, List<Trip> tripList) {
        super(context, resource, tripList);
        this.context= context;
        this.resource= resource;
        this.tripList=tripList;
    }

    //this will return the ListView Item as a View
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //we need to get the view of the xml for our list item
        //And for this we need a layoutinflater
        //  LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View view = layoutInflater.inflate(R.layout.custom_row2, parent, false);

        //getting the view elements of the list from the view
        ImageView imageView = view.findViewById(R.id.imageView2);
        TextView textViewName = view.findViewById(R.id.tripNameTV);


        //getting the hero of the specified position
        Trip trip = tripList.get(position);

        //adding values to the list item
        imageView.setImageResource(R.drawable.flag);
        textViewName.setText(trip.getName());

        //finally returning the view
        return view;
    }
}
