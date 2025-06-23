package com.example.workmanagernoteapp.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.Worker
import androidx.work.impl.Migration_1_2
import androidx.work.workDataOf
import com.example.workmanagernoteapp.R
import com.example.workmanagernoteapp.databinding.ActivityMainBinding
import com.example.workmanagernoteapp.sqlite.Note
import com.example.workmanagernoteapp.sqlite.NoteDatabase
import com.example.workmanagernoteapp.workManager.NoteUploadWorker
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlin.collections.mutableListOf

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
        val migration_1_2 = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Note ADD COLUMN note_savedOnFirebase INTEGER NOT NULL DEFAULT 0")
            }
        }

        db = Room.databaseBuilder(this, NoteDatabase:: class.java, "Note")
            .addMigrations(migration_1_2)
            .build()
        uploadOnFirebase()

    }
    fun onSaveClick(view : View){
        val title = binding.titleText.text.toString()
        val description = binding.descTxt.text.toString()
        if (title.isEmpty() || description.isEmpty()){
            Toast.makeText(this,"You should fill all blanks", Toast.LENGTH_SHORT).show()
        }
        else{
            val note : Note = Note(0,title,description,false)
            db.NoteDao().insert(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { Toast.makeText(this,"succesfully added", Toast.LENGTH_SHORT).show()},
                    {error -> Log.e("Database","insert error")})

        }

    }
    private fun uploadOnFirebase(){
        val unSavedNotIds = mutableListOf<Int>()
        //online veritabanına kaydedilmemiş verileri bulup onları veritabanına kaydeder.
        compositeDisposable.add(
            db.NoteDao().getAllUnsavedNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({unSavedIds ->
                    for (id in unSavedIds){
                        unSavedNotIds.add(id)
                        println("unsaved id: $id")

                        //Work Manager
                        //-> veritabanına kaydolmayan verileri bul
                        val data = Data.Builder()
                            .putIntArray("unsaved_ids",unSavedNotIds.toIntArray())
                            .build()

                        //telefon internete bağlanmasını gözlemle
                        val constraints = Constraints.Builder()
                            .setRequiredNetworkType(NetworkType.CONNECTED)
                            .build()

                        //yapılma işi oluştur (tek seferlik) -> her internet bağlantısında kontrol edip yapar
                        val workRequest : WorkRequest = OneTimeWorkRequestBuilder<NoteUploadWorker>()
                            .setConstraints(constraints)
                            .setInputData(data)
                            .addTag("ids saving on firebase")
                            .build()
                        //work manager örneklerini sıraya al başlat
                        WorkManager.getInstance(applicationContext).enqueue(workRequest)
                    }
                },{error -> Log.e("Database","failure on geting unsavedNotes")})
        )


    }
}