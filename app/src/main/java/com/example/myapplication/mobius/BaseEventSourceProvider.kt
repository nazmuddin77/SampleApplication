package com.example.myapplication.mobius

import com.spotify.mobius.EventSource
import com.spotify.mobius.rx2.RxEventSources
import io.reactivex.Observable

abstract class BaseEventSourceProvider<E>(private val eventSources: List<MobiusEventSource<E>>) : EventSourceProvider<E> {

    override fun eventSource(): EventSource<E> {
        return if (eventSources.isEmpty()) {
            RxEventSources.fromObservables(Observable.never())
        } else {
            eventSources
                    .map { eventSource ->
                        eventSource.eventObservable()
                    }
                    .let { observables ->
                        RxEventSources.fromObservables(*observables.toTypedArray())
                    }
        }
    }
}
