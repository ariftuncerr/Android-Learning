package com.example.googlemaps

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

object BitmapByteArrayConverter {
    fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val resized = Bitmap.createScaledBitmap(bitmap, 512, 512, true) // boyut küçültme
        val stream = ByteArrayOutputStream()
        resized.compress(Bitmap.CompressFormat.JPEG, 70, stream) // kalite düşürme
        return stream.toByteArray()
    }
    fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

}