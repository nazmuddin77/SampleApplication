package com.example.myapplication.mobius

import com.spotify.mobius.EventSource
import com.spotify.mobius.rx2.RxEventSources
import com.spotify.mobius.rx2.RxMobius
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

typealias TransformerType<E, F> = (Observable<F>) -> Observable<E>
typealias ConsumerType<F> = (F) -> Unit
typealias ActionType = () -> Unit

fun <F, E, G : F> RxMobius.SubtypeEffectHandlerBuilder<F, E>.addUiConsumer(effectClass: Class<G>, consumer: ConsumerType<G>): RxMobius.SubtypeEffectHandlerBuilder<F, E> {
    return this.addConsumer(effectClass, consumer, AndroidSchedulers.mainThread())
}

fun <F, E, G : F> RxMobius.SubtypeEffectHandlerBuilder<F, E>.addUiAction(effectClass: Class<G>, action: ActionType): RxMobius.SubtypeEffectHandlerBuilder<F, E> {
    return this.addAction(effectClass, action, AndroidSchedulers.mainThread())
}

fun <E> emptyEventSource(): EventSource<E> {
    return RxEventSources.fromObservables(Observable.never())
}
