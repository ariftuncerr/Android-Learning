package com.tnqr.workmanger;

import android.content.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class RefreshDatabase extends Worker {
    Context mycontext;

    //worker manager yapısı uygulama kapansa bile arka tarafta döndürmek istediğimiz işler için kullanılır.
    public RefreshDatabase(@NonNull Context context, @NonNull WorkerParameters workerParams) {

        super(context, workerParams);
        this.mycontext =context;

    }

    @NonNull
    @Override
    public Result doWork() {
        //girilen bir data değerini alır ve arka tarafta bu işlemi yapmaya olanak sağlar
        Data data = getInputData();
        int num = data.getInt("myNum",0);
        refreshValue(num);

        return Result.success(); // dışarıdan bir veri alma durumunda başarılı olup olmadığı kontrolü yapılır.
    }
    private void refreshValue(int num){
        SharedPreferences sharedPreferences =mycontext.getSharedPreferences("import android.content.SharedPreferences",Context.MODE_PRIVATE);
        int myNumber = sharedPreferences.getInt("number",0);
        myNumber = myNumber + num;
        System.out.println(myNumber);
        sharedPreferences.edit().putInt("number",myNumber).apply();
    }
}
