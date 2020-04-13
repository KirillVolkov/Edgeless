package io.volkov.edgeless.cases.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.volkov.edgeless.R
import kotlinx.android.synthetic.main.list_item.view.*

class NatureAdapter(private val onClick: () -> Unit) : RecyclerView.Adapter<NatureViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NatureViewHolder =
        NatureViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false),
            onClick
        )

    override fun getItemCount(): Int = 20

    override fun onBindViewHolder(holder: NatureViewHolder, position: Int) {
        holder.bind()
    }
}

class NatureViewHolder(view: View, private val onClick: () -> Unit) :
    RecyclerView.ViewHolder(view) {

    init {
        itemView.setOnClickListener {
            onClick()
        }
    }

    fun bind() {
        if (adapterPosition % 2 == 0) {
            itemView.ivImage.setImageResource(R.drawable.nature)
        } else {
            itemView.ivImage.setImageResource(R.drawable.nature2)
        }
    }
}
