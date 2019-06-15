package com.example.myapplication.dummyview

import android.os.Bundle
import com.example.dummyapplication.DummyApplication
import com.example.myapplication.R
import com.example.myapplication.dummyview.factory.ActivityModule
import com.example.myapplication.mobius.MobiusAppCompatActivity
import com.example.myapplication.mobius.MobiusPresenter
import com.example.myapplication.mobius.MobiusViewModel
import io.reactivex.Observable
import javax.inject.Inject

class MainActivity : MobiusAppCompatActivity<Model, Event, Effect>(), DummyContract.View {

    @Inject
    lateinit var dummyPresenter: MobiusPresenter<Model, Event, Effect>

    @Inject
    lateinit var viewModel: MobiusViewModel<Model>


    override val presenter: MobiusPresenter<Model, Event, Effect>
        get() = dummyPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun initOnCreate() {
        val applicationComponent = (application as DummyApplication).applicationComponent
        DaggerActivityComponent
                .builder()
                .activityModule(ActivityModule(this))
                .applicationComponent(applicationComponent)
                .build()
                .inject(this)
    }

    override fun getInitModel(): Model {
        return viewModel.getLiveModel().value ?: Model("")
    }

    override fun setTextClickedObservable(): Observable<Event> {
        return RxView.clicks(button).map { Event.OnSetTextClick(System.currentTimeMillis().toString()) }
                .cast(Event::class.java)
    }

    override fun render(model: Model) {
        textView.text = model.text
    }
}
