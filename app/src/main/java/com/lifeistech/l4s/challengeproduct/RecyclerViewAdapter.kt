package com.lifeistech.l4s.challengeproduct

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.item_book_data_cell.view.*


class RecyclerViewAdapter(
    private val context: Context,
    private var bookList: OrderedRealmCollection<Book>,
    private var time:String,
    private val autoUpdate: Boolean,
    private var listener: OnItemClickListener
) : RealmRecyclerViewAdapter<Book, RecyclerViewAdapter.ViewHolder>(bookList, autoUpdate) {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val autherTextView: TextView = view.findViewById(R.id.autherTextView)

        val timeTextView: TextView = view.findViewById(R.id.timeTextView)
        val container: LinearLayout = view.container

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//setOnClickListenerはレイアウトを適用する場所を決める

        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.item_book_data_cell, parent, false)

        return ViewHolder(view)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = bookList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Book = bookList?.get(position) ?: return

        holder.container.setOnClickListener {
            listener.onItemClick(item)
        }

        holder.titleTextView.text = item.title
        holder.autherTextView.text = item.auther
        holder.timeTextView.text = time

    }

    interface OnItemClickListener {
        fun onItemClick(item: Book)
    }

}