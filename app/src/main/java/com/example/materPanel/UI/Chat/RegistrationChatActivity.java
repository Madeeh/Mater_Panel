package com.example.materPanel.UI.Chat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.materPanel.Models.UsersChat;
import com.example.materPanel.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import de.hdodenhof.circleimageview.CircleImageView;

public class RegistrationChatActivity extends AppCompatActivity {

    TextView txt_signin, btn_SignUp;
    CircleImageView profile_image;
    EditText reg_name, reg_email, reg_password, reg_cPassword;
    FirebaseAuth auth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Uri imageUri;
    FirebaseDatabase database;
    FirebaseStorage storage;
    String imageURI;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_chat);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);


        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        txt_signin = findViewById(R.id.txt_signin);
        profile_image = findViewById(R.id.profile_image);
        reg_email = findViewById(R.id.reg_email);
        reg_name = findViewById(R.id.reg_name);
        reg_password = findViewById(R.id.reg_pass);
        reg_cPassword = findViewById(R.id.reg_cPass);
        btn_SignUp = findViewById(R.id.btn_SignUp);


        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                String name = reg_name.getText().toString();
                String email = reg_email.getText().toString();
                String password = reg_password.getText().toString();
                String cPassword = reg_cPassword.getText().toString();
                String staus = "Hey There I'm Using This Application";


                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) ||
                        TextUtils.isEmpty(password) || TextUtils.isEmpty(cPassword)) {
                    progressDialog.dismiss();
                    Toast.makeText(RegistrationChatActivity.this, "Please Enter Valid Data", Toast.LENGTH_SHORT).show();
                } else if (!email.matches(emailPattern)) {
                    reg_email.setError("Please Enter Valid Email");
                    progressDialog.dismiss();

                    Toast.makeText(RegistrationChatActivity.this, "Please Enter Valid Email", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(cPassword)) {
                    progressDialog.dismiss();

                    Toast.makeText(RegistrationChatActivity.this, "Password does not Match", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    progressDialog.dismiss();

                    Toast.makeText(RegistrationChatActivity.this, "Enter 6 Character Password", Toast.LENGTH_SHORT).show();
                } else {
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                DatabaseReference reference = database.getReference().child("Admin").child("admin").child(auth.getUid());
                                StorageReference storageReference = storage.getReference().child("uplod").child(auth.getUid());

                                if (imageUri != null) {
                                    storageReference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                    @Override
                                                    public void onSuccess(Uri uri) {
                                                        imageURI = uri.toString();
                                                        UsersChat usersChat = new UsersChat(auth.getUid(), name, email, imageURI, staus);
                                                        reference.setValue(usersChat).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    progressDialog.dismiss();
                                                                    startActivity(new Intent(RegistrationChatActivity.this, HomeChatActivity.class));
                                                                } else {
                                                                    Toast.makeText(RegistrationChatActivity.this, "Error in Creating a New user", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        }
                                    });
                                } else {
                                    String staus = "Hey There I'm Using This Application";
                                    imageURI = "https://firebasestorage.googleapis.com/v0/b/quickchatapp-a181b.appspot.com/o/profile_image.png?alt=media&token=a6748395-5c27-492d-b73b-d6859d4b5d15";
                                    UsersChat usersChat = new UsersChat(auth.getUid(), name, email, imageURI, staus);
                                    reference.setValue(usersChat).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                startActivity(new Intent(RegistrationChatActivity.this, HomeChatActivity.class));
                                            } else {
                                                Toast.makeText(RegistrationChatActivity.this, "Error in Creating a New user", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }

                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(RegistrationChatActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 10);
            }
        });


        txt_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationChatActivity.this, LoginChatActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10) {
            if (data != null) {
                imageUri = data.getData();
                profile_image.setImageURI(imageUri);
            }
        }
    }
}