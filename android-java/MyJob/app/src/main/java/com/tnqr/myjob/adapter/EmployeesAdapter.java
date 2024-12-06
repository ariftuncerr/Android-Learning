package com.tnqr.myjob.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tnqr.myjob.User;
import com.tnqr.myjob.databinding.RecyclerRowBinding;

import java.util.List;

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesAdapter.EmployeesHolder> {
    List<User> employeesList;

    public EmployeesAdapter(List<User> employeesList) {
       this.employeesList = employeesList;
    }

    @NonNull
    @Override
    //her yeni liste elamnı için layoutu inflate eder (xml i view objesine .evirir)
    public EmployeesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new EmployeesHolder(recyclerRowBinding);
    }
    //her satırdaki eleamanın pozisyonuna göre işlem yapmamızı sağlar
    @Override
    public void onBindViewHolder(@NonNull EmployeesHolder holder, int position) {
        holder.recyclerRowBinding.recyclerRowText.setText(employeesList.get(position).name);

    }

    @Override
    public int getItemCount() {
        return employeesList.size();
    }

    public class EmployeesHolder extends RecyclerView.ViewHolder{
        RecyclerRowBinding recyclerRowBinding;
        public EmployeesHolder(RecyclerRowBinding recyclerRowBinding) {
            super(recyclerRowBinding.getRoot());
            this.recyclerRowBinding = recyclerRowBinding;
        }
    }
}
