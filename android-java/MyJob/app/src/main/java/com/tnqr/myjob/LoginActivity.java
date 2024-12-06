package com.tnqr.myjob;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tnqr.myjob.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.accountLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void login(View view) {
        SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("MyJob", MODE_PRIVATE, null);
        Cursor cursor = null;

        try {
            // Kullanıcı adı ve şifre boşluk kontrolü
            String name = binding.loginGmailText.getText().toString();
            String pass = binding.passwordText.getText().toString();

            if (name.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Lütfen kullanıcı adı ve şifre giriniz", Toast.LENGTH_SHORT).show();
                return; // Boş ise işlem yapılmıyor
            }

            cursor = sqLiteDatabase.rawQuery("SELECT * FROM Users", null);
            int gmailIx = cursor.getColumnIndex("gmail");
            int passIx = cursor.getColumnIndex("password");
            int idIx = cursor.getColumnIndex("id");

            boolean loginSuccess = false;
            int succesLoginId =-1;

            while (cursor.moveToNext()) {
                String dbName = cursor.getString(gmailIx);
                String dbPass = cursor.getString(passIx);



                // Kullanıcı adı ve şifre kontrolü
                if (name.equals(dbName) && pass.equals(dbPass)) {
                    loginSuccess = true;
                    succesLoginId = cursor.getInt(idIx);

                    break;
                    // Eşleşme bulunduysa döngüyü bitir
                }
            }

            // Giriş başarılı mı kontrol et
            if (loginSuccess) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("LoginID",succesLoginId);
                //id bilgisini sharedpreferences a kaydetme ve bu sayede account vb bölgelerde id den bilgilere ulaşmak
                getSharedPreferences("loginID", MODE_PRIVATE)
                        .edit()
                        .putInt("LoginID", succesLoginId)
                        .apply();
                startActivity(intent);
            } else {
                Toast.makeText(this, "Yanlış kullanıcı adı veya şifre", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close(); // Cursor'u kapatma
            }
        }
    }



    public void newUser (View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }
}