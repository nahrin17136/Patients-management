package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Showdata extends AppCompatActivity {
    EditText mSlno;
    Button mSearch,mShowall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdata);
        mSlno = findViewById(R.id.Slno);
        mSearch = findViewById(R.id.Search);
        mShowall = findViewById(R.id.Showall);
        mShowall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Showall.class));
            }
        });
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String slno = mSlno.getText().toString().trim();
                if(TextUtils.isEmpty(slno)){
                    mSlno.setError("Serial No is Required.");
                    return;
                }
                Toast.makeText(Showdata.this,"Searching...",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
