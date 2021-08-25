package com.example.materPanel.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.materPanel.R;

public class MapActivity extends AppCompatActivity {
    Button liveLocation, liveAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        liveLocation = findViewById(R.id.live_location);
        liveLocation.setOnClickListener(v -> {
            Intent intent = new Intent(MapActivity.this, MapsActivity.class);
            startActivity(intent);
        });
        liveAddress = findViewById(R.id.live_address);
        liveAddress.setOnClickListener(v -> {
            Intent intent = new Intent(MapActivity.this, GetLocationAddress.class);
            startActivity(intent);
        });
    }
}