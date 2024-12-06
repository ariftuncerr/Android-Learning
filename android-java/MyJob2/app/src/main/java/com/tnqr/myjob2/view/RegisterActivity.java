package com.tnqr.myjob2.view;

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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tnqr.myjob2.R;
import com.tnqr.myjob2.databinding.ActivityRegisterBinding;
import com.tnqr.myjob2.model.User;

import java.util.HashMap;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    private  FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setSupportActionBar(binding.registerToolbar);
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();


    }
    public void register(View view){
        int userType = -1;  // Varsayılan olarak seçilmediğini belirtiyoruz

        if (binding.radioGroup.getCheckedRadioButtonId() == R.id.radioEmployee) {
            userType = 0; // Çalışan
        } else if (binding.radioGroup.getCheckedRadioButtonId() == R.id.radioEmployer) {
            userType = 1; // İşveren
        } else {
            Toast.makeText(this, "Kullanıcı tipini seçmelisin", Toast.LENGTH_SHORT).show();
            return;  // Eğer bir seçim yapılmadıysa işlemi durdur
        }
        System.out.println(userType);


        User user = new User(
                binding.nameText.getText().toString()
                ,binding.gmailText.getText().toString()
                ,binding.passwordText.getText().toString()
                ,userType
                );
        if (user == null)
            Toast.makeText(this, "Kullancı Bilgileri eksik Girildi", Toast.LENGTH_SHORT).show();
        else {
            auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {

                    HashMap<String, Object> userData = new HashMap<>();
                    userData.put("name",user.getName());
                    userData.put("gmail",user.getEmail());
                    userData.put("userType",user.getUserType());

                    firebaseFirestore.collection("Users").add(userData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(RegisterActivity.this, "Başarıyla Kaydolundu", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegisterActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });



                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegisterActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}