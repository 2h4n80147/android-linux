package com.zhaku.detailing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx


/**
 * Created by User on 5/28/2017.
 */

class ShareActivity : AppCompatActivity() {

    private val mContext = this@ShareActivity

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
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx)
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx)
        val menu = bottomNavigationViewEx.menu
        val menuItem = menu.getItem(ACTIVITY_NUM)
        menuItem.isChecked = true
    }

    companion object {
        private val TAG = "ShareActivity"
        private val ACTIVITY_NUM = 2
    }
}
