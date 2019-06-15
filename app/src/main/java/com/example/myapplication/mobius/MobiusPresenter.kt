package com.example.myapplication.mobius

import android.content.Intent
import android.os.Bundle
import com.spotify.mobius.rx2.RxMobius
import io.reactivex.Observable

abstract class MobiusPresenter<M, E, F> {

    open fun initEffects(model: M): Set<F> = emptySet()

    open fun renderView(model: M) {}

    open fun onCreate(savedInstances: Bundle?) {}

    open fun onStart() {}

    open fun onResume() {}

    open fun onPause() {}

    open fun onStop() {}

    open fun onDestroy() {}

    open fun onLowMemory() {}

    open fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {}

    open fun addEffectHandlers(builder: RxMobius.SubtypeEffectHandlerBuilder<F, E>) {}

    open fun eventsObservable(): Observable<E> {
        return Observable.never()
    }
}
