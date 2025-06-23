package com.example.workmanagernoteapp.workManager

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.work.WorkerParameters
import com.example.workmanagernoteapp.sqlite.NoteDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NoteUploadWorker(val context: Context,workerParams: WorkerParameters) :androidx.work.Worker(context, workerParams) {
    private val compositeDisposable  = CompositeDisposable()
    private lateinit var db : NoteDatabase

    override fun doWork(): Result {

        //yapılacak işi ele al
       val noteIdList = inputData.getIntArray("unsaved_ids")
        uploadOnFirebase(noteIdList?.toList())

        //sonuç durumları kontrol edilip failure - succes döndürülebilir
        return Result.success()

    }
    private fun uploadOnFirebase(noteIdList: List<Int>?) {
        //yapılan iş -> veritabanını aç ve gelen listedeki id lerin savedOn database değerini güncelle
        //verileri firebase e yükleme işlemi burada gerçekleşebilir.
        db = Room.databaseBuilder(context, NoteDatabase :: class.java,"Note").build()
        noteIdList?.let {
            for (id in noteIdList){
                println("id ${id} ")
                compositeDisposable.add(
                    db.NoteDao().updateSavedOnFirebase(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({println("succesfully updated")},
                            {error -> Log.e("Database","database updateSavedOnFirebaseError") })
                )
            }
        }
    }
}