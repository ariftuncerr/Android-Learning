package com.example.javaapp1;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import com.example.javaapp1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //drawer menüsünü bağlama satırları
        NavHostFragment navHostFragment  =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(binding.navigationView,navHostFragment.getNavController());
        binding.toolbar.setTitle("Başlık");
        ActionBarDrawerToggle toggle=
                new ActionBarDrawerToggle(this,binding.drawer,binding.toolbar,0,0);
        binding.drawer.addDrawerListener(toggle);
        toggle.syncState();

        // drawedaki başlığı bu satırlarla yerleştirdik
        View baslik = binding.navigationView.inflateHeaderView(R.layout.drawer_baslik);
        TextView textViewBaslik = baslik.findViewById(R.id.textViewbaslik);
        textViewBaslik.setText("Merhaba"); // setText ile başka başlık koyduk

    }


}