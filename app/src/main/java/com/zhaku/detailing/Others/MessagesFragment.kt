package com.zhaku.detailing.Others

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhaku.detailing.R


class MessagesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,  container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_messages, container, false)
    }

    companion object {
        private val TAG = "MessagesFragment"
    }
}
