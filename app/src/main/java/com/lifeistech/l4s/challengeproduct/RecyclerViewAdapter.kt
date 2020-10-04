package com.lifeistech.l4s.challengeproduct

import android.content.Context
import android.text.format.DateUtils
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
    private val time:Book,
    private val autoUpdate: Boolean,
    private var listener: OnItemClickListener
//    private val book: Long
) : RealmRecyclerViewAdapter<Book, RecyclerViewAdapter.ViewHolder>(bookList, autoUpdate) {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val autherTextView: TextView = view.findViewById(R.id.autherTextView)
        val timeTextView: TextView = view.findViewById(R.id.timeTextView)
        val container: LinearLayout = view.container

        fun bind(book: Book){
            titleTextView.text = view.title
            holder.autherTextView.text = item.auther
            holder.timeTextView.text = DateUtils.getRelativeTimeSpanString(
                book.createdAt.time, System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS
            ).toString()
        }

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
        holder.bind(book)


        holder.container.setOnClickListener {
            listener.onItemClick(item)
        }

    }

    interface OnItemClickListener {
        fun onItemClick(item: Book)
    }

}