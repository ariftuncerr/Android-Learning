package com.example.backstack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.backstack.databinding.ActivityBBinding;

public class ActivityB extends AppCompatActivity {
    private ActivityBBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.goTOCButton.setOnClickListener(view->
        {
            Intent pageC = new Intent(ActivityB.this,ActivityC.class);
            finish(); // B sayfasını back stackten siler c de iken geri tuşuna bastığımızda A sayfasını açar
            startActivity(pageC);

        });
    }
}