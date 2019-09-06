package com.zhaku.detailing.Register

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.zhaku.detailing.Login.LoginActivity
import com.zhaku.detailing.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.registration_first_step.*
import kotlinx.android.synthetic.main.snippet_center_editprofile.*


class FirstStep : Fragment() {

    var valid = 1
    private lateinit var user_name : TextInputEditText
    private lateinit var name_surname : TextInputEditText
    private lateinit var email : TextInputEditText
    private lateinit var password : TextInputEditText
    private lateinit var phone : TextInputEditText
    private lateinit var btn_register : Button
    private lateinit var userTypeChoice : RadioGroup
    private lateinit var name_surname_layout : TextInputLayout
    private lateinit var link_signup : TextView

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
            password = findViewById(R.id.input_password)
            phone = findViewById(R.id.input_phone_number)
            btn_register = findViewById(R.id.btn_register)
            userTypeChoice = findViewById(R.id.usertype)
            name_surname_layout = findViewById(R.id.input_fullname_layout)
            link_signup = findViewById(R.id.link_signup)
        }
        link_signup.setOnClickListener{
            val intent =  Intent(it.context, LoginActivity::class.java)
            startActivity(intent)
        }
        userTypeChoice.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.reg_choice_edu -> {
                    //name_surname.hint = "Имя"
                    name_surname_layout.hint = "Имя"
                }
                R.id.reg_choice_student -> {
                    name_surname_layout.hint = "Имя и Фамилия"
                }
                R.id.reg_choice_tutor -> {
                    name_surname_layout.hint = "Имя и Фамилия"
                }

            }
        }


        btn_register.setOnClickListener {

            if (user_name.text.toString().length < 3) {
                user_name.setError("Заполните поле (минимум 3 буквы)")
                valid = 0
            }
            if (name_surname.text.toString().equals("")) {
                name_surname.setError("Заполните поле")
                valid = 0
            }
            if (password.text.toString() == "") {
                valid = 0
                user_name.setError("Заполните поле")
            }
            if (phone.text.toString().equals("")) {
                user_name.setError("Заполните поле")
                valid = 0
            }
            if (email.text.toString().length > 0 && !isValidEmail(email.text.toString())) {
                email.setError("Неправильный формат")
                valid = 0
            }
            if (valid == 1) {
                val next = SecondStep()

                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.reg_host_fragment, next)
                    ?.commit()
            }
        }
        return rootView
    }
    fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}