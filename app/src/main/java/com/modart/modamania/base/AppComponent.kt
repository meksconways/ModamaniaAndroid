package com.modart.modamania.base

import android.content.Context
import com.modart.modamania.networking.NetworkModule
import com.modart.modamania.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ViewModelModule::class,
    NetworkModule::class
])
interface AppComponent {

    fun inject(application: ModamaniaApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindAppContext(application: Context): Builder

        fun build(): AppComponent

    }


}