package io.volkov.edgeless.cases.collapsing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import io.volkov.edgeless.R
import io.volkov.edgeless.SupportFragmentInset
import io.volkov.edgeless.setVerticalMargin
import kotlinx.android.synthetic.main.fragment_collapsing.*

class CollapseAppBarFragment : SupportFragmentInset() {

    private lateinit var collapseAppBarViewModel: CollapseAppBarViewModel

    override fun onInsetsReceived(top: Int, bottom: Int) {
        toolbar.setVerticalMargin(marginTop = top)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        collapseAppBarViewModel =
            ViewModelProviders.of(this).get(CollapseAppBarViewModel::class.java)
        return inflater.inflate(R.layout.fragment_collapsing, container, false)
    }
}
