package com.example.loginekran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.loginekran.databinding.ActivityIkinciEkranBinding;
import com.example.loginekran.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class ikinciEkran extends AppCompatActivity {
    private ActivityIkinciEkranBinding tasarim2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tasarim2 = ActivityIkinciEkranBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ikinci_ekran);
            tasarim2.goToMainButton.setOnClickListener(view -> {
                Snackbar.make(view, "Main Bloğa dönebildiniz", Snackbar.LENGTH_SHORT).show();
                Intent mainclass = new Intent(ikinciEkran.this, MainActivity.class);

                startActivity(mainclass);


            });

    }
}