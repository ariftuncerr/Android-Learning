package com.example.navigationcopmlement;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.navigationcopmlement.databinding.ActivityMainBinding;
import com.example.navigationcopmlement.databinding.FragmentMainBinding;
import com.example.navigationcopmlement.databinding.FragmentOyunEkraniBinding;

public class OyunEkraniFragment extends Fragment {
   private FragmentOyunEkraniBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOyunEkraniBinding.inflate(inflater,container,false);
        binding.playbutton.setOnClickListener(v ->{
            Navigation.findNavController(v).navigate(R.id.sonucEkraniGecis);

        });

        return binding.getRoot();
    }
}