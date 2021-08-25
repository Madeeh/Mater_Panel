package com.example.materPanel.UI;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.materPanel.R;

public class AdminCategory extends AppCompatActivity {
    ImageView tShirts, sportsTShirts, femaleDresses, sweathers;
    Button addPriceListWash, addPriceListIron, addPriceListWashAndIron;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);


        addPriceListWash = findViewById(R.id.add_price_list_wash);
        addPriceListIron = findViewById(R.id.add_price_list_Iron);
        addPriceListWashAndIron = findViewById(R.id.add_price_list_wash_and_iron);

        tShirts = findViewById(R.id.t_shirts);
        sportsTShirts = findViewById(R.id.sports_t_shirts);
        femaleDresses = findViewById(R.id.female_dresses);
        sweathers = findViewById(R.id.sweathers);


        addPriceListIron.setOnClickListener(v -> {
            Intent intent = new Intent(AdminCategory.this, AddPriceListIron.class);
            startActivity(intent);
        });
        addPriceListWashAndIron.setOnClickListener(v -> {
            Intent intent = new Intent(AdminCategory.this, AddPriceListWashAndIron.class);
            startActivity(intent);
        });

        addPriceListWash.setOnClickListener(v -> {
            Intent intent = new Intent(AdminCategory.this, AddPriceListWash.class);
            startActivity(intent);
        });

        tShirts.setOnClickListener(view -> {
            Intent intent = new Intent(AdminCategory.this, AddDataWomen.class);
            intent.putExtra("category", "tShirts");
            startActivity(intent);
        });


        sportsTShirts.setOnClickListener(view -> {
            Intent intent = new Intent(AdminCategory.this, AddDataMen.class);
            intent.putExtra("category", "Sports tShirts");
            startActivity(intent);
        });


        femaleDresses.setOnClickListener(view -> {
            Intent intent = new Intent(AdminCategory.this, AddDataKids.class);
            intent.putExtra("category", "Female Dresses");
            startActivity(intent);
        });


        sweathers.setOnClickListener(view -> {
            Intent intent = new Intent(AdminCategory.this, AddDataOthers.class);
            intent.putExtra("category", "Sweathers");
            startActivity(intent);
        });
    }
}
