package com.zhaku.detailing.Login

import android.app.ProgressDialog.show
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isVisible
import com.zhaku.detailing.LoginAndPassword
import com.zhaku.detailing.R
import com.zhaku.detailing.backendApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import io.reactivex.SingleObserver
import kotlinx.android.synthetic.main.login_form2.*
import retrofit2.adapter.rxjava2.Result.response
import com.google.gson.Gson
import com.zhaku.detailing.ItemLists.ItemListActivity
import com.zhaku.detailing.MainActivity
import com.zhaku.detailing.Register.RegisterActivity




class LoginActivity : AppCompatActivity() {
    val TAG = "LoginActivity"
    lateinit var headerAuth : String

    lateinit var progressBar : ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("oncreate", TAG)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val username = findViewById<TextInputEditText>(R.id.input_username)
        val password = findViewById<TextInputEditText>(R.id.input_password)
        progressBar = findViewById(R.id.progressbar_login)

        val btn2 = findViewById<Button>(R.id.btn_login)
        val registerButton = findViewById<TextView>(R.id.link_signup)

        username.setText("Bek")
        password.setText("bek")

        btn2.setOnClickListener {
            progressBar.setVisibility(ProgressBar.VISIBLE);

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

                    headerAuth = it.toString()
                    val intent = Intent(this@LoginActivity, ItemListActivity::class.java)
                    startActivity(intent)

                    progressBar.setVisibility(ProgressBar.INVISIBLE)

                }
            ,
                {
                    Log.d("login","fail")
                    var toast : Toast = Toast.makeText(getApplicationContext(),
                        "Не удалось зайти, попробуйте заново", Toast.LENGTH_LONG)

                    toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 10, 10)


                    toast.show()
                }
            )
    }

}
