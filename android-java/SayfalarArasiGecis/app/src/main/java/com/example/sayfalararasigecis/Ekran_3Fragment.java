package com.example.sayfalararasigecis;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sayfalararasigecis.databinding.FragmentEkran3Binding;
import com.example.sayfalararasigecis.databinding.FragmentKullaniciEkraniBinding;


public class Ekran_3Fragment extends Fragment {
    private FragmentEkran3Binding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEkran3Binding.inflate(inflater,container,false);
        binding.backTomain.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.anaSayfaFragment);

        });
        return binding.getRoot();
    }
}