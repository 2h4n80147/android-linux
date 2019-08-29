package com.zhaku.detailing.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import com.zhaku.detailing.LoginAndPassword
import com.zhaku.detailing.R
import com.zhaku.detailing.backendApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import io.reactivex.SingleObserver
import kotlinx.android.synthetic.main.login_form2.*


class LoginActivity : AppCompatActivity() {
    lateinit var headerAuth : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val username = findViewById<TextInputEditText>(R.id.input_username)
        val password = findViewById<TextInputEditText>(R.id.input_password)
        val progressBar = findViewById<ProgressBar>(R.id.progressbar_login)
        val btn = findViewById<AppCompatButton>(R.id.btn_login)
        val btn2 = findViewById<Button>(R.id.btnConnect)

        btn2.setOnClickListener{
            progressBar.isActivated = true
            login(username.text.toString(), password.text.toString())
        }
    }

    fun login(username : String, password : String) {
        val api: backendApiService = backendApiService.createWithRx()

        api.getAuthToken(LoginAndPassword(username, password))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(

                {
                    if (it != null) {
                        Log.d("login", "Success")
                        headerAuth = it
                    }

                },
                {}
            )
    }

}
