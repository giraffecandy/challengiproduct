package com.lifeistech.l4s.challengeproduct

import android.content.Context
import android.net.sip.SipSession
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection


class RecyclerViewAdapter(
    private val context: Context,
    private val bookList: OrderedRealmCollection<Book>,
    private val autoUpdate: Boolean,
    private val listener: SipSession.Listener
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    val items: MutableList<BookData> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.titleEditText)
        val autherText: TextView = view.findViewById(R.id.autherEditText)
        val descriptionText: TextView = view.findViewById(R.id.descriptionEditText)
        val priceText: TextView = view.findViewById(R.id.priceEditText)
        val timeText: TextView = view.findViewById(R.id.timeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_book_data_cell, parent)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.titleText.text = item.title
        holder.autherText.text = item.auther
        holder.descriptionText.text = item.description
        holder.priceText.text = item.price
        holder.timeText.text = item.time
    }

    fun addAll(items: List<BookData>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    interface Listener{
        fun onClickRow(view: View, rowModel:Book)
    }
}
