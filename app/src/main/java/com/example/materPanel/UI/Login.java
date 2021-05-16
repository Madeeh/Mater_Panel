package com.example.materPanel.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.materPanel.R;
import com.example.materPanel.UI.Chat.LoginChatActivity;

public class Login extends AppCompatActivity {

    Button button, test, chatBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        chatBtn = findViewById(R.id.chatBtn);
        chatBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, LoginChatActivity.class);
            startActivity(intent);
        });
        test = findViewById(R.id.button);
        test.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, AdminCategory.class);
            startActivity(intent);
        });
        button = findViewById(R.id.btnSignIn);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, TestWork.class);
            startActivity(intent);
        });

    }
}