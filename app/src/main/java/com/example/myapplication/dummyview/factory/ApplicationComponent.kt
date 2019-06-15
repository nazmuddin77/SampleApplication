package com.example.myapplication.dummyview.factory

import com.example.dummyapplication.DummyApplication
import com.example.myapplication.dummyview.Model
import com.example.myapplication.mobius.MobiusViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
        modules = [ApplicationModule::class]
)
interface ApplicationComponent {

    fun viewModel(): MobiusViewModel<Model>

    fun inject(application: DummyApplication)

}