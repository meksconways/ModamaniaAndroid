package com.modart.modamania.networking

import com.modart.modamania.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

@Module
object NetworkModule{

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create()
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

}