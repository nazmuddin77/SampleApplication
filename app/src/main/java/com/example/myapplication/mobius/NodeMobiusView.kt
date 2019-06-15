package com.example.myapplication.mobius

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer

abstract class NodeMobiusView<M, E, F> : MobiusView<M> {

    abstract val presenter: MobiusPresenter<M, E, F>

    open fun onCreate(savedInstanceState: Bundle?) {
        presenter.onCreate(savedInstanceState)
    }

    open fun onStart() {
        presenter.onStart()
    }

    open fun onResume() {
        presenter.onResume()
    }

    open fun onPause() {
        presenter.onPause()
    }

    open fun onStop() {
        presenter.onStop()
    }

    open fun onDestroy() {
        presenter.onDestroy()
    }

    open fun onLowMemory() {
        presenter.onLowMemory()
    }

    open fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        presenter.onActivityResult(requestCode, resultCode, data)
    }

    protected val renderView = Observer<M> { model ->
        if (model == null) {
            return@Observer
        }

        presenter.renderView(model)
    }
}
