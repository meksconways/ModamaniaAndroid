package com.modart.modamania.profile

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.modart.modamania.model.FeedPostModel
import com.modart.modamania.model.ProfileModel
import com.modart.modamania.networking.ApiRequester
import com.modart.modamania.sharedpreferences.SpHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ProfileViewModel
    @Inject constructor(
        private val spHelper: SpHelper,
        private val apiRequester: ApiRequester
    )
    : ViewModel() {


    private val profileData = MutableLiveData<ProfileModel>()
    private val postData = MutableLiveData<List<FeedPostModel>>()
    private val postArr = arrayListOf<FeedPostModel>()
    private val pageLoading = MutableLiveData<Boolean>(true)
    private val compositeDispose = CompositeDisposable()

    fun getProfileData(): LiveData<ProfileModel> = profileData
    fun getPostData(): LiveData<List<FeedPostModel>> = postData
    fun getPageLoading(): LiveData<Boolean> = pageLoading

    init {
        fetchProfile()
    }

    fun fetchProfile(){

        compositeDispose.add(
            apiRequester.getMyProfile(spHelper.getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        it?.let { profileModel ->
                            profileData.value = profileModel
                            postArr.addAll(profileModel.user.posts)
                            postData.value = postArr.reversed()
                            pageLoading.value = false
                        }

                    },
                    {}
                )

        )


    }


    override fun onCleared() {
        compositeDispose.clear()
    }

}
