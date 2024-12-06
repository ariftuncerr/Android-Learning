package com.tnqr.myjob;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tnqr.myjob.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        EdgeToEdge.enable(this);
        setContentView(view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.accountLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
    }
    public void register(View view) {
        String name = binding.nameText.getText().toString();
        String gmail = binding.gmailText.getText().toString();
        String userType = binding.userTypeText.getText().toString();
        String password = binding.passwordText.getText().toString();

        // Girdi kontrolü (boş alanlar kontrolü)
        if (name.isEmpty() || gmail.isEmpty() || userType.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Lütfen tüm alanları doldurunuz", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("MyJob", MODE_PRIVATE, null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Users(id INTEGER PRIMARY KEY, name VARCHAR, gmail VARCHAR, password VARCHAR, userType VARCHAR)");

            // SQL sorgusu
            String sqlString = "INSERT INTO Users(name, gmail, password, userType) VALUES(?, ?, ?, ?)";
            SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sqlString);

            // Verileri bağla
            sqLiteStatement.bindString(1, name);
            sqLiteStatement.bindString(2, gmail);
            sqLiteStatement.bindString(3, password);
            sqLiteStatement.bindString(4, userType);

            // Veritabanına veriyi ekle
            sqLiteStatement.executeInsert();  // Burada executeInsert() çağrılmalı

            Toast.makeText(this, "Başarıyla kaydolundu", Toast.LENGTH_SHORT).show();

            // MainActivity'e geçiş yap
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}