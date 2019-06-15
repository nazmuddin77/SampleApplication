package com.example.myapplication.mobius

import io.reactivex.Observable

interface MobiusEventSource<E> {
    fun eventObservable(): Observable<E>
}
