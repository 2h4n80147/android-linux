package com.zhaku.detailing.Register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zhaku.detailing.R
import kotlinx.android.synthetic.*


class ThirdStep : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.registration_third_step, container,false)

        return rootView
    }
}