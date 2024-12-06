package com.tnqr.myjob2.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.tnqr.myjob2.R;
import com.tnqr.myjob2.databinding.ActivityUserBinding;

public class UserActivity extends AppCompatActivity {
    private ActivityUserBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setSupportActionBar(binding.userToolbar);
        String name = getIntent().getStringExtra("name");
        binding.userNameText.setText(name);





    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.signOut){



        }
        if(item.getItemId() == R.id.mainPage){
            Intent intent = new Intent(getApplicationContext(), UserActivity.class);
            startActivity(intent);
        }



        return super.onOptionsItemSelected(item);

    }
}