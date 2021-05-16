package com.example.materPanel.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.materPanel.Models.UsersChat;
import com.example.materPanel.R;
import com.example.materPanel.UI.Chat.ChatActivity;
import com.example.materPanel.UI.Chat.HomeChatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.Viewholder> {
    Context homeActivity;
    ArrayList<UsersChat> usersChatArrayList;

    public UserAdapter(HomeChatActivity homeChatActivity, ArrayList<UsersChat> usersChatArrayList) {
        this.homeActivity = homeChatActivity;
        this.usersChatArrayList = usersChatArrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(homeActivity).inflate(R.layout.item_user_row_chat, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        UsersChat usersChat = usersChatArrayList.get(position);

        if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(usersChat.getUid())) {
            holder.itemView.setVisibility(View.GONE);
        }

        holder.user_name.setText(usersChat.getName());
        holder.user_status.setText(usersChat.getStatus());
        Picasso.get().load(usersChat.getImageUri()).placeholder(R.drawable.profile).into(holder.user_profile);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homeActivity, ChatActivity.class);
                intent.putExtra("name", usersChat.getName());
                intent.putExtra("ReciverImage", usersChat.getImageUri());
                intent.putExtra("uid", usersChat.getUid());
                homeActivity.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return usersChatArrayList.size();
    }

    static class Viewholder extends RecyclerView.ViewHolder {
        CircleImageView user_profile;
        TextView user_name;
        TextView user_status;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            user_profile = itemView.findViewById(R.id.user_image);
            user_name = itemView.findViewById(R.id.user_name);
            user_status = itemView.findViewById(R.id.user_status);

        }
    }
}
