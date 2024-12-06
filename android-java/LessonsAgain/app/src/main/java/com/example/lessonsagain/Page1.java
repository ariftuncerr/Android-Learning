package com.example.lessonsagain;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lessonsagain.databinding.FragmentPage1Binding;

public class Page1 extends Fragment {
    private FragmentPage1Binding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentPage1Binding.inflate(inflater,container,false);
       binding.Enter.setOnClickListener(v->{
           Navigation.findNavController(v).navigate(R.id.Page);
        });


        return binding.getRoot();
    }
}