package com.modart.modamania.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.modart.modamania.networking.ApiRequester
import com.modart.modamania.util.getApiBadRequestError
import com.modart.modamania.util.reqBodytoJson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import javax.inject.Inject

class LoginViewModel
@Inject constructor(
    private val apiRequester: ApiRequester
) : ViewModel() {

    private val pageLoading = MutableLiveData<Boolean>(false)
    private val routeMain = MutableLiveData<Boolean>(false)
    private val alertMessage = MutableLiveData<String?>(null)
    private val compositeDisposable = CompositeDisposable()

    fun getPageLoading(): LiveData<Boolean> = pageLoading
    fun getRouteMain(): LiveData<Boolean> = routeMain
    fun getAlertMessage(): LiveData<String?> = alertMessage

    fun clearAlert() {
        alertMessage.value = null
    }

    fun login(username: String, password: String) {
        val map = HashMap<String, String>()
        map["username"] = username
        map["password"] = password
        compositeDisposable.add(
            apiRequester.login(reqBodytoJson(map))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { pageLoading.value = true }
                .subscribe(
                    {

                        pageLoading.value = false
                        routeMain.value = true
                    },
                    {

                        if (it is HttpException) {
                            pageLoading.value = false
                            alertMessage.value = getApiBadRequestError(
                                it.response().errorBody()?.string()
                            )


                        }
                    }
                )
        )
    }


    override fun onCleared() {
        compositeDisposable.clear()
    }


}
