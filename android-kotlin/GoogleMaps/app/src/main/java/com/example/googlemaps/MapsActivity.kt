package com.example.googlemaps

import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,GoogleMap.OnMapLongClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private lateinit var locationManager : LocationManager
    private lateinit var locationListener: LocationListener

    private lateinit var permissionLauncher : ActivityResultLauncher<String>

    private lateinit var sharedPreferences: SharedPreferences
    private var trackBoolean : Boolean? = null

    private var selectedLongitude : Double? = null
    private var selectedLatitude : Double? = null

    //intent from favorites
    private var lat_fromFavorites = 0.0
    private var lng_fromFavorites = 0.0
    private var cameFromFavorites = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //fragmentte çalıştığı için fragment kurulumu
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        registerPermission()
        sharedPreferences = this.getSharedPreferences("com.example.googlemaps",MODE_PRIVATE)
        trackBoolean = false

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setOnMapLongClickListener(this)

        locationManager = this.getSystemService(LOCATION_SERVICE) as LocationManager

        locationListener = object : LocationListener{
            override fun onLocationChanged(location: Location) {

                /* sürekli çağrıldığı için değişimlerde kamerayı sürekli buraya zoomluyor
                val userLocation = LatLng(location.latitude, location.longitude)
                mMap.clear() // Önceki marker'ları temizle
                mMap.addMarker(MarkerOptions().position(userLocation).title("Your Location"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15f))
                */
                //çözüm -> konum ilk açıldığında bir seferliğe mahsus o konuma gitmek için kayıt tutarız
                lat_fromFavorites = intent.getDoubleExtra("lat", 0.0)
                lng_fromFavorites = intent.getDoubleExtra("lng", 0.0)
                cameFromFavorites = intent.getBooleanExtra("cameFromFavorites", false)

                trackBoolean = sharedPreferences.getBoolean("trackBoolean",false)
                if (trackBoolean == false) {
                    sharedPreferences.edit().putBoolean("trackBoolean", true).apply()

                    mMap.clear()
                    //favorites den gelindiyse
                    if (cameFromFavorites){
                        val placeLocation = LatLng(lat_fromFavorites,lng_fromFavorites)
                        mMap.addMarker(MarkerOptions().position(placeLocation).title("Place Location"))
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(placeLocation, 15f))
                    }

                    //main den gelindiyse
                    else{
                        val userLocation = LatLng(location.latitude, location.longitude)
                        mMap.addMarker(MarkerOptions().position(userLocation).title("Your Location"))
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15f))
                    }

               }

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
                val lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                if (lastLocation != null){
                    val lastUserLocation = LatLng(lastLocation.latitude,lastLocation.longitude)
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation,15f))
                }
                mMap.isMyLocationEnabled = true

            }


        }

    }
    fun registerPermission(){
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if(isGranted){
                if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,locationListener)
                    val lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    if (lastLocation != null){
                        val lastUserLocation = LatLng(lastLocation.latitude,lastLocation.longitude)
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation,15f))
                    }
                    mMap.isMyLocationEnabled = true

                }

            }
            else{
                Snackbar.make(binding.root,"Permission Needed", Snackbar.LENGTH_SHORT).show()

            }

        }
    }

    override fun onMapLongClick(p0: LatLng) {
        mMap.clear()
        mMap.addMarker(MarkerOptions().position(p0))
        selectedLatitude = p0.latitude
        selectedLongitude = p0.longitude
    }

    fun onSelectPlaceClick(view : View){
        if (selectedLongitude != null || selectedLatitude != null){
            val intent = Intent(this, MainActivity :: class.java).apply {
                putExtra("lng",selectedLongitude)
                putExtra("lat",selectedLatitude)
            }
            startActivity(intent)
        }
        else
            Snackbar.make(binding.root,"You should Select one Place", Snackbar.LENGTH_SHORT).show()


    }
}