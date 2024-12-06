package com.example.sayfalararasigecis;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sayfalararasigecis.databinding.FragmentAnaSayfaBinding;
public class AnaSayfaFragment extends Fragment {
    private FragmentAnaSayfaBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAnaSayfaBinding.inflate(inflater,container,false);

        binding.entryButton.setOnClickListener(v->{
            Kisiler kisiler = new Kisiler("Ahmet",2020);

           /* AnaSayfaFragmentDirections.KullaniciEkraniGecis gecis = AnaSayfaFragmentDirections.kullaniciEkraniGecis(kisiler);
            gecis.setAd("Arif");
            gecis.setSifre(6161);*/
            Navigation.findNavController(v).navigate(R.id.kullaniciEkraniGecis);

        });

        return binding.getRoot();
    }
}