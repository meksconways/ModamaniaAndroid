package com.modart.modamania.util

import android.view.View
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject

const val BASE_URL = "https://modamania.herokuapp.com/api/"

enum class ToolbarFont{
    N,OS
}

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}

fun View.invisible(){
    this.visibility = View.INVISIBLE
}

fun reqBodytoJson(items: Map<String, String>): RequestBody {

    val jsonObject = JSONObject()
    try {

        items.forEach { (key, value) -> jsonObject.put(key, value) }

    } catch (e: Exception) {
        e.printStackTrace()
    }


    val mediaType = MediaType.parse("application/json")
    return RequestBody.create(mediaType, jsonObject.toString())

}

fun getApiBadRequestError(data: String?): String {
    var message = ""

    if (data == null) {
        return ""
    }
    val jsonObject: JSONObject
    try {
        jsonObject = JSONObject(data)
        message = jsonObject.get("message").toString()

    } catch (e: Exception) {
        print("*******json parse error!!!")
        e.printStackTrace()
    }


    return message

}



