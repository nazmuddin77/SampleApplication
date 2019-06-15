package com.example.myapplication.dummyview

import com.example.myapplication.mobius.MobiusView
import com.example.myapplication.mobius.RootMobiusPresenter
import io.reactivex.Observable

interface DummyContract {
    interface View : MobiusView<Model> {
        fun render(model: Model)
        fun setTextClickedObservable(): Observable<Event>
    }

    abstract class Presenter(view: View) : RootMobiusPresenter<Model, Event, Effect, View>(view)
}