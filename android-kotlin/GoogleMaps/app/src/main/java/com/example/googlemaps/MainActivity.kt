package com.example.googlemaps

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.googlemaps.database.Place
import com.example.googlemaps.database.PlaceDao
import com.example.googlemaps.database.PlaceDatabase
import com.example.googlemaps.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.io.ByteArrayOutputStream
import java.sql.Blob

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var galleryLauncher : ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher : ActivityResultLauncher<String>
    private var selectedBitmap : Bitmap? = null

    private lateinit var db : PlaceDatabase
    private lateinit var placeDao : PlaceDao
    private val compositeDisposable = CompositeDisposable() // kullan at

    private var lat : Double? = null
    private var lng : Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view : View = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setSupportActionBar(binding.materialToolbar)

        //register launcher
        registerLauncher()



        db = Room.databaseBuilder(applicationContext, PlaceDatabase :: class.java,"PlaceDB")
            .build()
        placeDao = db.placeDao()

        lat = intent.getDoubleExtra("lat",0.0)
        lng = intent.getDoubleExtra("lng",0.0)
        binding.selectedLocationTxt.text = "Selected Location: $lat , $lng"
    }
    fun registerLauncher(){
        galleryLauncher = this.registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if (result.resultCode == RESULT_OK){
                val intentFromData = result.data //galeriden bir şey seçildi mi

                intentFromData?.let {
                    val imageData = intentFromData.data

                    if (imageData != null){
                        try {
                            if (Build.VERSION.SDK_INT >= 28){
                                val source = ImageDecoder.createSource(this@MainActivity.contentResolver,imageData)
                                selectedBitmap = ImageDecoder.decodeBitmap(source)
                                binding.imageView.setImageBitmap(selectedBitmap)
                            }
                            else{
                                selectedBitmap = MediaStore.Images.Media.getBitmap(contentResolver,imageData)
                                binding.imageView.setImageBitmap(selectedBitmap)
                            }

                        }
                        catch (e : Exception){
                            println(e.localizedMessage)
                        }
                    }
                }


            }
        }

        permissionLauncher = this.registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted->
            if (isGranted){
                val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                galleryLauncher.launch(intentToGallery)
            }
            else{
                Snackbar.make(binding.root,"For selecting image need permission", Snackbar.LENGTH_SHORT).show()
            }

        }
    }

    fun onSaveClick(view : View){
        val placeName = binding.placeEditTxt.text.toString()
        val placeComment = binding.comentEditTxt.text.toString()
        var streamImage : ByteArray? = null
        selectedBitmap?.let {
            streamImage = BitmapByteArrayConverter.bitmapToByteArray(it)
        }

        if(lat != 0.0 && lng != 0.0 && streamImage != null && !placeName.isEmpty() && !placeComment.isEmpty() ){
            val place = Place(0,placeName,placeComment,streamImage,lng!!,lat!!)
            compositeDisposable.add(
                placeDao.insert(place)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()) //android ui thread
                    .subscribe(
                        { this :: handleResponse },
                        { error-> println(error.printStackTrace())} ) // error gerekli
            )
        }
        else{
            Toast.makeText(this,"You should fill all infos", Toast.LENGTH_SHORT).show()
        }


    }
    fun handleResponse(){
        val intent = Intent(this, MainActivity :: class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
    fun onSelectImageClick(view : View){
        val permission = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            Manifest.permission.READ_MEDIA_IMAGES
        }
        else{
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

        if (ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED){
            //izin verilmedi "bir daha sorma denmedi" -> gerekçe göster
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,permission)){
                Snackbar.make(view,"For selecting image you should give permission", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Give Permission") {
                        //permission launcher
                        permissionLauncher.launch(permission)
                    }.show()
            }
            //gerekçe göstermeye gerek yok izin iste
            else{
                permissionLauncher.launch(permission)
            }

        }
        else{
            //permission already granted
            val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            galleryLauncher.launch(intentToGallery)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val page = when(item.itemId){
            R.id.action_addPlace -> MapsActivity :: class.java
            R.id.action_showFavorites -> FavoritesActivity :: class.java
            else -> null
        }
        page?.let {
            val intent = Intent(this,page)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

}