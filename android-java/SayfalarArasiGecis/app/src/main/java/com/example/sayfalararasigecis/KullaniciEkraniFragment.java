package com.example.sayfalararasigecis;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sayfalararasigecis.databinding.FragmentAnaSayfaBinding;
import com.example.sayfalararasigecis.databinding.FragmentKullaniciEkraniBinding;

public class KullaniciEkraniFragment extends Fragment {

    private FragmentKullaniciEkraniBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        KullaniciEkraniFragmentArgs bundle = KullaniciEkraniFragmentArgs.fromBundle(getArguments());
        String gelenAd = bundle.getAd();
        int gelenSifre = bundle.getSifre();
        Kisiler gelenKisiler = bundle.getNesne();
        Log.e("gelen ad",gelenAd);
        Log.e("gelen sifre", String.valueOf(gelenSifre));
        Log.e("gelen kisi ad",gelenKisiler.getKisiName());
        Log.e("gelen kişi nesnesi şifre",String.valueOf(gelenKisiler.getKisiSifre()));


        binding = FragmentKullaniciEkraniBinding.inflate(inflater,container,false);



     return binding.getRoot();
    }
}