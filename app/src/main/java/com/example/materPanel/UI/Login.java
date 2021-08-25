package com.example.materPanel.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.materPanel.R;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    Button login;
    TextInputLayout username, password;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Boolean savelogin;
    CheckBox savelogincheckbox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.BtnLogin);
        username = findViewById(R.id.etEmail);
        password = findViewById(R.id.password);
        sharedPreferences = getSharedPreferences("loginref", MODE_PRIVATE);
        savelogincheckbox = (CheckBox) findViewById(R.id.checkBox);
        editor = sharedPreferences.edit();

        login.setOnClickListener(v -> login());

        savelogin = sharedPreferences.getBoolean("savelogin", true);
        if (savelogin == true) {
            username.getEditText().setText(sharedPreferences.getString("username", null));
            password.getEditText().setText(sharedPreferences.getString("password", null));
        }
    }

    public void login() {
        String user = username.getEditText().getText().toString().trim();
        String pass = password.getEditText().getText().toString().trim();
        if (user.equals("Admin") && pass.equals("Admin123")) {
            if (savelogincheckbox.isChecked()) {
                editor.putBoolean("savelogin", true);
                editor.putString("username", user);
                editor.putString("password", pass);
                editor.commit();

            }
            Intent intent = new Intent(Login.this, AdminDashboard.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "username and password do not matched!", Toast.LENGTH_LONG).show();
        }
    }
}