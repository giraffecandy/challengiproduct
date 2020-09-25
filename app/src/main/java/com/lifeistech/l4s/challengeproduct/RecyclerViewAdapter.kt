package com.lifeistech.l4s.challengeproduct

import android.app.Activity
import android.content.Context
import android.net.sip.SipSession
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter


class RecyclerViewAdapter(
    private val context: Context,
    private var bookList: OrderedRealmCollection<Book>,
    private val autoUpdate: Boolean,
    private val listener: OnItemClickListener
) : RealmRecyclerViewAdapter<Book, RecyclerViewAdapter.ViewHolder>(bookList, autoUpdate) {
//    override fun onCreateViewHolder(
//        viewGroup: ViewGroup
//    ): ViewHolder {
//    }

        interface OnItemClickListener{
            fun onItemClick(item: Book)
        }


//    val items: MutableList<BookData> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val autherTextView: TextView = view.findViewById(R.id.autherTextView)
//        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTex)
//        val priceTextView: TextView = view.findViewById(R.id.priceEditText)
        val timeTextView: TextView = view.findViewById(R.id.timeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//setOnClickListenerはレイアウトを適用する場所を決める
//        val view: View
//        val viewHold: ViewHolder
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.item_book_data_cell, parent, false)
        val viewHold: ViewHolder = ViewHolder(view)
        // クリックリスナを搭載
        view.setOnClickListener {
            val position = viewHold.adapterPosition // positionを取得
            Log.d("test", position.toString()) //何番目を押したか返す
        }

//        val view = LayoutInflater.from(context).inflate(R.layout.item_book_data_cell)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (bookList[position].isValid) {
            val item = bookList[position]
            holder.titleTextView.text = item.title
            holder.autherTextView.text = item.auther
//            holder.descriptionTextView.text = item.description
//            holder.priceTextView.text = item.price.toString()
            holder.timeTextView.text = item.time.toString()
        }

//    fun addAll(items: List<BookData>) {
//        this.items.addAll(items)
//        notifyDataSetChanged()
//    }
    }}