package com.zhaku.detailing.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.widget.*
import com.zhaku.detailing.LoginAndPassword
import com.zhaku.detailing.R
import com.zhaku.detailing.backendApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.google.android.material.textfield.TextInputEditText
import com.zhaku.detailing.ItemLists.ItemListActivity
import com.zhaku.detailing.Profile.Info
import com.zhaku.detailing.Register.RegisterActivity




class LoginActivity : AppCompatActivity() {
    val TAG = "LoginActivity"

    lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("oncreate", TAG)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val username = findViewById<TextInputEditText>(R.id.input_username)
        val password = findViewById<TextInputEditText>(R.id.input_password)
        progressBar = findViewById(R.id.progressbar_login)

        val login_button = findViewById<Button>(R.id.btn_login)
        val registerButton = findViewById<TextView>(R.id.link_signup)

        username.setText("Bek")
        password.setText("bek")
        username.addTextChangedListener(  object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun afterTextChanged(p0: Editable?) {
                if (username.text.toString() != "" && password.text.toString() != "")
                    login_button.isEnabled = true
            }
        })

        login_button.setOnClickListener {
            progressBar.setVisibility(ProgressBar.VISIBLE)
            login(username.text.toString(), password.text.toString())
        }
        registerButton.setOnClickListener{

            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    fun login(username : String, password : String) {
        val api: backendApiService = backendApiService.createWithRx()

        api.getAuthToken(LoginAndPassword(username, password))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {

                    Info.authToken = it.toString()
                    val intent = Intent(this@LoginActivity, ItemListActivity::class.java)
                    startActivity(intent)

                    progressBar.setVisibility(ProgressBar.INVISIBLE)

                }
            ,
                {
                    //AlertDialog.Builder(MainActivity)
                    Log.d("login","fail")
                    var toast : Toast = Toast.makeText(getApplicationContext(),
                        "Не удалось зайти, попробуйте заново", Toast.LENGTH_LONG)

                    toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 10, 10)


                    toast.show()
                }
            )
    }

}
