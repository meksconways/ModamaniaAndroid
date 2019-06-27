package com.modart.modamania.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.modart.modamania.model.FeedModel
import com.modart.modamania.networking.ApiRequester
import com.modart.modamania.sharedpreferences.SpHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
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
        compositeDisposable.add(
            apiRequester.getFeed(spHelper.getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {

                        it?.let { model ->
                            feedData.value = model
                            pageLoading.value = false
                        }

                    },
                    {}

                )
        )


    }

    override fun onCleared() {
        compositeDisposable.clear()
    }

}
