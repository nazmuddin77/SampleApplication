package com.example.myapplication.mobius

import android.os.Bundle
import com.spotify.mobius.EventSource
import com.spotify.mobius.MobiusLoop
import com.spotify.mobius.Update
import com.spotify.mobius.android.AndroidLogger
import com.spotify.mobius.android.MobiusAndroid
import com.spotify.mobius.rx2.RxConnectables
import com.spotify.mobius.rx2.RxMobius
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

abstract class RootMobiusPresenter<M, E, F, V : MobiusView<M>>(protected val view: V) :
        ParentMobiusPresenter<M, E, F>() {

    private lateinit var controller: MobiusLoop.Controller<M, E>

    private val connectViews = { it: Observable<M> ->
        val modelDisposable = it.distinctUntilChanged().subscribe {
            getViewModel().getLiveModel().postValue(it)
            renderView(it)
        }
        this.eventsObservable()
                .doOnDispose { modelDisposable.dispose() }
    }

    override fun onCreate(savedInstances: Bundle?) {
        super.onCreate(savedInstances)
        initMobius(view.getInitModel())
    }

    override fun onStart() {
        super.onStart()
        controller.start()
    }

    override fun onStop() {
        super.onStop()
        controller.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        controller.disconnect()
    }

    override fun addEffectHandlers(builder: RxMobius.SubtypeEffectHandlerBuilder<F, E>) {
        super.addEffectHandlers(builder)
        addNonUiEffectHandlers(builder)
    }

    private fun effectHandlers(): ObservableTransformer<F, E> {
        val builder = RxMobius.subtypeEffectHandler<F, E>()
        addEffectHandlers(builder)
        return builder.build()
    }

    private fun initMobius(initModel: M) {
        val mobiusFactory = RxMobius.loop(updateHandler(), effectHandlers())
                .init(init())
                .eventSource(eventSource())
                .logger(AndroidLogger(this.javaClass.simpleName))
        controller = MobiusAndroid.controller(mobiusFactory, initModel)
        controller.connect(RxConnectables.fromTransformer(connectViews))
    }

    protected abstract fun updateHandler(): Update<M, E, F>

    protected abstract fun eventSource(): EventSource<E>

    protected abstract fun getViewModel(): MobiusViewModel<M>

    protected abstract fun addNonUiEffectHandlers(
        builder: RxMobius.SubtypeEffectHandlerBuilder<F, E>
    )
}
