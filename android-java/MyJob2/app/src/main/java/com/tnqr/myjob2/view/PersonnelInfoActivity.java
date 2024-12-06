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
import com.tnqr.myjob2.databinding.ActivityPersonelInfoBinding;

public class PersonnelInfoActivity extends AppCompatActivity {
    ActivityPersonelInfoBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityPersonelInfoBinding.inflate(getLayoutInflater());
        View view= binding.getRoot();
        auth = FirebaseAuth.getInstance();
        setSupportActionBar(binding.personnelActivityToolbar);

        setContentView(view);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.signOut){
            auth.signOut();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);


        }
        if(item.getItemId() == R.id.mainPage){
            Intent intent = new Intent(getApplicationContext(), UserActivity.class);
            startActivity(intent);
        }



        return super.onOptionsItemSelected(item);
    }
}