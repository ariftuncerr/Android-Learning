package com.example.backstack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.backstack.databinding.ActivityCBinding;

public class ActivityC extends AppCompatActivity {
    private ActivityCBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.goToAButton.setOnClickListener(view->
        {
            Intent pageA = new Intent(ActivityC.this,MainActivity.class);
            startActivity(pageA);
        });

    }
}