package com.example.myapplication.dummyview

import com.example.myapplication.mobius.MobiusViewModel
import com.example.myapplication.mobius.NodeMobiusView
import com.spotify.mobius.EventSource
import com.spotify.mobius.Update
import com.spotify.mobius.rx2.RxEventSources
import com.spotify.mobius.rx2.RxMobius
import io.reactivex.Observable
import javax.inject.Inject

class DummyPresenter @Inject constructor(view: DummyContract.View, private val dummyViewModel: MobiusViewModel<Model>) : DummyContract.Presenter(view) {

    override fun updateHandler(): Update<Model, Event, Effect> {
        return DummyUpdateHandler()
    }

    override fun initEffects(model: Model): Set<Effect> {
        return setOf(Effect.CheckForInitState)
    }

    override fun eventsObservable(): Observable<Event> {
        return view.setTextClickedObservable()
    }

    override fun eventSource(): EventSource<Event> {
        return RxEventSources.fromObservables(Observable.never())
    }

    override fun addNonUiEffectHandlers(builder: RxMobius.SubtypeEffectHandlerBuilder<Effect, Event>) {
    }

    override fun childViews(): List<NodeMobiusView<Model, Event, Effect>> {
        return emptyList()
    }

    override fun getViewModel(): MobiusViewModel<Model> {
        return dummyViewModel
    }

    override fun renderView(model: Model) {
        super.renderView(model)
        view.render(model)
    }

}