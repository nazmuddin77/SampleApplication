package com.example.myapplication.mobius

import com.spotify.mobius.Next

interface MobiusUpdateHandler<M, G, E, F> {
    fun update(model: M, event: E): Next<G, F>
}
