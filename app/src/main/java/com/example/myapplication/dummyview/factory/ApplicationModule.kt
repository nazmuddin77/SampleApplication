package com.example.myapplication.dummyview.factory

import com.example.myapplication.dummyview.DummyViewModel
import com.example.myapplication.dummyview.Model
import com.example.myapplication.mobius.MobiusViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
@Singleton
class ApplicationModule {

    @Provides
    @Singleton
    fun provideViewModel(viewModel: DummyViewModel): MobiusViewModel<Model> {
        return viewModel
    }

}