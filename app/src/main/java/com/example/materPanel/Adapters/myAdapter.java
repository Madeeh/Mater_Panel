package com.example.materPanel.Adapters;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.materPanel.Models.model;
import com.example.materPanel.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class myAdapter extends FirebaseRecyclerAdapter<model, myAdapter.myViewHolder> {
    public myAdapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull model model) {
        holder.title.setText(model.getTitle());
        holder.category.setText(model.getCategory());
        holder.price.setText(model.getPrice());
        Glide.with(holder.img.getContext()).load(model.getPImage()).into(holder.img);


        holder.edit.setOnClickListener(view -> {
            final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                    .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                    .setExpanded(true, 1100)
                    .create();

            View myView = dialogPlus.getHolderView();
            final EditText pImage = myView.findViewById(R.id.uImgUrl);
            final EditText title = myView.findViewById(R.id.uTitle);
            final EditText category = myView.findViewById(R.id.uCategory);
            final EditText price = myView.findViewById(R.id.uPrice);
            Button submit = myView.findViewById(R.id.uSubmit);

            pImage.setText(model.getPImage());
            title.setText(model.getTitle());
            category.setText(model.getCategory());
            price.setText(model.getPrice());

            dialogPlus.show();

            submit.setOnClickListener(view1 -> {
                Map<String, Object> map = new HashMap<>();
                map.put("pImage", pImage.getText().toString());
                map.put("title", title.getText().toString());
                map.put("price", price.getText().toString());
                map.put("category", category.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("products")
                        .child(getRef(position).getKey()).updateChildren(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                dialogPlus.dismiss();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                dialogPlus.dismiss();
                            }
                        });
            });


        });


        holder.delete.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.img.getContext());
            builder.setTitle("Delete Panel");
            builder.setMessage("Delete...?");

            builder.setPositiveButton("Yes", (dialogInterface, i) -> FirebaseDatabase.getInstance().getReference().child("products")
                    .child(getRef(position).getKey()).removeValue());

            builder.setNegativeButton("No", (dialogInterface, i) -> {

            });

            builder.show();
        });

    } // End of OnBindViewMethod


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow, parent, false);
        return new myViewHolder(view);
    }


    static class myViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        ImageButton edit, delete;
        TextView title, category, price;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img1);
            title = itemView.findViewById(R.id.etTitle);
            category = itemView.findViewById(R.id.etCategory);
            price = itemView.findViewById(R.id.etPrice);

            edit = itemView.findViewById(R.id.editIcon);
            delete = itemView.findViewById(R.id.deleteIcon);

        }
    }

}
