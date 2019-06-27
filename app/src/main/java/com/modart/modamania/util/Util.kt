package com.modart.modamania.util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

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

fun ImageView.loadImage(url: String){
    Glide.with(this)
        .load(url)
        .override(1000,1000)
        .into(this)
}


fun getPostDate(output: String): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
    return try {
        val date: Date = sdf.parse(output)
        sdf.applyPattern("dd MMMM EEEE")
        sdf.toLocalizedPattern()
        val newSdf = SimpleDateFormat("dd MMMM EEEE", Locale.getDefault())
        newSdf.format(date)
    } catch (e: Exception) {
        e.printStackTrace()
        output
    }

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



