package com.modart.modamania.sharedpreferences

import android.content.SharedPreferences
import javax.inject.Inject

class SpHelper @Inject
constructor(private val sharedPreferences: SharedPreferences) {

    fun getToken(): String{
        val token = sharedPreferences.getString("token",null)
        token?.let {
            return it
        }
        return ""
    }

    fun getUserId(): String{
        val userId = sharedPreferences.getString("token",null)
        userId?.let {
            return it
        }
        return ""
    }
    fun save(token: String,userId: String){
        sharedPreferences
            .edit()
            .putString("token", "Bearer $token")
            .putString("user_id", userId)
            .apply()
    }

}