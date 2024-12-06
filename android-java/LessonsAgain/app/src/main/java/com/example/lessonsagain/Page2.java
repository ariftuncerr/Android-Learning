package com.example.lessonsagain;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lessonsagain.databinding.FragmentPage2Binding;



public class Page2 extends Fragment {
    private FragmentPage2Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPage2Binding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}