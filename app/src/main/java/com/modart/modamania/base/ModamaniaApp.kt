package com.modart.modamania.base

import android.app.Application
import android.content.Context

class ModamaniaApp : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = inject()
    }

}

fun ModamaniaApp.inject(): AppComponent {
    val component = DaggerAppComponent.builder()
        .bindAppContext(this)
        .build()
    component.inject(this)
    return component
}

fun Context.getAppComponent() : AppComponent = (applicationContext as ModamaniaApp).component