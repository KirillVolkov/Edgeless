package io.volkov.edgeless.cases.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import io.volkov.edgeless.*
import kotlinx.android.synthetic.main.fragment_collapsing.*

class CollapseActivity : AppCompatActivity(), OnSystemBarsSizeChangedListener {

    override val listener = MutableLiveData<VerticalInset>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_collapsing)

        observeLiveDataSafe(listener) { verticalInset ->
            toolbar.setVerticalMargin(marginTop = verticalInset.top)
            text.setPadding(0, 0, 0, verticalInset.bottom * 3 / 2)
        }

        overrideSystemInsets(listener)
    }

}