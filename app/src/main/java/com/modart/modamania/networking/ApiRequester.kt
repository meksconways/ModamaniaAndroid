package com.modart.modamania.networking

import com.modart.modamania.model.LoginModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRequester @Inject constructor(private val apiService: ApiService){

    fun login(body: RequestBody): Single<LoginModel> =
        apiService.login(body).subscribeOn(Schedulers.io())


}