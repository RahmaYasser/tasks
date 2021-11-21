package com.example.a20180100_20180109_20180064;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.a20180100_20180109_20180064.notification.MyFirebaseServices;
import com.example.a20180100_20180109_20180064.notification.Notification;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public static final int PICK_FILE_REQUEST_CODE=300;
    private static final String TAG = "PushNotification";
    private static final String CHANNEL_ID = "101";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View button = findViewById(R.id.uploadFile);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFile();
            }
        });
        //getToken();
    }

    private void selectFile() {
        Intent intent= new Intent(Intent.ACTION_GET_CONTENT);

        intent.setType("/*");
        startActivityForResult(intent,PICK_FILE_REQUEST_CODE);
        //mStartForResult.launch(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_FILE_REQUEST_CODE && resultCode==RESULT_OK){
            Uri filePath = data.getData();
            uploadFile(filePath);
        }
    }
    private void uploadFile(Uri filePath){
       StorageReference root = FirebaseStorage.getInstance().getReference();
       StorageReference fileRef = root.child("image/popcorn.png");
       fileRef.putFile(filePath);
    }

    /*ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        // Handle the Intent
                        Uri filePath = data.getData();
                        uploadFile(filePath);
                    }
                }
            });*/

    /*private void getToken(){
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(!task.isSuccessful()){
                    Log.d(TAG,"failed to get token");
                }
                String token=task.getResult();
                Log.d(TAG,"On complete \n" + token);
            }
        });
    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "firebaseNotificationChannel";
            String description = "description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void sendMessage(View view) {
        MyFirebaseServices myFirebaseServices=new MyFirebaseServices();
        String title= myFirebaseServices.getNotificationTitle();
        String body= myFirebaseServices.getNotificationBody();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("notification").push();
        databaseReference.child("Title").setValue(title);
        databaseReference.child("Body").setValue(body);
    }*/
}