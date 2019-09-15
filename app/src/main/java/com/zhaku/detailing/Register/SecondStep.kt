package com.zhaku.detailing.Register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.zhaku.detailing.R
import com.hootsuite.nachos.NachoTextView
import com.zhaku.detailing.data.EdFieldForChoice
import com.zhaku.detailing.data.backendApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.core.app.ActivityCompat.startActivityForResult
import android.content.Intent
import android.R.attr.data
import android.app.Activity
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide

import kotlinx.android.synthetic.main.fragment_gallery.*


class SecondStep : Fragment() {
    private lateinit var cityChoice : Spinner
    private lateinit var reg_edfields :NachoTextView
    private lateinit var reg_select_image : Button
    private lateinit var reg_profile_image : ImageView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.registration_second_step, container,false)
        with(rootView) {
            cityChoice = findViewById(R.id.reg_city_choice)
            reg_select_image = findViewById(R.id.reg_select_image)
            reg_edfields = findViewById(R.id.reg_edfieds)
            reg_profile_image = findViewById(R.id.reg_profile_image)
        }
        reg_select_image.setOnClickListener{
            pickFromGallery()
        }
        setupNachos()
        return rootView
    }
    fun setupNachos() {
        val apiService = backendApiService.createWithRx()
        val task = apiService.getEducationFields()
        task.enqueue(object  : Callback<List<EdFieldForChoice>> {
            override fun onResponse(
                call: Call<List<EdFieldForChoice>>,
                response: Response<List<EdFieldForChoice>>
            ) {
                val it = response.body()
                val suggestions = mutableListOf<String>()
                if (it != null)
                    for (v in it ) {
                        suggestions.add(v.value)
                    }
                val adapter = ArrayAdapter(reg_edfields.context, android.R.layout.simple_dropdown_item_1line, suggestions)
                reg_edfields.setAdapter(adapter)
                reg_edfields.setOnClickListener({
                    reg_edfields.showDropDown()
                })

            }

            override fun onFailure(call: Call<List<EdFieldForChoice>>, t: Throwable) {
               Log.d("retrofit","fail")
            }
        })


    }
    private fun pickFromGallery() {
        //Create an Intent with action as ACTION_PICK
        val intent = Intent(Intent.ACTION_PICK)
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.type = "image/*"
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        val mimeTypes = arrayOf("image/jpeg", "image/png", "image/jpg")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        // Launching the Intent
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }


     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         if (resultCode != Activity.RESULT_OK)
             return
        if (requestCode == GALLERY_REQUEST_CODE) {
            if (data == null)
                return
            val selectedImage = data.data
            registrationProfileImageURI = selectedImage!!
            Glide.with(reg_profile_image).
                load(registrationProfileImageURI)
                .centerCrop()
                .into(reg_profile_image)

        }
        super.onActivityResult(requestCode, resultCode, data)
    }
    companion object{
        val GALLERY_REQUEST_CODE = 909;
         var registrationProfileImageURI : Uri? = null
    }

}