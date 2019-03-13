package com.example.user.layan;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    Button goToSignInButton, goToSignUpButton;
    private static final int  NOTIFICATION_REMINDER_NIGHT=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent notifyIntent = new Intent(this,MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_REMINDER_NIGHT, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)
                this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis(),
                1000 * 60 * 60 * 24, pendingIntent);

        goToSignInButton = (Button) findViewById(R.id.goToSignInButton);
        goToSignUpButton = (Button) findViewById(R.id.goToSignUpButton);

        goToSignInButton.setOnClickListener(this);
        goToSignUpButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent i;
        if (v == goToSignInButton) {
            i = new Intent(this, SignInActivity.class);
            startActivity(i);
        }
        else if (v==goToSignUpButton){
            i = new Intent(this, SignUpActivity.class);
            startActivity(i);
        }
    }





}