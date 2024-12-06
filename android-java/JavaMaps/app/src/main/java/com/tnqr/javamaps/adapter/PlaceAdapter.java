package com.tnqr.javamaps.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Placeholder;
import androidx.recyclerview.widget.RecyclerView;

import com.tnqr.javamaps.databinding.RecyclerRowBinding;
import com.tnqr.javamaps.model.Place;
import com.tnqr.javamaps.view.MapsActivity;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceHolder> {

    //adaptere gelecek listeyi alıp bu listeyi kullanmak için oluşturduğumuz değişken ve consructer
    List<Place> places;

    public PlaceAdapter(List<Place> places) {
        this.places = places;
    }

    @NonNull
    @Override

    //
    public PlaceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new PlaceHolder(recyclerRowBinding);
    }

    @Override
    //bağlanan görünmündeki her elemanın posisyonunu göre işlemler yapabilceğimiz bölüm
    public void onBindViewHolder(@NonNull PlaceHolder holder, int position) {
        holder.recyclerRowBinding.recylerViewTextView.setText(places.get(position).name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(holder.itemView.getContext(), MapsActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });


    }


    //listedeki eleman sayısını tutacak kısım
    @Override
    public int getItemCount() {
        return places.size();
    }


//satır olarak olan görünümü gelip bağlama işlemi
    public class PlaceHolder extends RecyclerView.ViewHolder{

       RecyclerRowBinding recyclerRowBinding;
        public PlaceHolder(RecyclerRowBinding recyclerRowBinding) {
            super(recyclerRowBinding.getRoot());
            this.recyclerRowBinding=recyclerRowBinding;
        }
    }
}
