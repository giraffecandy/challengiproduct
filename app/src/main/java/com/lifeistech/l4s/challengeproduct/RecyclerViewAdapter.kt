package com.lifeistech.l4s.challengeproduct

import android.content.Context
import android.util.Log
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
    private val autoUpdate: Boolean,
    private var listener: OnItemClickListener
) : RealmRecyclerViewAdapter<Book, RecyclerViewAdapter.ViewHolder>(bookList, autoUpdate) {
//    override fun onCreateViewHolder(
//        viewGroup: ViewGroup
//    ): ViewHolder {
//    }


//    val items: MutableList<BookData> = mutableListOf()

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val autherTextView: TextView = view.findViewById(R.id.autherTextView)

        //        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTex)
//        val priceTextView: TextView = view.findViewById(R.id.priceEditText)
        val timeTextView: TextView = view.findViewById(R.id.timeTextView)
        val container: LinearLayout = view.container
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//setOnClickListenerはレイアウトを適用する場所を決める
//        val view: View
//        val viewHold: ViewHolder
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.item_book_data_cell, parent, false)
//        val viewHold: ViewHolder = ViewHolder(view)

        return ViewHolder(view)
        // クリックリスナを搭載
//        view.setOnClickListener {
//            val position = viewHolder.adapterPosition // positionを取得
//            Log.d("test", position.toString()) //何番目を押したか返す
//        }

//        val view = LayoutInflater.from(context).inflate(R.layout.item_book_data_cell)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = bookList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        if (bookList[position].isValid) {
        val item: Book = bookList?.get(position) ?: return

        holder.container.setOnClickListener {
            listener.onItemClick(item)
        }

        holder.titleTextView.text = item.title
        holder.autherTextView.text = item.auther
//            holder.descriptionTextView.text = item.description
//            holder.priceTextView.text = item.price.toString()
        holder.timeTextView.text = item.time.toString()

    }

    interface OnItemClickListener {
        fun onItemClick(item: Book)
    }
}