package com.example.user.layan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ThirdPageActivity extends AppCompatActivity implements View.OnClickListener {

    Button plannedTripsButton, planNewTripButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_page);

        plannedTripsButton = (Button) findViewById(R.id.plannedTripsButton);
        planNewTripButton = (Button) findViewById(R.id.planNewTripButton);

        plannedTripsButton.setOnClickListener(this);
        planNewTripButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent i;
        if (v == plannedTripsButton) {
            i = new Intent(this, TripListActivity.class);
            startActivity(i);
        }
        else if (v == planNewTripButton) {
            i = new Intent(this, PlanTripActivity.class);
            startActivity(i);
        }
    }

}
