package com.zhaku.detailing.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.ViewFlipper
import com.zhaku.detailing.R

class LoginAndRegister : AppCompatActivity() {
    lateinit var viewFlipper: ViewFlipper
    lateinit var loginButton: Button
    lateinit var registerButton: Button
    lateinit var linkButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_and_register)


        viewFlipper = findViewById(R.id.view_flipper)
        loginButton = findViewById(R.id.login_button)
        registerButton = findViewById(R.id.register_button)
        linkButton = findViewById(R.id.link_signup)
        loginButton.setOnClickListener {
            val id = viewFlipper.displayedChild
            if (id == 1)
                viewFlipper.showNext()
        }
        registerButton.setOnClickListener {
            val id = viewFlipper.displayedChild
//            Snackbar.make(it, id.toString() + " " + R.layout.register_form + " " + R.layout.login_form, Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            if (id == 0)
                viewFlipper.showNext()
        }
        linkButton.setOnClickListener{
            viewFlipper.showNext()
        }
    }
}
