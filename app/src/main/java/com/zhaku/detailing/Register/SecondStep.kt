package com.zhaku.detailing.Register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.zhaku.detailing.R
import kotlinx.android.synthetic.*


class SecondStep : Fragment() {
    private lateinit var cityChoice : Spinner
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.registration_second_step, container,false)
        with(rootView) {
            cityChoice = findViewById(R.id.reg_city_choice)

        }
        return rootView
    }
}