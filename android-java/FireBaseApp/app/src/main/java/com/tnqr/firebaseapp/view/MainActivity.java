package com.tnqr.firebaseapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tnqr.firebaseapp.R;
import com.tnqr.firebaseapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        auth = FirebaseAuth.getInstance();
        setContentView(view);

        FirebaseUser user = auth.getCurrentUser();

        if (user != null){

            Intent intent = new Intent(this, FeedActivity.class);
            startActivity(intent);
            finish();
        }






        EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void signInClicked(View view){
        String email = binding.emailText.getText().toString();
        String password = binding.passwordText.getText().toString();
        if (email.isEmpty() ||password.isEmpty() ){
            Toast.makeText(this, "Email ya da Password girilmedi", Toast.LENGTH_SHORT).show();

        }
        else {
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(getApplicationContext(), FeedActivity.class);
                    startActivity(intent);
                    finish();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public void signUpClicked(View view){
        String email = binding.emailText.getText().toString();
        String password = binding.passwordText.getText().toString();
        if (email.isEmpty() ||password.isEmpty() ){
            Toast.makeText(this, "Email ya da Password girilmedi", Toast.LENGTH_SHORT).show();

        }
        else {
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(getApplicationContext(), FeedActivity.class);
                    startActivity(intent);
                    finish();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }


    }


}