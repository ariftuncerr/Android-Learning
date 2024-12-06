package com.tnqr.myjob;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tnqr.myjob.adapter.EmployeesAdapter;
import com.tnqr.myjob.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setSupportActionBar(binding.toolbar);

        setContentView(view);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.accountLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //menü bağlayıcı
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.sidebar_menu,menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_daily){
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.account){
            Intent intent = new Intent(this,AccountActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.employeesItem){
            Intent intent = new Intent(this, EmployeesActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);



            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}