package com.example.chatapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.databinding.ItemContainerUserBinding;

import java.util.List;

public class usersAdapter extends RecyclerView.Adapter<usersAdapter.UserViewHolder> {
    private final List<User> users;
    private final UserListeners userListeners;

    public usersAdapter(List<User> users, UserListeners userListeners) {
        this.users = users;
        this.userListeners=userListeners;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContainerUserBinding itemContainerUserBinding = ItemContainerUserBinding.inflate(

                LayoutInflater.from(parent.getContext()),
                parent, false
        );
        return new UserViewHolder(itemContainerUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull usersAdapter.UserViewHolder holder, int position) {
        holder.setUserData(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        ItemContainerUserBinding binding;

        UserViewHolder(ItemContainerUserBinding itemContainerUserBinding) {
            super(itemContainerUserBinding.getRoot());
            binding = itemContainerUserBinding;
        }

        void setUserData(User user) {
            binding.userName.setText(user.name);
            binding.userEmail.setText(user.email);
            binding.imageProfile.setImageBitmap(getUerImage(user.image));
            binding.getRoot().setOnClickListener(v-> userListeners.onUserClicked(user));

        }
    }

    private Bitmap getUerImage(String encodedImage) {
        byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
