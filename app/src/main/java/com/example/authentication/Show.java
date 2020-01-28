package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Show extends AppCompatActivity {

    ListView listView;
    FirebaseListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        listView = findViewById(R.id.listview);
        Query query = FirebaseDatabase.getInstance().getReference().child("Patients");
        FirebaseListOptions<Patients> options = new FirebaseListOptions.Builder<Patients>().setLayout(R.layout.patient).setLifecycleOwner(Show.this).setQuery(query,Patients.class).build();
        listAdapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {
                TextView mSerialno = v.findViewById(R.id.Serial_no);
                TextView mName =v.findViewById(R.id.Name) ;
                TextView mAge = v.findViewById(R.id.Age);
                TextView mMedicines = v.findViewById(R.id.Medicines);
                Patients patients = (Patients) model;
                mSerialno.setText("Serial no: "+patients.getSerial_no().toString());
                mName.setText("Patients name: "+patients.getName().toString());
                mAge.setText("Age: "+patients.getAge().toString());
                mMedicines.setText("Medicines: "+patients.getMedicines().toString());

            }
        };
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent UpdateDelete = new Intent(Show.this,UpdateDelete.class);
                Patients p = (Patients) adapterView.getItemAtPosition(i);
                UpdateDelete.putExtra("Patients name",p.getName());
                UpdateDelete.putExtra("Age",p.getAge());
                UpdateDelete.putExtra("Medicines",p.getMedicines());
                UpdateDelete.putExtra("key",p.getSerial_no());
                startActivity(UpdateDelete);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        listAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        listAdapter.stopListening();
    }
}
