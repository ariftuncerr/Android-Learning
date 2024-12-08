package com.tnqr.myjob.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.tnqr.myjob.R;
import com.tnqr.myjob.databinding.ActivityLoginBinding;

public class loginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth firebaseAuth;

    private static final int RC_SIGN_IN = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        firebaseAuth = FirebaseAuth.getInstance();

        // Google Sign-In Options
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id)) // Firebase Web Client ID
                .requestEmail()
                .build();

        // Google Sign-In Client
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        //daha önceden kullanıcı girişi varsa onu bulur ve direkt anasayfaya yönlendirir
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
          navigateToHome();
        }

        // Google ile sign in olma butonunu kontrol eder.
        binding.signInwithGoogleButton.setOnClickListener(v -> {
            Intent signInIntent = googleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google hesabından başarılı giriş
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Log.w("GoogleSignIn", "Google sign in failed", e);
                Toast.makeText(this, "Google Sign-In failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Giriş başarılı, kullanıcı bilgilerini al
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        Log.d("FirebaseAuth", "Sign-in successful: " + user.getEmail());
                        navigateToHome();
                    } else {
                        // Giriş başarısız
                        Log.w("FirebaseAuth", "Sign-in failed", task.getException());
                        Toast.makeText(this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void navigateToHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }
    public void notRegisteredClick(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

}