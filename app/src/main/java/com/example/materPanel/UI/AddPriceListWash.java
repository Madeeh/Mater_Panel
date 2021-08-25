package com.example.materPanel.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.materPanel.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddPriceListWash extends AppCompatActivity {

    private String iName, iPrice, saveCurrentDate, saveCurrentTime;
    private Button AddNewPrice;
    private EditText ItemName, ItemPrice;
    private String productRandomKey;
    private DatabaseReference ProductsRef;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_price_list);


        ProductsRef = FirebaseDatabase.getInstance().getReference().child("PriceList").child("Wash");


        AddNewPrice = findViewById(R.id.add_new_price);
        ItemName = findViewById(R.id.itemName);
        ItemPrice = findViewById(R.id.itemPrice);
        loadingBar = new ProgressDialog(this);

        AddNewPrice.setOnClickListener(view -> ValidateProductData());
    }


    private void ValidateProductData() {
        iName = ItemPrice.getText().toString();
        iPrice = ItemName.getText().toString();


        if (TextUtils.isEmpty(iName)) {
            Toast.makeText(this, "Please write product Name...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(iPrice)) {
            Toast.makeText(this, "Please write product Price...", Toast.LENGTH_SHORT).show();
        } else {
            StoreProductInformation();
        }
    }


    private void StoreProductInformation() {
        loadingBar.setTitle("Add New Product");
        loadingBar.setMessage("Dear Admin, please wait while we are adding the new product.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;

        HashMap<String, Object> productMap = new HashMap<>();
        productMap.put("pTitle", iPrice);
        productMap.put("pPrice", iName);

        ProductsRef.child(productRandomKey).updateChildren(productMap)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(AddPriceListWash.this, AdminCategory.class);
                        startActivity(intent);

                        loadingBar.dismiss();
                        Toast.makeText(AddPriceListWash.this, "Product is added successfully..", Toast.LENGTH_SHORT).show();
                    } else {
                        loadingBar.dismiss();
                        String message = task.getException().toString();
                        Toast.makeText(AddPriceListWash.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                    }
                });

    }

}