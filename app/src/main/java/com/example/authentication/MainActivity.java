package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.security.PublicKey;

public class MainActivity extends AppCompatActivity {
    Button msaveBtn,notificationBtn;
    Button mshowBtn;
    public final String CHANNEL_ID = "001";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mshowBtn = findViewById(R.id.Show);
        msaveBtn= findViewById(R.id.SaveData);
        notificationBtn = findViewById(R.id.NotificationBtn);
        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification(view);
            }
        });
        msaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Dataentry.class));
            }
        });
        mshowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Show.class));
            }
        });
    }
    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
    }
    public void showNotification(View view){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String message = "This is a notification example";
            NotificationChannel notificationChannel = new NotificationChannel("001","001", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("This is description.");
            NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
            Notification.Builder builder = new Notification.Builder(this,CHANNEL_ID);
            Intent intent = new Intent(MainActivity.this,Show.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("message",message);
            PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);
            builder.setSmallIcon(R.drawable.ic_message).setContentText("For Showing All Patients Information").setContentTitle("Click Here..").setPriority(Notification.PRIORITY_DEFAULT);
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
            notificationManagerCompat.notify(001,builder.build());

        }
        else {

            Notification.Builder builder = new Notification.Builder(this);
            builder.setSmallIcon(R.drawable.ic_message).setContentText("For Showing All Patients Information").setContentTitle("Click Here..").setPriority(Notification.PRIORITY_DEFAULT);
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
            notificationManagerCompat.notify(001,builder.build());
        }
    }
}
