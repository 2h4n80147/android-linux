package com.zhaku.detailing.Others

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.zhaku.detailing.R


/**
 * Created by User on 5/28/2017.
 */

class ProfileActivity : AppCompatActivity() {

    private val mContext = this@ProfileActivity

    override fun onCreate( savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Log.d(TAG, "onCreate: started.")

        setupBottomNavigationView()
    }

    /**
     * BottomNavigationView setup
     */
    private fun setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView")
        val bottomNavigationViewEx = findViewById(R.id.bottomNavViewBar) as BottomNavigationViewEx
        BottomNavigationViewHelper.setupBottomNavigationView(
            bottomNavigationViewEx
        )
        BottomNavigationViewHelper.enableNavigation(
            mContext,
            bottomNavigationViewEx
        )
        val menu = bottomNavigationViewEx.menu
        val menuItem = menu.getItem(ACTIVITY_NUM)
        menuItem.isChecked = true
    }

    companion object {
        private val TAG = "ProfileActivity"
        private val ACTIVITY_NUM = 4
    }
}