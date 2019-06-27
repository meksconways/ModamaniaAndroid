package com.modart.modamania.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.modart.modamania.model.FeedModel
import com.modart.modamania.networking.ApiRequester
import com.modart.modamania.sharedpreferences.SpHelper
import com.modart.modamania.util.getApiBadRequestError
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import javax.inject.Inject

class FeedViewModel
@Inject constructor(
    private val spHelper: SpHelper,
    private val apiRequester: ApiRequester
) : ViewModel() {

    private val feedData = MutableLiveData<List<FeedModel>>()
    private val pageLoading = MutableLiveData<Boolean>(true)
    private val compositeDisposable = CompositeDisposable()

    fun getPageLoading(): LiveData<Boolean> = pageLoading
    fun getFeedData(): LiveData<List<FeedModel>> = feedData



    init {
        fetchFeed()
    }

    private fun fetchFeed() {

        Log.d("***token", spHelper.getToken())
        compositeDisposable.add(
            apiRequester.getFeed(spHelper.getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.d("***burdayz", "asdasd")
                        it?.let { model ->
                            feedData.value = model
                            pageLoading.value = false
                        }

                    },
                    {
                        if (it is HttpException) {
                            Log.d("***error", it.localizedMessage)
                            val a = getApiBadRequestError(
                                it.response().errorBody()?.string()
                            )
                            Log.d("***error", a)
                        }

                    }

                )
        )


    }

}
