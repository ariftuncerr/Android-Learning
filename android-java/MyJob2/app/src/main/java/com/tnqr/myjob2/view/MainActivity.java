package com.tnqr.myjob2.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.tnqr.myjob2.databinding.ActivityMainBinding;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    FirebaseUser user;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setSupportActionBar(binding.toolbar);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        user = auth.getCurrentUser();

        if (user != null) {
            getUser(); // Kullanıcı bilgilerini al
        }
    }

    public void newUser(View view) {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        String email = binding.loginGmailText.getText().toString();
        String password = binding.passwordText.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email ya da şifre girilmedi", Toast.LENGTH_SHORT).show();
        } else {
            auth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            // Giriş başarılıysa kullanıcıyı yönlendir
                            Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    public void getUser() {
        firebaseFirestore.collection("Users").whereEqualTo("email", user.getEmail())
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Toast.makeText(MainActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (value != null && !value.isEmpty()) {
                            for (DocumentSnapshot snapshot : value.getDocuments()) {
                                Map<String, Object> data = snapshot.getData();
                                name = (String) data.get("name");
                            }

                            if (name != null) {
                                // Kullanıcı adı başarıyla alındı, UserActivity'e gönder
                                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                                intent.putExtra("name", name);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }
                });
    }

}
