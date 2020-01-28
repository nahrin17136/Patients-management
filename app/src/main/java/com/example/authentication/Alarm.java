package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Alarm extends AppCompatActivity {

    EditText mHour,mMinute;
    Button setBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        mHour = findViewById(R.id.hour);
        mMinute = findViewById(R.id.minute);
        setBtn = findViewById(R.id.setbtn);
        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = Integer.parseInt(mHour.getText().toString());
                int minute = Integer.parseInt(mMinute.getText().toString());
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_HOUR,hour);
                intent.putExtra(AlarmClock.EXTRA_MINUTES,minute);
                if(hour<=24&&minute<=60) {
                    startActivity(intent);
                }
            }
        });
    }
}
