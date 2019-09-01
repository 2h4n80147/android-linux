package com.zhaku.detailing

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


/*
.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
 */

interface backendApiService {

    @GET("customers/students/{id}/")
     fun getStudentById(@Path("id") id : Int): Single<Student>
    @GET("customers/education_centers/{id}/")
    fun getEducationCenterById(@Path("id") id : Int): Single<EducationCenter>
    @GET("tutors/{id}/")
    fun getTutorById(@Path("id") id : Int): Single<Tutor>

    @GET("customers/students/")
    fun getStudentList() : Observable<List<Student>>
    @GET("customers/education_centers/")
    fun getEducationCenterList() : Observable<List<EducationCenter> >
    @GET("customers/tutors/")
    fun getTutorList() : Observable<List<Tutor> >

    @PUT("customers/students/{id}")
    fun updateStudent(@Path("id") id : Int, @Body student : Student)

    @POST("api-token-auth/")
    fun getAuthToken(@Body log : LoginAndPassword) : Single<Any>

    @GET("customers/students")
    fun queryOnCity(@Query("city") city : String) : Observable<List<Student>>
    @POST("customers/students")
    fun registerStudent(@Body user : Student) : Single<Student>



    /**
     * Companion object to create the apiService
     */
    companion object Factory {
        fun createWithRx(): backendApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://bilimdelion-backup-server.herokuapp.com/")
                .build()

            return retrofit.create(backendApiService::class.java)
        }
    }
}