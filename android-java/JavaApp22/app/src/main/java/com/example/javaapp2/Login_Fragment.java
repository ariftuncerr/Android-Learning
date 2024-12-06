package com.example.javaapp2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.javaapp2.databinding.FragmentLoginBinding;
import com.google.android.material.snackbar.Snackbar;

public class Login_Fragment extends Fragment {
    private FragmentLoginBinding binding;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       binding = FragmentLoginBinding.inflate(inflater,container,false);
       binding.entryButton.setOnClickListener(v -> {
           String getUserName = binding.userEditText.getText().toString();
           int getPass = Integer.parseInt(binding.userPass.getText().toString());

           if (getPass==6161 && getUserName.equals("Arif")) {
               Login_FragmentDirections.UserInfoGecis gecis = Login_FragmentDirections.userInfoGecis(getUserName);
               gecis.setUserName(getUserName);
               Navigation.findNavController(v).navigate(R.id.userInfo_Fragment);
               Snackbar.make(v, "Giriş Yapabildiniz", Snackbar.LENGTH_SHORT).show();

           }
           else{
               Snackbar.make(v, "Hatalı Kullanıcı Adı veya şifre", Snackbar.LENGTH_SHORT).show();

           }





       });
        return binding.getRoot();
    }
}