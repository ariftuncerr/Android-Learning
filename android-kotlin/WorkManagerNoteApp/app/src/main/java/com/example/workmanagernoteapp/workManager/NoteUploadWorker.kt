package com.example.workmanagernoteapp.workManager

import android.content.Context
import androidx.work.WorkerParameters

class NoteUploadWorker(context: Context,
                       workerParams: WorkerParameters
) :androidx.work.Worker(context, workerParams) {
    override fun doWork(): Result {
        TODO("Not yet implemented")
    }

}