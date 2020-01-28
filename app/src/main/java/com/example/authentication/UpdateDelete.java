package com.example.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateDelete extends AppCompatActivity {

    EditText mName,mAge,mMedicine,mSlno;
    Button mupdate,mdelete,malert;
    DatabaseReference databaseReference;
    TextView mkey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        mSlno = findViewById(R.id.Slno);
        mName = findViewById(R.id.Name);
        mAge = findViewById(R.id.Age);
        mMedicine = findViewById(R.id.Medicines);
        mkey = findViewById(R.id.key);
        mupdate = findViewById(R.id.Updatebtn);
        mdelete = findViewById(R.id.Deletebtn);
        malert = findViewById(R.id.AlarmBtn);
        String key = getIntent().getExtras().get("key").toString();
        mkey.setText("Serial No: "+key);
        databaseReference = FirebaseDatabase.getInstance().getReference("Patients").child(key);
        mName.setText(getIntent().getStringExtra("Patients name"));
        mAge.setText(getIntent().getStringExtra("Age"));
        mMedicine.setText(getIntent().getStringExtra("Medicines"));

        mupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("Name").setValue(mName.getText().toString());
                databaseReference.child("Age").setValue(mAge.getText().toString());
                databaseReference.child("Medicines").setValue(mMedicine.getText().toString());
                Toast.makeText(UpdateDelete.this,"Data Updated successfully..",Toast.LENGTH_LONG).show();
                UpdateDelete.this.finish();
                startActivity(new Intent(getApplicationContext(),Show.class));
            }
        });
        mdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(UpdateDelete.this,"Recored Deleted successfully..",Toast.LENGTH_LONG).show();
                            UpdateDelete.this.finish();
                        }
                        else {
                            Toast.makeText(UpdateDelete.this,"Recored Not Deleted",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                startActivity(new Intent(getApplicationContext(),Show.class));
            }
        });

        malert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Alarm.class));
            }
        });
    }
}
