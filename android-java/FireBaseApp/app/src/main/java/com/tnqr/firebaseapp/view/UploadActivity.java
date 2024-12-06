package com.tnqr.firebaseapp.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.tnqr.firebaseapp.databinding.ActivityUploadBinding;

import java.util.HashMap;
import java.util.UUID;

public class UploadActivity extends AppCompatActivity {
    private ActivityUploadBinding binding;
    ActivityResultLauncher<Intent> activityResultLauncher;
    ActivityResultLauncher<String> permissionLauncher;
    Uri imageData;
    Bitmap selectedImage;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityUploadBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        registerLauncher();
        firebaseStorage = FirebaseStorage.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        storageReference = firebaseStorage.getReference();
        //initilaze launchers


    }
    public void uploadImage(View view){
        UUID uuid = UUID.randomUUID();
        String imageResource = "images/" +uuid+ ".jpg";
        if (imageData != null){

            storageReference.child(imageResource).putFile(imageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    //download url
                    //firebaseFireStore a eleman koymak icin yapmamız gerekenler yani kaydettiğimiz göreselin
                    //kim tarafından hangi yorumla hangi tarihte yüklendiğini bu işlemlerle alacaüız

                    //kaydettiğim görselin referansını oluşturma
                    StorageReference newReference = firebaseStorage.getReference(imageResource);

                    //aldığım referanstan url adresini al ve alma işlemi başarılı ise yap
                    newReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String downloadUrl = uri.toString();
                            String comment = binding.imageCommentText.getText().toString();
                            String postName = binding.imageNameText.getText().toString();

                            FirebaseUser user = auth.getCurrentUser();
                            String email = user.getEmail();

                            //anahtar kelime String değer her şey olabilir
                            HashMap<String,Object> postData = new HashMap<>();
                            postData.put("usermail",email);
                            postData.put("downloadUrl",downloadUrl);
                            postData.put("comment",comment);
                            postData.put("postName", postName);
                            postData.put("Date", FieldValue.serverTimestamp());
                            firebaseFirestore.collection("Posts").add(postData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Intent intent = new Intent(UploadActivity.this, FeedActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(UploadActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();


                                }
                            });






                        }
                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(UploadActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
    public void selectImage(View view){
        //izin daha önceden verilmemişse izin mantığını kullanıcıya sun, daha sonra kullanıcıdan izni almak için bir snackbar koy, eğer kullanıcı redderse

        //Eger izin daha önceden verilmediyse
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)!= PackageManager.PERMISSION_GRANTED){
            //daha önceden izin reddedilmişse izni isteyerek bunun mantığının açıklandığı kısımıdrı
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_MEDIA_IMAGES)) {
                Snackbar.make(view, "Permission needed for Gallery", Snackbar.LENGTH_INDEFINITE).setAction("Give Permission", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES);


                    }
                }).show();
            }
            //eğer izin almak için sorduğunda izin reddedildiyse
            else {
                //ask permission
                permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES);
            }

        }
        else {
            Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activityResultLauncher.launch(intentToGallery);
        }
    }


    private void registerLauncher(){
        //activity launchera bir sonuç için aktivite başlat bu sonuç buraya kaydolsun
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                if (o.getResultCode() == RESULT_OK) {
                    Intent intentFromResult = o.getData();
                    if (intentFromResult != null){
                        imageData = intentFromResult.getData();
                        binding.imageView.setImageURI(imageData);
                        
                        /*firebase için bitmape çevirmeye ihtiyaç yok aşağıda bitmape çevirme kodları verildi.
                        try {
                            if (Build.VERSION.SDK_INT >=28){
                                ImageDecoder.Source source = ImageDecoder.createSource(UploadActivity.this.getContentResolver(),imageData);
                                Bitmap selectedBitmap = ImageDecoder.decodeBitmap(source);
                                binding.imageView.setImageBitmap(selectedBitmap);

                            }
                            else{
                                selectedImage = MediaStore.Images.Media.getBitmap(UploadActivity.this.getContentResolver(),imageData);
                                binding.imageView.setImageBitmap(selectedImage);

                            }

                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }*/


                    }
                }

            }
        });
        permissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean o) {
                if (o){
                    Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activityResultLauncher.launch(intentToGallery);
                }
                else {
                    Snackbar.make(binding.getRoot(), "Permission denied", Snackbar.LENGTH_SHORT).show();
                }

            }
        });

    }

}