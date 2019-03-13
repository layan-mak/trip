package com.example.user.layan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class PlanTripActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    Button saveTripButton, addTripImageButton;

    EditText tripDescription, tripNameET;

    ImageView tripImage;

    ListView daysLV;

    ArrayList<TripDay> days = new ArrayList<>();

    CustomAdapter dayAdapter;

    ArrayList<Map> users;

    private FirebaseAuth mAuth;

    FirebaseUser currentUser;

    String temporary;

    Bitmap actualImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_trip);

        mAuth = FirebaseAuth.getInstance();

        currentUser=mAuth.getCurrentUser();

        tripImage= (ImageView) findViewById(R.id.tripImage);
        temporary= getIntent().getStringExtra("imageTrip");
        actualImage= stringToBitmap(temporary);
        tripImage.setImageBitmap(actualImage);

        saveTripButton= (Button) findViewById(R.id.saveTripButton);
        addTripImageButton= (Button) findViewById(R.id.addTripImageButton);
        tripDescription=(EditText) findViewById(R.id.tripDescription);
        tripNameET= (EditText) findViewById(R.id.tripNameET);
        daysLV= (ListView) findViewById(R.id.daysLV);

        final FirebaseDatabase database= FirebaseDatabase.getInstance();
        final DatabaseReference myRef= database.getReference().child("Users");


        users= new ArrayList<Map>();
/*        final ArrayAdapter adapter;
        adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,users);
        daysLV.setAdapter(adapter);*/

//        days.add(new TripDay("country", "city", "day1", R.drawable.flag));
//        days.add(new TripDay("country", "city", "day2", R.drawable.flag));
//        days.add(new TripDay("country", "city", "day3", R.drawable.flag));
//        days.add(new TripDay("country", "city", "day4", R.drawable.flag));


        saveTripButton.setOnClickListener(this);
        addTripImageButton.setOnClickListener(this);

        dayAdapter= new CustomAdapter(this, R.layout.custom_row, days);
        daysLV.setAdapter(dayAdapter);
        daysLV.setOnItemClickListener(this);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Map<String, String> map= (Map<String, String>) dataSnapshot.getValue();
                Log.v("E_VALUE", "DATA: "+ dataSnapshot.getValue());

                String country= map.get("country");
                String cities= map.get("cities");
                String image= map.get("image");
                String tripDayId= map.get("tripDayId");
                String description= map.get("description");
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    public Bitmap stringToBitmap(String encodedString){
        try{
            byte[] encodeByte= Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte,0,encodeByte.length);
            return bitmap;
        }
        catch (Exception e) {
            e.getMessage();
            return null;
        }
    }


    @Override
    public void onClick(View v) {
        Intent goToCameraGalleryActivity= new Intent(this, CameraGalleryActivity.class);
        if(tripNameET.getText().toString().equals("") || tripDescription.getText().toString().equals("")) {
            Toast.makeText(this, "There Are Empty Fields", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if ( v==addTripImageButton) {
                startActivity(goToCameraGalleryActivity);
            }

            else if(v==saveTripButton){
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("Users");

                reference.child(currentUser.getUid()).child("Trips").push().setValue(new Trip(tripNameET.getText().toString(), tripDescription.getText().toString(), "", temporary));

                Intent i = new Intent(this, TripListActivity.class);
                startActivity(i);
            }

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
