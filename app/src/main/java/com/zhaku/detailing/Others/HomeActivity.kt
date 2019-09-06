package com.zhaku.detailing.Others

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.zhaku.detailing.R

class HomeActivity : AppCompatActivity() {
    private val TAG = "HomeActivity"
    private val ACTIVITY_NUM = 0

    private val mContext = this@HomeActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Log.d(TAG, "onCreate: starting.");

        setupBottomNavigationView();
    }

    /**
     * BottomNavigationView setup
     */
    private fun setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView")
        val bottomNavigationViewEx = findViewById(R.id.bottomNavViewBar) as BottomNavigationViewEx
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx)
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx)
        val menu = bottomNavigationViewEx.menu
        val menuItem = menu.getItem(ACTIVITY_NUM)
        menuItem.isChecked = true
    }
}
