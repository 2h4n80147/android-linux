package com.zhaku.detailing.StudentContent

import android.app.Activity
import android.media.Image
import android.net.Uri
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar
import com.zhaku.detailing.*
import kotlinx.android.synthetic.main.item_list_content.view.*
import java.util.ArrayList
import java.util.HashMap
import com.zhaku.detailing.dummy.DummyContent
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection
import java.security.AccessController.getContext


object EducationCenterContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<EducationCenter> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, EducationCenter> = HashMap()
    val complete = false
    private var COUNT = 0

    init {
        //makeItem(7)
//        val api = backendApiService.create()


//        CoroutineScope(Dispatchers.IO).launch {
//            val response = api.getEducationCenterList()
//            withContext(Dispatchers.Main) {
//                try {
//                    if (response.isSuccessful) {
//                        var x = response.body()
//                        for (y: EducationCenter in x!!.toList()) {
//                            addItem(y)
//                        }
//                        COUNT = ITEMS.count()
//                    }
//                    else {
//                        Log.d("lol", "Response is not successful here")
//                    }
//                } catch (e: Throwable) {
//                    Log.d("EXP", "Ooops: Something else went wrong")
//                } catch (e: HttpException) {
//                    Log.d("EXP", "Exception ${e.message}");
//                }
//            }
//        }
    }

    public fun addItem(item: EducationCenter) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id.toString(), item)
        COUNT++
    }
     fun makeItem( id : Int) {
        var tag : String = "education_center_id"
        val apiService = backendApiService.createWithRx()
        var res = apiService.getEducationCenterById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                },
                {}
            )
    }

}
