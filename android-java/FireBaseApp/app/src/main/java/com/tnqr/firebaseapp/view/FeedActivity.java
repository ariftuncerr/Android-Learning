package com.tnqr.firebaseapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.tnqr.firebaseapp.R;
import com.tnqr.firebaseapp.adapter.PostAdapter;
import com.tnqr.firebaseapp.databinding.ActivityFeedBinding;
import com.tnqr.firebaseapp.model.Post;

import java.util.ArrayList;
import java.util.Map;

public class FeedActivity extends AppCompatActivity {
    private ActivityFeedBinding binding;
    private FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    ArrayList<Post> postArrayList;
    PostAdapter postAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityFeedBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.toolbar2);
        View view = binding.getRoot();
        setContentView(view);

        //giriş yapan geçerli kullanıcı alınır.
        auth = FirebaseAuth.getInstance();

        firebaseFirestore = FirebaseFirestore.getInstance();
        postArrayList = new ArrayList<>();
        getData();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        postAdapter = new PostAdapter(postArrayList);
        binding.recyclerView.setAdapter(postAdapter);


    }
    private void getData(){

        firebaseFirestore.collection("Posts").orderBy("Date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null){
                    Toast.makeText(FeedActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
                if (value != null){
                    for (DocumentSnapshot snapshot: value.getDocuments()){

                        Map<String,Object> data= snapshot.getData();

                        String email = (String) data.get("usermail");
                        String postName = (String) data.get("postName");
                        String comment = (String) data.get("comment");
                        String dowloadUrl = (String) data.get("dowloadUrl");

                        //System.out.println(comment);
                        Post post = new Post(email,comment,dowloadUrl,postName);
                        postArrayList.add(post);


                    }
                    postAdapter.notifyDataSetChanged();
                }

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.sidebar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.loginItem){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            boolean info = true;
            intent.putExtra("comeToFeed",info);
            startActivity(intent);


        }
        if (item.getItemId() == R.id.signOutItem){
            auth.signOut();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();

        }
        if (item.getItemId() == R.id.uploadItem){
            Intent intent = new Intent(getApplicationContext(), UploadActivity.class);

            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);


    }
    public void getUser (){

    }
}