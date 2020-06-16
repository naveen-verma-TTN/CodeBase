@file:Suppress("DEPRECATION")

package com.ttn.camera_ui_testing

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.min

const val REQUEST_IMAGE_CAPTURE = 1234
const val KEY_IMAGE_DATA = "data"

class MainActivity : AppCompatActivity(){

    private val TAG: String = "AppDebug"
    private lateinit var currentPhotoPath: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_launch_camera.setOnClickListener {
            dispatchCameraIntent()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            Log.d(TAG, "RESULT_OK")
            when(requestCode){

                REQUEST_IMAGE_CAPTURE -> {
                    Log.d(TAG, "REQUEST_IMAGE_CAPTURE detected.")
                    data?.extras.let{ extras ->
                        if (extras == null || !extras.containsKey(KEY_IMAGE_DATA)) {
                            return
                        }
                        val imageBitmap = extras[KEY_IMAGE_DATA] as Bitmap?
                        image.setImageBitmap(imageBitmap)
                    }
                }
            }
        }
    }

    private fun dispatchCameraIntent() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

/*    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            Log.d(TAG, "RESULT_OK")
            when(requestCode){

                REQUEST_IMAGE_CAPTURE -> {
                    Log.d(TAG, "REQUEST_IMAGE_CAPTURE detected.")
                    setPic()
                }
            }
        }
    }

    *//**
     * fun to capture full size image
     *//*
    private fun dispatchCameraIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File
                    Log.e(TAG, "dispatchTakePictureIntent: ",ex )
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                            this,
                            "com.ttn.camera_ui_testing.fileprovider",
                            it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return File.createTempFile(
                "JPEG_${timeStamp}_", *//* prefix *//*
                ".jpg", *//* suffix *//*
                storageDir *//* directory *//*
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    private fun setPic() {
        // Get the dimensions of the View
        val targetW: Int = image.width
        val targetH: Int = image.height

        val bmOptions = BitmapFactory.Options().apply {
            // Get the dimensions of the bitmap
            inJustDecodeBounds = true

            val photoW: Int = outWidth
            val photoH: Int = outHeight

            // Determine how much to scale down the image
            val scaleFactor: Int = min(photoW / targetW, photoH / targetH)

            // Decode the image file into a Bitmap sized to fill the View
            inJustDecodeBounds = false
            inSampleSize = scaleFactor
            inPurgeable = true
        }
        BitmapFactory.decodeFile(currentPhotoPath, bmOptions)?.also { bitmap ->
            image.setImageBitmap(bitmap)
        }
    }*/

}
