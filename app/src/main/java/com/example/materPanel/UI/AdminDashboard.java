package com.example.materPanel.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.materPanel.R;
import com.example.materPanel.UI.Chat.LoginChatActivity;

public class AdminDashboard extends AppCompatActivity {
    Button chatBtn, CheckOrdersBtn, addProducts, maintainProductsBtn, LogoutBtn, MapBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        MapBtn = findViewById(R.id.mapBtn);
        MapBtn.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboard.this, MapActivity.class);
            startActivity(intent);
        });

        chatBtn = findViewById(R.id.chatBtn);
        chatBtn.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboard.this, LoginChatActivity.class);
            startActivity(intent);
        });

        CheckOrdersBtn = findViewById(R.id.check_orders_btn);
        CheckOrdersBtn.setOnClickListener(view -> {
            Intent intent = new Intent(AdminDashboard.this, AdminNewOrders.class);
            startActivity(intent);
        });

        addProducts = findViewById(R.id.add_products);
        addProducts.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboard.this, AdminCategory.class);
            startActivity(intent);
        });

        maintainProductsBtn = findViewById(R.id.maintain_btn);

        maintainProductsBtn.setOnClickListener(view -> {
            Intent intent = new Intent(AdminDashboard.this, PracticeWork.class);
            intent.putExtra("Admin", "Admin");
            startActivity(intent);
        });

        LogoutBtn = findViewById(R.id.admin_logout_btn);
        LogoutBtn.setOnClickListener(view -> {
            Intent intent = new Intent(AdminDashboard.this, Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

    }
}