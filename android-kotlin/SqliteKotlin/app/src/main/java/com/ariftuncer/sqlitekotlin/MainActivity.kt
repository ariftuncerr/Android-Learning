package com.ariftuncer.sqlitekotlin

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        try {
            val myDatabase = this.openOrCreateDatabase("Musicians", MODE_PRIVATE,null)
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians (name VARCHAR, age INT)")
            myDatabase.execSQL("INSERT INTO musicians (name,age) VALUES ('Arif',22)")
            myDatabase.execSQL("INSERT INTO musicians (name,age) VALUES ('Yusuf',22)")


            val cursor = myDatabase.rawQuery("SELECT * from musicians",null)

            val nameIx = cursor.getColumnIndex("name")
            val ageIx = cursor.getColumnIndex("age")

            while(cursor.moveToNext()){
                println("Name:"+cursor.getString(nameIx))
                println("Age:"+cursor.getInt(ageIx))

            }
            cursor.close()


        }
        catch (e: Exception){
            println(e.printStackTrace())
        }

    }
}