package com.example.myapplication.dummyview.factory

import com.example.myapplication.dummyview.*
import com.example.myapplication.mobius.MobiusPresenter
import dagger.Module
import dagger.Provides


@ActivityScope
@Module
class ActivityModule(private val activity: MainActivity) {


    @Provides
    @ActivityScope
    fun provideView(): DummyContract.View {
        return activity
    }

    @Provides
    @ActivityScope
    fun providePresenter(presenter: DummyPresenter): MobiusPresenter<Model, Event, Effect> {
        return presenter
    }


}