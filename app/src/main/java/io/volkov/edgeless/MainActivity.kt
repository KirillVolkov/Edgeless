package io.volkov.edgeless

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : SupportActivityInset() {

    override fun getTopFragment(): Fragment? {
        return if (nav_host_fragment.childFragmentManager.fragments.isNotEmpty()) {
            nav_host_fragment.childFragmentManager.fragments[0]
        } else null
    }

    override fun onInsetsReceived(top: Int, bottom: Int) {
        nav_view.setVerticalMargin(marginBottom = bottom)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)

        nav_view.setupWithNavController(navController)
    }
}
