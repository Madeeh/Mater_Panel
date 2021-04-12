package com.example.materPanel.UI;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.materPanel.Models.model;
import com.example.materPanel.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;
import java.util.Random;

public class AddData extends AppCompatActivity {

    EditText title, category, price;
    Uri filepath;
    ImageView img;
    Bitmap bitmap;
    Button submit, back, browse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);


        img = findViewById(R.id.img);
        browse = findViewById(R.id.browse);
        title = findViewById(R.id.add_title);
        price = findViewById(R.id.add_price);
        category = findViewById(R.id.add_category);


        browse.setOnClickListener(view -> Dexter.withActivity(AddData.this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "Select Image File"), 1);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check());

        back = findViewById(R.id.add_back);
        back.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), TestWork.class));
            finish();
        });

        submit = findViewById(R.id.add_submit);
        submit.setOnClickListener(view -> processInsert());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            filepath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
            } catch (Exception ex) {

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void processInsert() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("File Uploader");
        dialog.show();

        title = findViewById(R.id.add_title);
        category = findViewById(R.id.add_category);
        price = findViewById(R.id.add_price);


        FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference uploader = storage.getReference("Image1" + new Random().nextInt(50));

        uploader.putFile(filepath)
                .addOnSuccessListener(taskSnapshot -> uploader.getDownloadUrl().addOnSuccessListener(uri -> {

                    dialog.dismiss();
                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference root = db.getReference("products");

                    model obj = new model(title.getText().toString(), category.getText().toString(), price.getText().toString(), uri.toString());
                    root.child(category.getText().toString()).setValue(obj);

                    title.setText("");
                    category.setText("");
                    price.setText("");
                    img.setImageResource(R.drawable.black_image);
                    Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_LONG).show();
                }))
                .addOnProgressListener(taskSnapshot -> {
                    float percent = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                    dialog.setMessage("Uploaded :" + (int) percent + " %");
                });

    }

}