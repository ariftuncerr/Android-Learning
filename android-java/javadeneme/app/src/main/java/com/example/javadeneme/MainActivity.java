package com.example.javadeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    public static void main(String[] args){
        StudentInfo s1 = new StudentInfo();
        s1.name ="Arif";
        s1.midScore =60;
        s1.finScore =40;
        s1.CalculateAverage(s1.midScore,s1.midScore);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("Etiket","Tebrikler Uygulama çalıştı");


    }
}