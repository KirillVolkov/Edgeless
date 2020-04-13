package io.volkov.edgeless

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData

abstract class SupportActivityInset : AppCompatActivity(), OnSystemBarsSizeChangedListener {

    abstract fun getTopFragment(): Fragment?

    abstract fun onInsetsReceived(top: Int, bottom: Int)

    override val listener = MutableLiveData<VerticalInset>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeLiveDataSafe(listener) { verticalInset ->
            onInsetsReceived(verticalInset.top, verticalInset.bottom)
            val fragment = getTopFragment()
            if (fragment != null && fragment is SupportFragmentInset) {
                fragment.onInsetsReceived(verticalInset.top, verticalInset.bottom)
            }
        }

        overrideSystemInsets(listener)
    }
}

interface OnSystemBarsSizeChangedListener {
    val listener: MutableLiveData<VerticalInset>
}
