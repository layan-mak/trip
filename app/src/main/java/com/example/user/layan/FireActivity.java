package com.example.user.layan;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;


public class FireActivity extends AppCompatActivity {

    Button btSave;
    TextView tvName, tvProfession;
    EditText etName, etPass;
    ListView lvUsers;
    ArrayList<Map> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire);

        btSave=(Button) findViewById(R.id.btSave);
        tvName= (TextView) findViewById(R.id.tvName);
        tvProfession= (TextView) findViewById(R.id.tvProfession);
        etName= (EditText) findViewById(R.id.etName);
        etPass= (EditText) findViewById(R.id.etPass);
        lvUsers= (ListView)findViewById(R.id.lvUsers);

        users= new ArrayList<Map>();
        final ArrayAdapter adapter;
        adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,users);
        lvUsers.setAdapter(adapter);

        final FirebaseDatabase database= FirebaseDatabase.getInstance();
        final DatabaseReference myRef= database.getReference("Users");

        /*btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= etName.getText().toString();
                myRef.child("Name").setValue(email);
                myRef.child("Profession").setValue("Student");
            }
        });*/

//        myRef.child("Name").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Map<String,String> map= (Map<String, String>) dataSnapshot.getValue();
//                Log.v("E_VALUE","Data" + dataSnapshot.getValue());
//
//                String name= map.get("Name");
//                String profession= map.get("Profession");
//                tvName.setText(name);
//                tvProfession.setText(profession);
//            }

//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Map<String, String> map= (Map<String, String>) dataSnapshot.getValue();
                Log.v("E_VALUE", "DATA: "+ dataSnapshot.getValue());

                String country= map.get("country");
                String cities= map.get("cities");
                String image= map.get("image");
                String tripDayId= map.get("tripDayId");

//                users.add();
//                adapter.notifyDataSetChanged();
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
}
