package com.example.navigationcopmlement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.navigationcopmlement.databinding.FragmentMainBinding;


public class MainFragment extends Fragment {
    private FragmentMainBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMainBinding.inflate(inflater,container,false);
        binding.entryButton.setOnClickListener(v ->{
            Navigation.findNavController(v).navigate(R.id.oyunEkraniGecis);

        });

        return binding.getRoot();
    }
}