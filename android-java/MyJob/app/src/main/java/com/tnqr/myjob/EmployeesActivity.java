package com.tnqr.myjob;

import android.content.Intent;
import android.database.Cursor;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tnqr.myjob.adapter.EmployeesAdapter;
import com.tnqr.myjob.databinding.ActivityEmployeesBinding;

import java.util.ArrayList;
import java.util.List;

public class EmployeesActivity extends AppCompatActivity {
    ActivityEmployeesBinding binding;
    private EmployeesAdapter employeesAdapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityEmployeesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        userList = new ArrayList<>();

        setSupportActionBar(binding.toolbar2);

        //çalışanları sayfada gösterme
        saveEmployees();





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
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
    public void saveEmployees(){
        try {
            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("MyJob", MODE_PRIVATE, null);
            Cursor cursor = null;
            cursor = sqLiteDatabase.rawQuery("SELECT * FROM Users", null);
            int nameIx = cursor.getColumnIndex("name");
            int userTypeIx = cursor.getColumnIndex("userType");
            int idIx = cursor.getColumnIndex("id");
            int gmailIx = cursor.getColumnIndex("gmail");


            while (cursor.moveToNext()) {
                User user = new User(cursor.getString(nameIx),cursor.getInt(idIx), cursor.getString(gmailIx),cursor.getString(userTypeIx) );
                userList.add(user);
                System.out.println(user.name);


            }
            cursor.close();
            employeesAdapter = new EmployeesAdapter(userList);
            binding.RecyclerView.setLayoutManager(new LinearLayoutManager(this));
            binding.RecyclerView.setAdapter(employeesAdapter);





        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}