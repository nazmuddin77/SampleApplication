package com.example.dummyapplication

import android.app.Application
import com.example.myapplication.dummyview.factory.ApplicationComponent
import com.example.myapplication.dummyview.factory.ApplicationModule
import com.example.dummyapplication.factory.DaggerApplicationComponent

class DummyApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule())
                .build()
    }

}