package com.modart.modamania.base

import android.content.Context
import com.modart.modamania.login.LoginFragment
import com.modart.modamania.networking.NetworkModule
import com.modart.modamania.sharedpreferences.SPHelperModule
import com.modart.modamania.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class,
        NetworkModule::class,
        SPHelperModule::class
    ]
)
interface AppComponent {

    fun inject(application: ModamaniaApp)
    fun inject(loginFragment: LoginFragment)
    fun inject(baseFragment: BaseFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindAppContext(application: Context): Builder

        fun build(): AppComponent

    }


}