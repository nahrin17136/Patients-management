package com.example.authentication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Showall extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseDatabase database;
    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    Patients patients;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showall);
        patients = new Patients();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Patients");
        listView = findViewById(R.id.listview);
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(this,R.layout.patients_info,R.id.userInfo,arrayList);
        listView.setAdapter(arrayAdapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds :dataSnapshot.getChildren()){
                    patients = ds.getValue(Patients.class);
                    arrayList.add(patients.getSerial_no().toString() + ". " +patients.getName().toString() + " Age: "+patients.getAge().toString() + " Medicines: " +patients.getMedicines().toString() );
                }
                listView.setAdapter(arrayAdapter);
                /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent UpdateDelete = new Intent(Showall.this,UpdateDelete.class);
                        Patients p = (Patients) adapterView.getItemAtPosition(i);
                        UpdateDelete.putExtra("Serial no",p.getSerial_no());
                        UpdateDelete.putExtra("Patients name",p.getName());
                        UpdateDelete.putExtra("Age",p.getAge());
                        UpdateDelete.putExtra("Medicines",p.getMedicines());
                        startActivity(UpdateDelete);
                    }
                });*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
