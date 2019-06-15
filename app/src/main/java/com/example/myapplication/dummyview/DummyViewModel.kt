package com.example.myapplication.dummyview

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.mobius.MobiusViewModel
import javax.inject.Inject

class DummyViewModel @Inject constructor() : MobiusViewModel<Model>() {
    private val liveData = MutableLiveData<Model>()

    override fun getLiveModel(): MutableLiveData<Model> {
        return liveData
    }

}