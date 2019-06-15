package com.example.myapplication.mobius

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class MobiusViewModel<T> : ViewModel() {

    abstract fun getLiveModel(): MutableLiveData<T>
}
