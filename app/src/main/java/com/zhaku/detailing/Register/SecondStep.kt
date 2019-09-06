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
        setupNachos()
        return rootView
    }
    fun setupNachos() {
        val apiService = backendApiService.createWithRx()
        var task = apiService.getEducationFields()
        task.enqueue(object  : Callback<List<EdFieldForChoice>> {
            override fun onResponse(
                call: Call<List<EdFieldForChoice>>,
                response: Response<List<EdFieldForChoice>>
            ) {
                var it = response.body()
                var suggestions = mutableListOf<String>()
                if (it != null)
                    for (v in it ) {
                        suggestions.add(v.value)
                    }
                var adapter = ArrayAdapter(reg_edfields.context, android.R.layout.simple_dropdown_item_1line, suggestions)
                reg_edfields.setAdapter(adapter)

            }

            override fun onFailure(call: Call<List<EdFieldForChoice>>, t: Throwable) {
               Log.d("retrofit","fail")
            }
        })
        reg_edfields.setOnClickListener({
            reg_edfields.showDropDown()
        })

    }
}