package com.modart.modamania.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object SPHelperModule {

    @Provides
    @Reusable
    @JvmStatic
    fun provideSPHelper(context: Context): SharedPreferences {
        return context.getSharedPreferences("modamania_sp", Context.MODE_PRIVATE)
    }

}