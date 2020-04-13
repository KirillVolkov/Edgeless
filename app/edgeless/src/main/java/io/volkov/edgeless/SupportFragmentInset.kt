package io.volkov.edgeless

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class SupportFragmentInset : Fragment() {

    abstract fun onInsetsReceived(top: Int, bottom: Int)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? OnSystemBarsSizeChangedListener)?.apply {
            observeLiveDataSafe(listener) { verticalInset ->
                onInsetsReceived(verticalInset.top, verticalInset.bottom)
            }
        }
    }
}