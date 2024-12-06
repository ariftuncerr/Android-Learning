package com.tnqr.sqlite2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.tnqr.sqlite2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("COLLEGE",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS Students (id INTEGER PRIMARY KEY, name VARCHAR,age INT)");

            database.execSQL("INSERT INTO Students(name,age) VALUES ('Boncuk','21')");
            database.execSQL("INSERT INTO Students(name,age) VALUES ('Yaren','22')");

            Cursor cursor = database.rawQuery("SELECT * FROM STUDENTS WHERE id =1 ",null);
            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");
            String name = cursor.getString(nameIx);



            binding.nameText.setText(name);
            cursor.close();


        }
        catch (Exception e){
            e.printStackTrace();
        }



    }
}