package com.tnqr.javamaps.view;

import android.content.Intent;
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
import androidx.room.Room;

import com.tnqr.javamaps.R;
import com.tnqr.javamaps.adapter.PlaceAdapter;
import com.tnqr.javamaps.databinding.ActivityMainBinding;
import com.tnqr.javamaps.model.Place;
import com.tnqr.javamaps.roomdb.PlaceDao;
import com.tnqr.javamaps.roomdb.PlaceDatabase;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    PlaceDatabase db;
    PlaceDao placeDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        EdgeToEdge.enable(this);
        setContentView(view);
        setSupportActionBar(binding.toolbar);
        db = Room.databaseBuilder(getApplicationContext(),PlaceDatabase.class,"Place").fallbackToDestructiveMigration().build();
        placeDao = db.placeDao();

        compositeDisposable.add(placeDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(MainActivity.this::handleResponse)
        );


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;

        });
    }
    private void handleResponse(List<Place> places){
        binding.recylerView.setLayoutManager(new LinearLayoutManager(this));
        PlaceAdapter placeAdapter = new PlaceAdapter(places);
        binding.recylerView.setAdapter(placeAdapter);
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
        if (item.getItemId() == R.id.maps){
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}