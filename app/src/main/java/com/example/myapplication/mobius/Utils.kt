package com.example.myapplication.mobius

import com.spotify.mobius.Next

fun <M, F> dispatchEffects(vararg effects: F): Next<M, F> {
    val effectSet = effects.toSet()
    return Next.dispatch(effectSet)
}

fun <M, F> dispatchModel(model: M): Next<M, F> {
    return Next.next(model)
}

fun <M, F> noChange(): Next<M, F> {
    return Next.noChange()
}

fun <M, F> updateModelAndDispatchEffects(model: M, vararg effects: F): Next<M, F> {
    val effectSet = effects.toSet()
    return Next.next(model, effectSet)
}

fun <M, F> updatedModel(change: Next<M, F>, model: M): M {
    return if (change.hasModel()) {
        change.modelUnsafe()
    } else {
        model
    }
}
