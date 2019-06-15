package com.example.myapplication.mobius

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

abstract class MobiusAppCompatActivity<M, E, F> : AppCompatActivity(), MobiusView<M> {

    protected abstract val presenter: MobiusPresenter<M, E, F>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initOnCreate()
        presenter.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        presenter.onPause()
        super.onPause()
    }

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        presenter.onLowMemory()
        super.onLowMemory()
    }

    protected val renderView = Observer<M> { model ->
        if (model == null) {
            return@Observer
        }

        presenter.renderView(model)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.onActivityResult(requestCode, resultCode, data)
    }

    abstract fun initOnCreate()
}
