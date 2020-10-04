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
//    private val time:Book,
    private val autoUpdate: Boolean,
    private var listener: OnItemClickListener
//    private val book: Long
) : RealmRecyclerViewAdapter<Book, RecyclerViewAdapter.ViewHolder>(bookList, autoUpdate) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//setOnClickListenerはレイアウトを適用する場所を決める

        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.item_book_data_cell, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = bookList?.size ?: 0


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book: Book = bookList?.get(position) ?: return
        holder.bind(book, listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        private val autherTextView: TextView = view.findViewById(R.id.autherTextView)
        private val timeTextView: TextView = view.findViewById(R.id.timeTextView)
        private val container: LinearLayout = view.container

        fun bind(book: Book, listener: OnItemClickListener){
            titleTextView.text = book.title
            autherTextView.text = book.auther
            timeTextView.text = DateUtils.getRelativeTimeSpanString(
                book.createdAt.time, System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS
            ).toString()

            container.setOnClickListener {
                listener.onItemClick(book)
            }
        }
    }


    interface OnItemClickListener {
        fun onItemClick(item: Book)
    }

}