package com.example.backstack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.backstack.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.goToBButton.setOnClickListener(view->
        {
            Intent pageB = new Intent(MainActivity.this,ActivityB.class);
            finish();
            startActivity(pageB);

        });

    }
}