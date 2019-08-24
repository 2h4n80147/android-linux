package com.zhaku.detailing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.zhaku.detailing.backendApiService
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.item_detail.view.*
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var t : TextView = findViewById(R.id.ttt)
        val apiService = backendApiService.createWithRx()
        val student = apiService.getStudentById(16)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

            .subscribe(object : SingleObserver<Student> {
                override fun onSubscribe(d: Disposable) {
                    // we'll come back to this in a moment
                }

                override fun onSuccess(person: Student) {

                    // data is ready and we can update the UI
                    t.text = person.toString()
                }

                override fun onError(e: Throwable) {
                    // oops, we best show some error message
                }
            })
    }
}
