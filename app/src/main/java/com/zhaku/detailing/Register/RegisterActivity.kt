package com.zhaku.detailing.Register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import com.zhaku.detailing.R
import kotlinx.android.synthetic.main.snippet_center_editprofile.*
import android.util.Patterns
import android.text.TextUtils
import android.util.Log
import android.view.View


class RegisterActivity : AppCompatActivity() {

     val TAG : String = "REGISTER"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        Log.d("oncreate", TAG)
        var f = FirstStep()
        supportFragmentManager.
            beginTransaction().
            replace(R.id.reg_host_fragment, f)
            .commit()

    }
//    fun validatefields()
//    {
//
//        if(newPassword.getText().toString().length()<8 &&!isValidPassword(newPassword.getText().toString())){
//            System.out.println("Not Valid");
//        }else{
//            System.out.println("Valid");
//        }
//    }

//    //*****************************************************************
//    fun isValidPassword(password: String): Boolean {
//
//        val pattern: Pattern
//        val matcher: Matcher
//        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
//        pattern = Pattern.compile(PASSWORD_PATTERN)
//        matcher = pattern.matcher(password)
//
//        return matcher.matches()
//
//    }
}
