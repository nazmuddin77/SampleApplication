package com.example.myapplication.mobius

import android.content.Intent
import android.os.Bundle
import com.spotify.mobius.First.first
import com.spotify.mobius.Init
import com.spotify.mobius.rx2.RxMobius
import io.reactivex.Observable

abstract class ParentMobiusPresenter<M, E, F> : MobiusPresenter<M, E, F>() {

    override fun onCreate(savedInstances: Bundle?) {
        super.onCreate(savedInstances)
        childViews().forEach { it.onCreate(savedInstances) }
    }

    override fun onStart() {
        super.onStart()
        childViews().forEach { it.onStart() }
    }

    override fun onResume() {
        super.onResume()
        childViews().forEach { it.onResume() }
    }

    override fun onPause() {
        childViews().forEach { it.onPause() }
        super.onPause()
    }

    override fun onStop() {
        childViews().forEach { it.onStop() }
        super.onStop()
    }

    override fun onDestroy() {
        childViews().forEach { it.onDestroy() }
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        childViews().forEach { it.onLowMemory() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        childViews().forEach {
            it.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun renderView(model: M) {
        super.renderView(model)
        childViews().forEach {
            it.presenter.renderView(model)
        }
    }

    override fun addEffectHandlers(builder: RxMobius.SubtypeEffectHandlerBuilder<F, E>) {
        super.addEffectHandlers(builder)
        childViews().forEach {
            it.presenter.addEffectHandlers(builder)
        }
    }

    override fun eventsObservable(): Observable<E> {
        // NOTE: For non leaf nodes, merge super.eventsObservable while overriding this method
        return Observable.merge(childViews().map {
            it.presenter.eventsObservable()
        }
        )
    }

    protected abstract fun childViews(): List<NodeMobiusView<M, E, F>>

    protected fun init(): Init<M, F> {
        return Init { model ->
            first(model, childViews()
                    .map {
                        it.presenter.initEffects(model)
                    }
                    .flatten()
                    .toSet()
                    .plus(initEffects(model))
            )
        }
    }
}
