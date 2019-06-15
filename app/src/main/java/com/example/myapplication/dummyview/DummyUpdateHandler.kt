package com.example.myapplication.dummyview

import com.spotify.mobius.Next
import com.spotify.mobius.Update

class DummyUpdateHandler : Update<Model, Event, Effect> {

    override fun update(model: Model, event: Event): Next<Model, Effect> {
        return when (event) {
            is Event.OnSetTextClick -> {
                val updatedModel = model.copy(text = event.text)
                Next.next(updatedModel)
            }
        }
    }

}