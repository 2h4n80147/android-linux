package com.zhaku.detailing.Register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.zhaku.detailing.R
import kotlinx.android.synthetic.*
import android.content.Intent
import android.util.Log
import com.hootsuite.nachos.NachoTextView
import android.widget.ArrayAdapter
import com.zhaku.detailing.backendApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit




class SecondStep : Fragment() {
    private lateinit var cityChoice : Spinner
    private lateinit var reg_edfields :NachoTextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.registration_second_step, container,false)
        with(rootView) {
            cityChoice = findViewById(R.id.reg_city_choice)
        }
        val uploadImageButton : Button = rootView.findViewById(R.id.reg_select_image)
//        uploadImageButton.setOnClickListener{
//            startActivityForResult(
//                Intent(
//                    Intent.ACTION_PICK,
//                    android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI
//                ), GET_FROM_GALLERY
//            )
//        }
        reg_edfields = rootView.findViewById(R.id.reg_edfieds)
        return rootView
    }
    fun setupNachos() {

//        val retrofit = Retrofit.Builder()
//            .baseUrl(backendApiService.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//                        val suggestions = mutableListOf<String>()
//                        for (v in it) {
//                            suggestions.add (v.value)
//                            Log.d("edfields", v.value)
//                        }
//
//                        val adapter = ArrayAdapter(reg_edfields.context, android.R.layout.simple_dropdown_item_1line, suggestions)
//
//                        reg_edfields.setAdapter(adapter)
//                    }
//                    ,
//                    {}
//                )
    }
}