package com.example.navigationcopmlement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.navigationcopmlement.databinding.FragmentOyunEkraniBinding;
import com.example.navigationcopmlement.databinding.FragmentSonucEkraniBinding;


public class SonucEkraniFragment extends Fragment {
    private FragmentSonucEkraniBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSonucEkraniBinding.inflate(inflater,container,false);
        binding.resultButton.setOnClickListener(v ->{

        });

        return binding.getRoot();
    }
}