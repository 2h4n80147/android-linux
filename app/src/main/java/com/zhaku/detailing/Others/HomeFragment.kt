package com.zhaku.detailing.Others

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.zhaku.detailing.R
import kotlinx.android.synthetic.main.bottom_bar_sheet.*


class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = inflater.inflate(R.layout.fragment_home, container, false)
        var bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet)
        return rootview

    }

    companion object {
        private val TAG = "HomeFragment"
    }
}

