package io.volkov.edgeless.cases.keyboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import io.volkov.edgeless.R
import io.volkov.edgeless.SupportFragmentInset

class KeyboardFragment : SupportFragmentInset() {

    private lateinit var keyboardViewModel: KeyboardViewModel

    override fun onInsetsReceived(top: Int, bottom: Int) {

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        keyboardViewModel =
                ViewModelProviders.of(this).get(KeyboardViewModel::class.java)
        return inflater.inflate(R.layout.fragment_keyboard, container, false)
    }
}
