package com.example.googlemaps

import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.googlemaps.databinding.ActivityMapsBinding
import com.google.android.material.snackbar.Snackbar
import java.security.Permissions
import java.security.acl.Permission
import java.util.jar.Manifest

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private lateinit var locationManager : LocationManager
    private lateinit var locationListener: LocationListener

    private lateinit var permissionLauncher : ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //fragmentte çalıştığı için fragment kurulumu
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        registerPermission()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        locationManager = this.getSystemService(LOCATION_SERVICE) as LocationManager

        locationListener = object : LocationListener{
            override fun onLocationChanged(location: Location) {
                val userLocation = LatLng(location.latitude, location.longitude)
                mMap.clear() // Önceki marker'ları temizle
                mMap.addMarker(MarkerOptions().position(userLocation).title("Your Location"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15f))
            }

            override fun onProviderEnabled(provider: String) {
                super.onProviderEnabled(provider)
            }
        }

        val permission = android.Manifest.permission.ACCESS_FINE_LOCATION

        if (ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED){
            //permission denied
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,permission)){
                Snackbar.make(binding.root,"Permission Needed", Snackbar.LENGTH_INDEFINITE).setAction("Give Permission"){
                    permissionLauncher.launch(permission)
                }.show()
            }
            else{
                permissionLauncher.launch(permission)
            }
        }
        else{
            //permission granted
            if (ActivityCompat.checkSelfPermission(this,permission) == PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,locationListener)
            }

        }
        //izin gerekli




        //başlangıç enlem boylam
        /*val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney")) //açıkalamalı marker ekleyici
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney)) //kamerayı bu yöne götür*/
    }
    fun registerPermission(){
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if(isGranted){
                if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,locationListener)
            }
            else{
                Snackbar.make(binding.root,"Permission Needed", Snackbar.LENGTH_SHORT).show()

            }

        }
    }
}