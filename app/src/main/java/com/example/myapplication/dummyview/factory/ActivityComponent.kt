package com.example.myapplication.dummyview.factory

import com.example.myapplication.dummyview.MainActivity
import com.example.myapplication.dummyview.Model
import com.example.myapplication.mobius.MobiusViewModel
import dagger.Component


@ActivityScope
@Component(
        modules = [ActivityModule::class],
        dependencies = [ApplicationComponent::class]
)
interface ActivityComponent {

    fun viewModel(): MobiusViewModel<Model>

    fun inject(activity: MainActivity)

}