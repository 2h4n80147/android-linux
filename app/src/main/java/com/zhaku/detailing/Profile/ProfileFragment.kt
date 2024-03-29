package com.zhaku.detailing.Profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.zhaku.detailing.R
import com.zhaku.detailing.data.backendApiService
import kotlinx.android.synthetic.main.profile_view.*


class ProfileFragment : Fragment() {
    val TAG = "ProfileFragment"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "oncreate")


        val rootView = inflater.inflate(R.layout.profile_view, container, false)
        val edit : TextView = rootView.findViewById(R.id.profile_edit_button)
        edit.setOnClickListener{
            val intent = Intent(rootView.context, EditProfile::class.java)
            startActivity(intent)
        }
        return rootView
    }
    fun getUserInfo() {
        val apiService = backendApiService.createWithRx()

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.profile_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_exit -> {
                activity?.finish()

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}