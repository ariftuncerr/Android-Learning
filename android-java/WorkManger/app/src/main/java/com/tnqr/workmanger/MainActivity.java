package com.tnqr.workmanger;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Data data  = new Data.Builder().putInt("myNum",3).build();

        //constarints objesinde tanımlanan durumlar tutulur
        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(false)
                .build();

        //çalışacak işlem constraints de olan durumlara göre çalışır Refresh database classı bu sayede uygulama kapandığında da çalışabilir değerler tutulur.

        WorkRequest workRequest = new OneTimeWorkRequest.Builder(RefreshDatabase.class)
                .setConstraints(constraints)
                .setInputData(data)
                .setInitialDelay(5, TimeUnit.SECONDS)
                .build();
        WorkManager.getInstance(this).enqueue(workRequest);

        WorkRequest workRequest1 = new PeriodicWorkRequest.Builder(RefreshDatabase.class,15,TimeUnit.MINUTES);
                .setConstraints(constraints)
                .setInputData(data)
                .setInputData(data);
    }
}