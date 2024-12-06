package com.example.loginekran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.loginekran.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding tasarim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String name = "Arif";
        int password = 6161;
        super.onCreate(savedInstanceState);
        tasarim = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(tasarim.getRoot());
            tasarim.loginbutton.setOnClickListener(view -> {


                    Intent digersayfa = new Intent(MainActivity.this, ikinciEkran.class);
                    String getUserName = tasarim.user.getText().toString();
                    int getUserPass = Integer.parseInt(tasarim.passw.getText().toString());
                Log.e("gelenad",getUserName);
                if (getUserPass == password && getUserName.equals(name)) {
                        startActivity(digersayfa);
                        Snackbar.make(view, "Giriş Yapabildiniz", Snackbar.LENGTH_SHORT).show();
                    }
                else {
                    Snackbar.make(view, "Yanlış Kullanici adi veya şifre", Snackbar.LENGTH_SHORT).show();

                }



            });

        }




}