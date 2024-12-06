package com.example.sayfalararasigecis;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.sayfalararasigecis.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(binding.navigatipnView,navHostFragment.getNavController());
        binding.toolbar.setTitle("Başlık");

        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this,binding.drawer,binding.toolbar,0,0);
        binding.drawer.addDrawerListener(toggle);
        toggle.syncState();


    }
}