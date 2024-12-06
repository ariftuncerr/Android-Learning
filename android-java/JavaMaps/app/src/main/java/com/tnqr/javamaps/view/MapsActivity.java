package com.tnqr.javamaps.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.tnqr.javamaps.R;
import com.tnqr.javamaps.databinding.ActivityMapsBinding;
import com.tnqr.javamaps.model.Place;
import com.tnqr.javamaps.roomdb.PlaceDao;
import com.tnqr.javamaps.roomdb.PlaceDatabase;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

import io.reactivex.rxjava3.schedulers.Schedulers;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    ActivityResultLauncher<String> permissionLauncher;
    LocationManager locationManager;
    LocationListener locationListener;
    SharedPreferences sharedPreferences;
    boolean info;
    PlaceDatabase db;
    PlaceDao placeDao;
    double selectedLongitude;
    double selectedLatitude;

    // database işlemlerini arka planda yap at için kullanılır
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);


        sharedPreferences = this.getSharedPreferences(" com.tnqr.javamaps",MODE_PRIVATE);
        info = false;

        db = Room.databaseBuilder(getApplicationContext(),PlaceDatabase.class,"Place").fallbackToDestructiveMigration().build();
        placeDao = db.placeDao();
        selectedLongitude =0.0;
        selectedLatitude =0.0;
        binding.saveButton.setEnabled(false);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        registerLauncher();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Toast.makeText(this, "Harita Yüklendi", Toast.LENGTH_SHORT).show();

        mMap.setOnMapLongClickListener(this);
        //casting yaparak bu nesnenin LocationManager sınıfından olduğunu anlat
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
               // System.out.println("Location:"+location.toString());
                info = sharedPreferences.getBoolean("info",false);
                if (!info){
                    LatLng userLocation = new LatLng(location.getLatitude(),location.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,15));
                    sharedPreferences.edit().putBoolean("info",true).apply();
                }

            }

        };


        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                Snackbar.make(binding.getRoot(),"Haritalar için izin Gerekli",Snackbar.LENGTH_INDEFINITE).setAction("İzin ver", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //request permission
                        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);

                    }
                }).show();


            }
            else {
                //request permission
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);


            }

        }
        else{
            //izin daha önceden alımıştı
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);

            //son konumu gps provider dan al lastLocation a kaydet
            Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


            if(lastLocation != null){
                //ifasde null değilse enlem ve boylamı Latlng türünden bir objeye koy kamerayı oraya zoomla
                LatLng lastUserLocation = new LatLng(lastLocation.getLatitude(),lastLocation.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation,15));

            }
            //konum göstericisindeki mavi işaret
            mMap.setMyLocationEnabled(true);
        }

        // Add a marker in Sydney and move the camera
        //39.925014116194205, 32.83714296902486
        /*LatLng anitkabir = new LatLng(39.925014116194205, 32.83714296902486);
        mMap.addMarker(new MarkerOptions().position(anitkabir).title("Marke"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(anitkabir,20f));
        */
    }

    private void registerLauncher(){
        permissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean o) {
                if(o){
                    if (ContextCompat.checkSelfPermission(MapsActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);



                }
                else {
                    //izin verilmedi
                    Toast.makeText(MapsActivity.this, "Permission needed!", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        Toast.makeText(this, "Long click detected", Toast.LENGTH_SHORT).show();

        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(latLng));
        selectedLatitude = latLng.latitude;
        selectedLongitude = latLng.longitude;

        binding.saveButton.setEnabled(true);

    }


    //sidebar menü
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //menü bağlayıcı


        return super.onCreateOptionsMenu(menu);

    }
    MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.sidebar_menu,menu);
    //sidebar menüye tıklandığında
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mainItem){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
    public void save(){

        Place place = new Place(binding.placeText.getText().toString(),selectedLatitude,selectedLongitude);
        //therading -> Main (uı), default ( cpu intensive), Io thread (network,database)


        //placeDao.insert(place).subscribeOn(Schedulers.io()).subscribe();

        //disposable ->kullan at
        compositeDisposable.add(placeDao.insert(place)
                        .subscribeOn(Schedulers.io()) //burda yap
                        .observeOn(AndroidSchedulers.mainThread()) //main threadde gözlemle
                        .subscribe(MapsActivity.this::handleResponse)
                );




    }



    public void delete(){

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
    private void handleResponse(){
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}

