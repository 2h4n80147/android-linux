package com.zhaku.detailing.Register

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.zhaku.detailing.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.snippet_center_editprofile.*


class FirstStep : Fragment() {

    private lateinit var user_name : TextInputEditText
    private lateinit var name_surname : TextInputEditText
    private lateinit var email : TextInputEditText
    private lateinit var password : TextInputEditText
    private lateinit var phone : TextInputEditText
    private lateinit var btn_register : Button

    val TAG : String = this.javaClass.name
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("oncreate", TAG)

        val rootView = inflater.inflate(R.layout.registration_first_step, container, false)
        with(rootView) {


            user_name = findViewById(R.id.input_username)
            name_surname = findViewById(R.id.input_fullname)
            email = findViewById(R.id.input_email)
            password = findViewById(R.id.input_email)
            phone = findViewById(R.id.input_phone_number)
            btn_register = findViewById(R.id.btn_register)
        }

        btn_register.setOnClickListener {
            if (user_name.text.toString().length < 3)
                user_name.setError("Заполните поле (минимум 3 буквы)")
            if (name_surname.text.toString().equals(""))
                name_surname.setError("Заполните поле")
            if (password.text.toString() == "")
                user_name.setError("Заполните поле")
            if (phone.text.toString().equals(""))
                user_name.setError("Заполните поле")
            if (email.text.toString().length > 0 && !isValidEmail(email.text.toString()))
                email.setError("Неправильный формат")

        }
        return rootView
    }
    fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}