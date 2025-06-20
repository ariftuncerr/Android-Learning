package com.example.androidpermissions

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

class SQLiteHelper(context: Context, dbName : String, version: Int): SQLiteOpenHelper (context,dbName,null,version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS images_table (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "image BLOB)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS images_table")
        onCreate(db)
    }

    fun insertImages(bitmapImage: Bitmap) : Boolean {
        try {
            val db = writableDatabase
            val smallBitmap = resizeBitmap(bitmapImage,500)
            val byteArray = bitmapToByteArray(smallBitmap)

            val values = ContentValues().apply {
                put("image",byteArray)
            }
            db.insert("images_table",null,values)
            db.close()
            return true
        }
        catch (e : Exception){
            println(e.printStackTrace())
            return false
        }

    }


    fun readImages() : List<Bitmap>? {
        val db = readableDatabase
        val imageList = mutableListOf<Bitmap>()
        try {

            val query = "SELECT image From images_table"

            val cursor = db.rawQuery(query,null)
            while (cursor.moveToNext()){
                val image : ByteArray = cursor.getBlob(0)
                val bitMapImage = byteArrayToBitmap(image)
                imageList.add(bitMapImage)
            }

        }
        catch (e : Exception){
            println(e.localizedMessage)

        }
        db.close()
        return imageList

    }

    fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
    fun resizeBitmap(bitmap: Bitmap, maxSize: Int): Bitmap {
        val width = bitmap.width
        val height = bitmap.height

        val scale = maxSize.toFloat() / maxOf(width, height)
        val newWidth = (width * scale).toInt()
        val newHeight = (height * scale).toInt()

        return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true)
    }


}