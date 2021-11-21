package com.example.a20180100_20180109_20180064.notification;

import android.app.PendingIntent;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.a20180100_20180109_20180064.MainActivity;
import com.example.a20180100_20180109_20180064.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseServices extends FirebaseMessagingService {
    private static final String TAG = "PushNotification";
    private static final String CHANNEL_ID = "101";
    public RemoteMessage localMessage;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        //MyFirebaseServices myFirebaseServices=new MyFirebaseServices();
        //String title= myFirebaseServices.getNotificationTitle();
        //String body= myFirebaseServices.getNotificationBody();
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference("notification").push();
//        databaseReference.child("Title").setValue(title);
//        databaseReference.child("Body").setValue(body);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("date").setValue(remoteMessage.getNotification().getBody());

        String title = remoteMessage.getNotification().getTitle();
        String description = remoteMessage.getNotification().getBody();
        localMessage=remoteMessage;
        showNotification(title,description);
    }

    public String getNotificationTitle(){
        return localMessage.getNotification().getTitle();
    }
    public String getNotificationBody(){
        return localMessage.getNotification().getBody();
    }

    private void showNotification(String title,String body){
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build());
    }
}
