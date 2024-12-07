package com.tnqr.workmanger;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
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

        //periodic olarak refresh database classı çalışacak min 15 değeri verilebiliyor
        WorkRequest workRequest1 = new PeriodicWorkRequest.Builder(RefreshDatabase.class,15,TimeUnit.SECONDS)
                .setConstraints(constraints)
                .setInputData(data)
                .build();

        //oradan oluşturulan neyneyi sıraya eklemek.
        WorkManager.getInstance(this).enqueue(workRequest1);

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(workRequest1.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                if (workInfo.getState() == WorkInfo.State.RUNNING){
                    System.out.println("running");
                }else if (workInfo.getState() == WorkInfo.State.FAILED){
                    System.out.println("failed");
                } else if (workInfo.getState() == WorkInfo.State.BLOCKED) {
                    System.out.println("blocked");
                }
            }
        });

        WorkManager.getInstance(this).cancelAllWork(); //tüm işleri iptal eder.

        //alt satırdaki gibi işlemleri sırasıyla yapmayı sağlayan beginWith ve then
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(RefreshDatabase.class)
                .setConstraints(constraints)
                .setInputData(data)
                .build();
        OneTimeWorkRequest oneTimeWorkRequest2 = new OneTimeWorkRequest.Builder(RefreshDatabase.class)
                .setConstraints(constraints)
                .setInputData(data)
                .build();

        WorkManager.getInstance(this).beginWith(oneTimeWorkRequest)
                .then(oneTimeWorkRequest2)
                .enqueue();



    }
}