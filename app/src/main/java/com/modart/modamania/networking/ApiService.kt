package com.modart.modamania.networking

import com.modart.modamania.model.LoginModel
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {


    @POST("login")
    fun login(@Body loginBody: RequestBody): Single<LoginModel>



}