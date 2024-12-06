package com.tnqr.fragment;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.tnqr.fragment.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    }
    public void firstFragmentButtoon(View view){
        //bağlamak için fragment yönetici ve o bağlantıtı başlatacak Transaction
        FragmentManager fragmentManager =  getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FirstFragment firstFragment = new FirstFragment();

        fragmentTransaction.replace(,firstFragment ).commit();


    }
    public void secondFragmentButtoon(View view){
        FragmentManager fragmentManager =  getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SecondFragment secondFragment = new SecondFragment();

        fragmentTransaction.replace(,secondFragment ).commit();

    }


}