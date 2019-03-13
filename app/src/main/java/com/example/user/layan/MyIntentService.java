package com.example.user.layan;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.support.v4.app.NotificationManagerCompat;

public class MyIntentService extends IntentService {
    private static final int  NOTIFICATION_ID=3;

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
            Notification.Builder builder = new Notification.Builder(this);
            builder.setContentTitle("Reminder");
            builder.setContentText("Feel like planning a new trip today?");
            builder.setSmallIcon(R.drawable.notification);
            Intent notifyIntent = new Intent(this, HomeActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            //to be able to launch your activity from the notification
            builder.setContentIntent(pendingIntent);
            Notification notificationCompat = builder.build();
            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
            managerCompat.notify(NOTIFICATION_ID, notificationCompat);

    }

}
