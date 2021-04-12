package com.example.materPanel.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.materPanel.R;

public class Login extends AppCompatActivity {

    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        button = findViewById(R.id.btnSignIn);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, TestWork.class);
            startActivity(intent);
        });

    }
}