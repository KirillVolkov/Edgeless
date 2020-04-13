package io.volkov.edgeless.cases.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import io.volkov.edgeless.R
import io.volkov.edgeless.SupportFragmentInset
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : SupportFragmentInset() {

    private lateinit var listViewModel: ListViewModel

    override fun onInsetsReceived(top: Int, bottom: Int) {
        rv.setPadding(0, top, 0, 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listViewModel =
            ViewModelProviders.of(this).get(ListViewModel::class.java)
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv.adapter = NatureAdapter {
            startActivity(Intent(context, CollapseActivity::class.java))
        }
    }
}