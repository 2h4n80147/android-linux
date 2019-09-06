package com.zhaku.detailing.StudentContent

import com.zhaku.detailing.data.EducationCenter
import com.zhaku.detailing.data.Student
import com.zhaku.detailing.data.Tutor
import com.zhaku.detailing.data.backendApiService
import java.util.ArrayList
import java.util.HashMap
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

//init {
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
//}
object EducationCenterContent { // 0

    val ITEMS: MutableList<EducationCenter> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, EducationCenter> = HashMap()

    private var COUNT = 0
    /**
     * An array of sample (dummy) items.
     */
    var complete = false


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
object StudentContent { // 2

    val ITEMS: MutableList<Student> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, Student> = HashMap()

    private var COUNT = 0
    /**
     * An array of sample (dummy) items.
     */
    var complete = false


    fun addItem(item: Student) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id.toString(), item)
        COUNT++
    }
    fun makeItem( id : Int) {
        var tag : String = "student_id"
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
object TutorContent {

    val ITEMS: MutableList<Tutor> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, Tutor> = HashMap()

    private var COUNT = 0
    /**
     * An array of sample (dummy) items.
     */
    var complete = false


     fun addItem(item: Tutor) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id.toString(), item)
        COUNT++
    }
    fun makeItem( id : Int) {
        var tag : String = "tutor_id"
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


