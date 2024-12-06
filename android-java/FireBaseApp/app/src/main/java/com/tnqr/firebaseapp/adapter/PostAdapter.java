package com.tnqr.firebaseapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tnqr.firebaseapp.databinding.RecyclerRowBinding;
import com.tnqr.firebaseapp.model.Post;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {
    private ArrayList<Post> postArrayList;

    public PostAdapter(ArrayList<Post> postArrayList) {
        this.postArrayList = postArrayList;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new PostHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.recyclerRowBinding.postName.setText(postArrayList.get(position).postName);
        holder.recyclerRowBinding.commentText.setText(postArrayList.get(position).comment);
        holder.recyclerRowBinding.userText.setText(postArrayList.get(position).email);
        Picasso.get().load(postArrayList.get(position).downloadUrl).into(holder.recyclerRowBinding.recyclerViewImageView);


    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

    class PostHolder extends RecyclerView.ViewHolder{
        RecyclerRowBinding recyclerRowBinding;

        public PostHolder(@NonNull RecyclerRowBinding recyclerRowBinding) {
            super(recyclerRowBinding.getRoot());
            this.recyclerRowBinding = recyclerRowBinding;
        }
    }
}
