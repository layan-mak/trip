package com.example.user.layan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TripListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    ArrayList<Trip> trips;

    ListView tripsLV;

    CustomAdapter2 tripAdapter;

    TextView tripListTV;

    ArrayList<Map> users;

    ImageButton addNewTripIB;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user= mAuth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list);

        final FirebaseDatabase database= FirebaseDatabase.getInstance();
        final DatabaseReference myRef= database.getReference("Users/"+user.getUid()+"/Trips");

        tripsLV= findViewById(R.id.tripsLV);

        addNewTripIB= findViewById(R.id.addNewTripIB);
    /*    users= new ArrayList<Map>();

        final ArrayAdapter adapter;
        adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,users);
        tripsLV.setAdapter(adapter);
*/
        //      tripsLV= (ListView) findViewById(R.id.tripsLV);
        tripListTV= (TextView) findViewById(R.id.tripListTV);

        trips= new ArrayList<>();
//
//
//        trips.add(new Trip("my fav trip", 1, "was amazing", 3));
//        trips.add(new Trip("my worst trip", 1, "not so good", 5));
//
//        trips.get(0).setCountries();


        tripAdapter= new CustomAdapter2(this, R.layout.custom_row2, trips);
        tripsLV.setAdapter(tripAdapter);
        tripsLV.setOnItemClickListener(this);
        addNewTripIB.setOnClickListener(this);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Map<String, String> map= (Map<String, String>) dataSnapshot.getValue();
                Log.v("E_VALUE", "DATA: "+ dataSnapshot.getValue());

                String key = dataSnapshot.getKey();

                String description= map.get("description");

                String name= map.get("name");

                String tripId= map.get("tripId");

           //     String countries=map.get("countries");

//              String days= map.get("days");
                /*int pin= Integer.parseInt(map.get("pin"));*/
                int pin= 0;
                String TripDay=map.get("TripDay");

                trips.add(new Trip(name, description,key ));
                tripAdapter.notifyDataSetChanged();

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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()){
            case R.id.viewMap:
                i = new Intent(this, MapActivity.class);
                startActivity(i);
                break;
            case R.id.changePass:

                break;
            case R.id.logOut:
                i = new Intent(this, TripListActivity.class);
                startActivity(i);
                break;
        }
        return true;
    }




    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        //----------------------------------------------------THE ERROR IS HERE!!!!!!!!!!!!!!!!!----------------------------------------------------------------------------
        Intent i= new Intent(this, TripDesActivity.class);
        i.putExtra("trip", trips.get(position));
        startActivity(i);
        //----------------------------------------------------------------------------------------------------------------------------


        //String selectedItem= trips.get(position);
        //if (selectedItem=="Germany") {

    }

    @Override
    public void onClick(View v) {
        if (v== addNewTripIB) {
            Intent i = new Intent(this, PlanTripActivity.class);
            startActivity(i);

            //Intent i2= new Intent(this, PlanDayActivity.class);
            //i2.putExtra("key");
        }
    }
}

