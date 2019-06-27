package com.modart.modamania.networking

import com.modart.modamania.model.FeedModel
import com.modart.modamania.model.LoginModel
import com.modart.modamania.model.ProfileModel
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {


    @POST("login")
    fun login(@Body loginBody: RequestBody): Single<LoginModel>

    @GET("getFeeds")
    fun getFeed(@Header("Authorization") token: String): Single<List<FeedModel>>

    @GET("myProfile")
    fun getMyProfile(@Header("Authorization") token: String): Single<ProfileModel>




}