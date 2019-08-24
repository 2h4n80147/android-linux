package com.zhaku.detailing

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface backendApiService {

    @GET("students/{id}/")
     fun getStudentById(@Path("id") id : Int): Single<Student>
    @GET("education_centers/{id}/")
    fun getEducationCenterById(@Path("id") id : Int): Single<EducationCenter>

    @GET("students/")
     fun getStudentList() : Response<List<Student>>
    @GET("education_centers/")
     fun getEducationCenterList() : Response<List<EducationCenter>>



    /**
     * Companion object to create the apiService
     */
    companion object Factory {
        fun createWithRx(): backendApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://bilimdelion-backend.herokuapp.com/customers/")
                .build()

            return retrofit.create(backendApiService::class.java)
        }
    }
}