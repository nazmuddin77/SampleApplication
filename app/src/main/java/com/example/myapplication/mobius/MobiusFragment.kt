package com.example.myapplication.mobius

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class MobiusFragment<M, E, F> : Fragment(), MobiusView<M> {

    protected abstract val presenter: MobiusPresenter<M, E, F>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        super.onPause()
        presenter.onPause()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        presenter.onLowMemory()
    }

    abstract fun initOnCreate()
}
