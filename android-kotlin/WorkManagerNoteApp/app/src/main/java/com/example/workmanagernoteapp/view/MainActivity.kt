package com.example.workmanagernoteapp.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.workmanagernoteapp.R
import com.example.workmanagernoteapp.databinding.ActivityMainBinding
import com.example.workmanagernoteapp.sqlite.Note
import com.example.workmanagernoteapp.sqlite.NoteDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var db : NoteDatabase
    private var compositeDisposable = CompositeDisposable()

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

        db = Room.databaseBuilder(this, NoteDatabase:: class.java, "Note").build()

    }
    fun onSaveClick(view : View){
        val title = binding.titleText.text.toString()
        val description = binding.descTxt.text.toString()
        if (title.isEmpty() || description.isEmpty()){
            Toast.makeText(this,"You should fill all blanks", Toast.LENGTH_SHORT).show()
        }
        else{
            val note : Note = Note(0,title,description)
            db.NoteDao().insert(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { Toast.makeText(this,"succesfully added", Toast.LENGTH_SHORT).show()},
                    {error -> Log.e("Database","insert error")})

        }
    }
}