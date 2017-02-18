package com.example.game.pictureform

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.content.ContentResolver
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import java.io.InputStream
import java.net.URL
import android.Manifest.permission
import android.Manifest.permission.WRITE_CALENDAR
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat




class MainActivity : AppCompatActivity() {
    var sharedPref: SharedPreferences? = null

    var imageUriString = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPref = this.getPreferences(Context.MODE_PRIVATE)

        // to get default image
        val defaultPictureDrawable: BitmapDrawable? = image.drawable as BitmapDrawable?
        val defaultPictureBitmap: Bitmap = defaultPictureDrawable!!.bitmap

        // recall data
        getData()

        // for add picture
        image.setOnClickListener {
            insertPictureFormGallery(image)
        }

        // to reset the form
        reset.setOnClickListener {
            image.setImageBitmap(defaultPictureBitmap)
            people_check.isChecked = true
            caption.setText("")

            sharedPref!!.edit().clear().apply()
        }
    }

    override fun onDestroy() {
        saveData()
        super.onDestroy()
    }

    private fun saveData() {
        val editor: SharedPreferences.Editor = sharedPref!!.edit()
        editor.putString("image", imageUriString)
        editor.putString("genre", getGenre())
        editor.putString("caption", caption.text.toString())
        editor.apply()
    }

    private fun getData() {
        val savedImage = sharedPref!!.getString("image", imageUriString)
        println(savedImage)
        val savedGenre = sharedPref!!.getString("genre", getGenre())
        val savedCaption = sharedPref!!.getString("caption", caption.text.toString())

        image.setImageURI(Uri.parse(savedImage))
        imageUriString = savedImage
        when (savedGenre) {
            people_check.text -> people_check.isChecked = true
            animal_check.text -> animal_check.isChecked = true
            object_check.text -> object_check.isChecked = true
            place_check.text -> place_check.isChecked = true
            else -> {
                other_check.isChecked = true
                other_text.setText(savedGenre)
            }
        }
        caption.setText(savedCaption)
    }

    private fun getGenre(): String {
        if (genre.checkedRadioButtonId == other_check.id) {
            return other_text.text.toString()
        } else {
            val checkedRadioButton = findViewById(genre.checkedRadioButtonId) as RadioButton
            return checkedRadioButton.text.toString()
        }
    }

    fun insertPictureFormGallery(view: View) {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.putExtra("picture_id", view.id)
        startActivityForResult(galleryIntent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri: Uri = data.data
            val view_id: Int = intent.getIntExtra("picture_id", 0)
            if (image.id == view_id) {
                image.setImageURI(selectedImageUri)
            }

            imageUriString = selectedImageUri.toString()
        }
    }
}
