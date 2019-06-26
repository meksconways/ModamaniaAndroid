package com.modart.modamania.viewmodel

import androidx.lifecycle.ViewModel
import com.modart.modamania.feed.FeedViewModel
import com.modart.modamania.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

//    @Binds
//    @IntoMap
//    @ViewModelKey (FeedViewModel::class)
//    abstract fun bindFeedViewModel(viewModel: FeedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginVM(viewModel: LoginViewModel): ViewModel

}