package com.tnqr.artbook;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.Manifest;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;
import com.tnqr.artbook.databinding.ActivityArtBinding;
import com.tnqr.artbook.databinding.ActivityMainBinding;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ArtActivity extends AppCompatActivity {
    private ActivityArtBinding binding;

    // Galeriye gitmek için kullanılan ActivityResultLauncher
    ActivityResultLauncher<Intent> activityResultLauncher;

    // İzin istemek için kullanılan ActivityResultLauncher
    ActivityResultLauncher<String> permissionLauncher;

    Bitmap selectedImage;
    SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ViewBinding kullanarak XML'deki görünümleri bağlama
        binding = ActivityArtBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        // Activity Result Launcher'ları kaydetme
        registerLauncher();

    }


    public Bitmap makeSmallerImage(Bitmap image, int maximumSize){
        int width = image.getWidth();
        int height = image.getHeight();
        float bitmapRatio = (float) width / (float) height;

        if (bitmapRatio > 1){
            //landscapeImage
            width = maximumSize;
            height = (int) (width/bitmapRatio);

        }
        else {
            //portraitImage
            height = maximumSize;
            width =(int) (height/bitmapRatio);

        }
        return Bitmap.createScaledBitmap(image,width,height,true);
    }

    // Görüntü seçme işlemi (galeriden resim seçme)
    public void selectImage(View view){
        // Android sürümü TIRAMISU (33) ve üstü ise
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){

            // Galeriye erişim izni verilmiş mi kontrol et
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED){

                // Eğer izin verilmemişse ve daha önce izin istemişsek, izin isteme sebebini açıkla
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_MEDIA_IMAGES)){
                    // Kullanıcıya Snackbar ile izin gerektiğini göster
                    Snackbar.make(view,"Galeriye erişim izni gerekli", Snackbar.LENGTH_INDEFINITE).setAction("İzin ver", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // İzni isteme
                            permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES);
                        }
                    }).show();
                } else {
                    // Direkt olarak izni isteme
                    permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES);
                }
            }
            else {
                // İzin zaten verilmişse galeriye git
                Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intentToGallery);
            }
        }
        else {
            // Android 33'ten küçük sürümler için
            // Galeriye erişim izni verilmiş mi kontrol et
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                // Eğer izin verilmemişse ve daha önce izin istemişsek, izin isteme sebebini açıkla
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                    // Kullanıcıya Snackbar ile izin gerektiğini göster
                    Snackbar.make(view,"Galeriye erişim izni gerekli", Snackbar.LENGTH_INDEFINITE).setAction("İzin ver", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // İzni isteme
                            permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
                        }
                    }).show();
                } else {
                    // Direkt olarak izni isteme
                    permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
                }
            }
            else {
                // İzin zaten verilmişse galeriye git
                Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intentToGallery);
            }
        }
    }

    // İzin ve galeri aktiviteleri için launcher'ları kaydetme işlemi
    private void registerLauncher(){
        // Galeriye gitmek için result launcher'ı kaydediyoruz
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK){
                    // Eğer galeri seçimi başarılıysa
                    Intent intentFromResult = result.getData();
                    if(intentFromResult != null){
                        // Seçilen görüntü URI'sini alıyoruz
                        Uri imageData = intentFromResult.getData();
                        try {
                            // Android sürümü 28 ve üzeriyse ImageDecoder kullanarak görüntüyü al
                            if(Build.VERSION.SDK_INT >= 28) {
                                ImageDecoder.Source source = ImageDecoder.createSource(ArtActivity.this.getContentResolver(), imageData);
                                selectedImage = ImageDecoder.decodeBitmap(source);
                                binding.imageView.setImageBitmap(selectedImage);
                            }
                            // Daha düşük sürümler için MediaStore kullanarak görüntüyü al
                            else{
                                selectedImage = MediaStore.Images.Media.getBitmap(ArtActivity.this.getContentResolver(), imageData);
                                binding.imageView.setImageBitmap(selectedImage);
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        // İzin istemek için result launcher'ı kaydediyoruz
        permissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if(result){
                    // Eğer izin verildiyse galeriye git
                    Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activityResultLauncher.launch(intentToGallery);
                }
                else{
                    // Eğer izin verilmezse kullanıcıya uyarı göster
                    Toast.makeText(ArtActivity.this, "İzin Gerekli!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Kaydetme işlemi (henüz boş)
    public void save(View view){
        String artName = binding.artNameText.getText().toString();
        String artYear = binding.artAgeText.getText().toString();
        String artistName = binding.artistNameText.getText().toString();

        Bitmap smallImage = makeSmallerImage(selectedImage,300);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        smallImage.compress(Bitmap.CompressFormat.PNG,50,outputStream);
        byte [] byteArray =outputStream.toByteArray();
        try {
            database = this.openOrCreateDatabase("Arts",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS arts(id INTEGER PRIMARY KEY ,artName VARCHAR,artistName VARCHAR,year  VARCHAR,image BLOB)");
            String sqlString = "INSERT INTO arts (artName,artistName,year,image) VALUES (?,?,?,?)";
            SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
            sqLiteStatement.bindString(1,artName);
            sqLiteStatement.bindString(2,artistName);;
            sqLiteStatement.bindString(3,artYear);
            sqLiteStatement.bindBlob(4,byteArray);




        }
        catch (Exception e){
            e.printStackTrace();
        }
        //finish();
        Intent intent = new Intent(ArtActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);



    }
}
