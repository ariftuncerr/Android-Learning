package com.tnqr.artbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.tnqr.artbook.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    ArrayList<Art> arts;
    ArtAdapter artAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);
        setSupportActionBar(binding.toolbar);
        arts = new ArrayList<Art>();
        binding.recylerView.setLayoutManager(new LinearLayoutManager(this));
        artAdapter = new ArtAdapter(arts);
        binding.recylerView.setAdapter(artAdapter);

        getData();




    }
    private void getData(){
        try {
            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Arts",MODE_PRIVATE,null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT *FROM Arts",null);
            int nameIx = cursor.getColumnIndex("artName");
            int idIx = cursor.getColumnIndex("id");
            while (cursor.moveToNext()){
                String name = cursor.getString(nameIx);
                int id = cursor.getInt(idIx);
                Art art = new Art(name,id);
                arts.add(art);



            }
            artAdapter.notifyDataSetChanged();
            cursor.close();


        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //menü bağlayıcı
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.art_menu,menu);


        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_art){
            Intent intent = new Intent(this, ArtActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}