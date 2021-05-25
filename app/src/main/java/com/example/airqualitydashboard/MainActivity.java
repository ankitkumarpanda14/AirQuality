package com.example.airqualitydashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button cpsButton;
    Button ospcbButton;
    Button cspurButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cpsButton = findViewById(R.id.CPS_button);
        ospcbButton = findViewById(R.id.OSPCB_button);
        cspurButton = findViewById(R.id.CSPUR_button);
        cpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent activityChangeIntent = new Intent(MainActivity.this, DetailsActivity.class);
                activityChangeIntent.putExtra("ButtonPressed","CPS");
                // currentContext.startActivity(activityChangeIntent);
                MainActivity.this.startActivity(activityChangeIntent);
            }
        });
        ospcbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent activityChangeIntent = new Intent(MainActivity.this, DetailsActivity.class);
                activityChangeIntent.putExtra("ButtonPressed","CSPUR");
                // currentContext.startActivity(activityChangeIntent);
                MainActivity.this.startActivity(activityChangeIntent);
            }
        });
        cspurButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent activityChangeIntent = new Intent(MainActivity.this, DetailsActivity.class);
                activityChangeIntent.putExtra("ButtonPressed","CSPUR");
                // currentContext.startActivity(activityChangeIntent);
                MainActivity.this.startActivity(activityChangeIntent);
            }
        });

    }
}
