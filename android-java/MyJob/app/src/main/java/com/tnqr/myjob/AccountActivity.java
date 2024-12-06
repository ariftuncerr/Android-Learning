package com.tnqr.myjob;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tnqr.myjob.databinding.ActivityAccountBinding;

public class AccountActivity extends AppCompatActivity {
    ActivityAccountBinding binding;
    int loginId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAccountBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setSupportActionBar(binding.toolbar);
        SharedPreferences prefs = getSharedPreferences("loginID", MODE_PRIVATE);
        loginId = prefs.getInt("LoginID", -1);
        saveAccountInfo(loginId);


        setContentView(view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.accountLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
    }
    public void saveAccountInfo(int loginId) {


        // Geçerli bir loginId olup olmadığını kontrol et
        if (loginId == -1) {
            Toast.makeText(this, "Geçersiz giriş ID", Toast.LENGTH_SHORT).show();
            return; // Geçersiz ID durumunda çık
        }

        SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("MyJob", MODE_PRIVATE, null);
        Cursor cursor = null;

        try {
            // Parametreli sorgu kullanarak veritabanından veri çek
            cursor = sqLiteDatabase.rawQuery("SELECT * FROM Users WHERE id = ?", new String[]{String.valueOf(loginId)});

            // Cursor'un null olup olmadığını ve ilk satırda verinin olup olmadığını kontrol et
            if (cursor != null && cursor.moveToFirst()) {
                int nameIx = cursor.getColumnIndex("name");
                int userTypeIx = cursor.getColumnIndex("userType");
                int gmailIx = cursor.getColumnIndex("gmail");

                // Verileri alma işlemi
                String name = cursor.getString(nameIx);
                String userType = cursor.getString(userTypeIx);
                String gmail = cursor.getString(gmailIx);

                // UI elementlerine değer atama
                binding.accountNameText.setText(name);
                binding.accountGmailText.setText(gmail);
                binding.accountUserTypeText.setText(userType);
            } else {
                Toast.makeText(this, "Kullanıcı bulunamadı", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close(); // Cursor'u burada kapat
            }
        }
    }
    public void updateUserType(View view) {
        SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("MyJob", MODE_PRIVATE, null);

        // Mevcut kullanıcı türünü al
        String userType = binding.accountUserTypeText.getText().toString();
        String newUserType;

        if (userType.equals("user")) {
            newUserType = "İşveren";
        } else {
            newUserType = "İşçi";
        }

        // ContentValues kullanarak güncelleme işlemi
        ContentValues contentValues = new ContentValues();
        contentValues.put("userType", newUserType);

        // Güncelleme işlemi
        try {
            int rowsAffected = sqLiteDatabase.update("Users", contentValues, "id = ?", new String[]{String.valueOf(loginId)});

            if (rowsAffected > 0) {
                Toast.makeText(this, "Kullanıcı türü güncellendi", Toast.LENGTH_SHORT).show();
                saveAccountInfo(loginId);
            } else {
                Toast.makeText(this, "Güncelleme başarısız", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Güncelleme başarısız", Toast.LENGTH_SHORT).show();
        } finally {
            sqLiteDatabase.close(); // Veritabanını kapat
        }

    }



    //sidebar menü bölümü
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