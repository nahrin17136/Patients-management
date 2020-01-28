package com.example.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Dataentry extends AppCompatActivity {
    EditText mName,mAge,mMedicine,mSlno;
    Button mSaveBtn;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    private Patients patients;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataentry);
        mSlno = findViewById(R.id.Slno);
        mName = findViewById(R.id.Name);
        mAge = findViewById(R.id.Age);
        mMedicine = findViewById(R.id.Medicines);
        mSaveBtn = findViewById(R.id.Save);
        database = FirebaseDatabase.getInstance();
        patients = new Patients();
        databaseReference = database.getReference("Patients");
        mSaveBtn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String slno = mSlno.getText().toString().trim();
                String pname = mName.getText().toString().trim();
                String age = mAge.getText().toString().trim();
                String medicine = mMedicine.getText().toString();
                patients.setSerial_no(slno);
                patients.setName(pname);
                patients.setAge(age);
                patients.setMedicines(medicine);
                if(TextUtils.isEmpty(slno)){
                    mSlno.setError("Serial No is Required.");
                    return;
                }
                if(TextUtils.isEmpty(pname)){
                    mName.setError("Patient name is Required.");
                    return;
                }
                Toast.makeText(Dataentry.this,"User Created.",Toast.LENGTH_SHORT).show();
                HashMap<String,String> dataMap = new HashMap<String, String>();
                dataMap.put("Serial_no",slno);
                dataMap.put("Name",pname);
                dataMap.put("Age",age);
                dataMap.put("Medicines",medicine);
                databaseReference.child(patients.getSerial_no()).setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Dataentry.this, "Stored...", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Dataentry.this, "Error...",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}
