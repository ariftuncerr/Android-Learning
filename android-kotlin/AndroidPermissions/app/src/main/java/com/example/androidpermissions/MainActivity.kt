package com.example.androidpermissions

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidpermissions.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    //Activity Result Launcher farklı bir yerden bir aktivite başlatmak için kullanılır
    // -> Galleriden veri almak için türü Intent'tir
    private lateinit var galleryLauncher : ActivityResultLauncher<Intent>

    // -> İzin istemek için kullanılan tür String'tir (izinlerin dönüş tipi String)
    private lateinit var permissionLauncher : ActivityResultLauncher<String>

    //seçilen görsel'i bitmap e çevirmek için kullanılır.
    private var selectedBitmap : Bitmap? = null

    //database initialize
    private val db = SQLiteHelper(this,"Images",4)

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
        //resultLauncherRegister
        registerLauncher()



    }

    fun getRecords(view : View){
        db.readImages().let { it ->
            println(it)
            binding.imageView.setImageBitmap(it?.get(0)!!)
            val imageAdapter = ImageAdapter(this,it!!)
            binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
            binding.recyclerView.adapter = imageAdapter
        }


    }
    fun onSaveDatabaseClick(view : View){
        selectedBitmap?.let {
            val isInserted = db.insertImages(selectedBitmap!!) // null kontrolü yapıldı
            if (isInserted)
                Snackbar.make(view,"Saved on Database", Snackbar.LENGTH_SHORT).show()
            else
                Snackbar.make(view,"Did not Save on Database", Snackbar.LENGTH_SHORT).show()

        }?: Toast.makeText(this,"For save You should select an image", Toast.LENGTH_SHORT).show()

    }
    fun registerLauncher(){
        //gallery launcher' ı bir aktivite sonucu için başlat (register et)
        galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if (result.resultCode == RESULT_OK) {// eğer galeriye gidildiyse (user cancel da yapabilir
                val intentFromData = result.data // eğer galeriden bir şey seçildiyse gelen veri (Intent)-> true

                intentFromData?.let {
                    val imageData = intentFromData.data // gelen sonucun içeriğini(resmi) imageData yap (resmin URI i döner)
                    //binding.imageView.setImageURI(imageData) // veriyi göstermek için yeterli
                    //imageData -> kaynak Uri -> SQLite için Bitmap'e çevrilmesi gerekir
                    if (imageData != null) {
                        //bitmap'e çevirme
                        try {
                            if (Build.VERSION.SDK_INT >= 28) { //ImageDecoder.createSource -> android sdk 28 ve üstü için geçerli
                                val source = ImageDecoder.createSource(this@MainActivity.contentResolver,imageData)
                                selectedBitmap = ImageDecoder.decodeBitmap(source)
                                binding.imageView.setImageBitmap(selectedBitmap)
                            }
                            else { // Android Sdk < 28  -> Android Sdk 28 altı için geçerli yöntem MediaStore
                                selectedBitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageData)
                                binding.imageView.setImageBitmap(selectedBitmap)
                            }

                        } catch (e: Exception){
                            e.printStackTrace()
                        }
                    }
                }
            }
        }

        //permission için başlatılacak launcher izin isteği başlatır
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted){
                //permission granted
                //nereye gidip ne alacağını belirtmek için intent başlat, nereden -> medistore..  , ne yapacağım -> pıck (al)
                val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                galleryLauncher.launch(intentToGallery) // veri almak için gallery launcher' ı başlat
            }
            else{
                //permission denied
                Snackbar.make(binding.root,"Permission needed!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
    fun onSelectClick(view : View){


        // Android 13 ve sonrası için doğru izin
        val permissionString = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            android.Manifest.permission.READ_MEDIA_IMAGES
        } else {
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        }
        //eğer izin daha önceden verilmediyse
        if (ContextCompat.checkSelfPermission(this,permissionString) != PackageManager.PERMISSION_GRANTED){
            //kullanıcı izni daha önceden reddetti ama "bir daha sorma" demediyse ("izne neden ihtiyaç var açılanır -> rationale = gerekçe")
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,permissionString)){
                //rationale
                Snackbar.make(view,"Permission needed for gallery", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Give Permission"){ listener ->
                        //request permission
                        //iznin ne olduğunu permission launcher a verdik -> izinin ne olduğu manifestte tanımlı
                        permissionLauncher.launch(permissionString)
                    }.show()
            } else{
                //request permission
                //iznin ne olduğunu permission launcher a verdik -> izinin ne olduğu manifestte tanımlı
                permissionLauncher.launch(permissionString)
            }

        }
        // izin önceden verildiyse
        else{
            //nereye gidip ne alacağını belirtmek için intent başlat, nereden -> medistore..  , ne yapacağım -> pıck (al)
            val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            galleryLauncher.launch(intentToGallery) // veri almak için gallery launcher' ı başlat

        }

    }
}