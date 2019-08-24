package com.zhaku.detailing

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View


class CameraFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?):
            View? {

        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    companion object {
        private val TAG = "CameraFragment"
    }
}