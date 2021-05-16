package com.example.materPanel.UI;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.materPanel.R;

public class AdminCategory extends AppCompatActivity {
    ImageView tShirts, sportsTShirts, femaleDresses, sweathers;
    Button LogoutBtn, CheckOrdersBtn, maintainProductsBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        LogoutBtn = findViewById(R.id.admin_logout_btn);
        CheckOrdersBtn = findViewById(R.id.check_orders_btn);
        maintainProductsBtn = findViewById(R.id.maintain_btn);

        maintainProductsBtn.setOnClickListener(view -> {
            Intent intent = new Intent(AdminCategory.this, PracticeWork.class);
            intent.putExtra("Admin", "Admin");
            startActivity(intent);
        });


        LogoutBtn.setOnClickListener(view -> {
            Intent intent = new Intent(AdminCategory.this, Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        CheckOrdersBtn.setOnClickListener(view -> {
            Intent intent = new Intent(AdminCategory.this, AdminNewOrders.class);
            startActivity(intent);
        });

        tShirts = findViewById(R.id.t_shirts);
        sportsTShirts = findViewById(R.id.sports_t_shirts);
        femaleDresses = findViewById(R.id.female_dresses);
        sweathers = findViewById(R.id.sweathers);


        tShirts.setOnClickListener(view -> {
            Intent intent = new Intent(AdminCategory.this, AddData.class);
            intent.putExtra("category", "tShirts");
            startActivity(intent);
        });


        sportsTShirts.setOnClickListener(view -> {
            Intent intent = new Intent(AdminCategory.this, AddData.class);
            intent.putExtra("category", "Sports tShirts");
            startActivity(intent);
        });


        femaleDresses.setOnClickListener(view -> {
            Intent intent = new Intent(AdminCategory.this, AddData.class);
            intent.putExtra("category", "Female Dresses");
            startActivity(intent);
        });


        sweathers.setOnClickListener(view -> {
            Intent intent = new Intent(AdminCategory.this, AddData.class);
            intent.putExtra("category", "Sweathers");
            startActivity(intent);
        });


    }
}
