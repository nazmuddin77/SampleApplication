package com.example.myapplication.mobius

import com.spotify.mobius.EventSource

interface EventSourceProvider<E> {
    fun eventSource(): EventSource<E>
}
