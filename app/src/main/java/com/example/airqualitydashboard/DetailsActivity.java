package com.example.airqualitydashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DetailsActivity extends AppCompatActivity {

    TextView rspmText;
    TextView spmText;
    TextView lastUpdated;
    TextView predictedRspm;
    TextView predictedSpm;
    String selected_area;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        rspmText = findViewById(R.id.RSPMText);
        spmText = findViewById(R.id.SPMText);
        lastUpdated = findViewById(R.id.lastUpdated);
        predictedRspm = findViewById(R.id.predictedRSPM);
        predictedSpm = findViewById(R.id.predictedSPM);

        selected_area = getIntent().getStringExtra("ButtonPressed");
        data_retrieve(selected_area);
        predicted_retrieve(selected_area);
    }

    private void data_retrieve(String selected_area) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference CPSReference = databaseReference.child(selected_area);
        Query lastQuery = CPSReference.orderByKey().limitToLast(1);
        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren())
                {
                    String SPM = String.valueOf(data.child("SPM").getValue());
                    String RSPM = String.valueOf(data.child("PM10").getValue());
                    String Date = String.valueOf(data.child("Date").getValue());
                    rspmText.setText("PM10: " + RSPM);
                    spmText.setText("PM2.5: " + SPM);
                    lastUpdated.setText("Last Updated: " + Date);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void predicted_retrieve(String selected_area) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference CPSReference = databaseReference.child("Predicted").child(selected_area);
        Query lastQuery = CPSReference.orderByKey().limitToFirst(1);
        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren())
                {
                    String SPM = String.valueOf(data.child("SPM").getValue());
                    String RSPM = String.valueOf(data.child("PM10").getValue());
                    predictedRspm.setText("RSPM Future: " + RSPM.substring(0,5));
                    predictedSpm.setText("SPM Future: " + SPM.substring(0,5));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
