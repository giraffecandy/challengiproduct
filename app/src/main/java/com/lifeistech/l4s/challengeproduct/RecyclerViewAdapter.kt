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
import io.realm.Realm
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.item_book_data_cell.view.*
import java.util.*
import kotlin.math.abs


class RecyclerViewAdapter(
    private val context: Context,
    private var bookList: OrderedRealmCollection<Book>,
    private var time:String,
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

//        val results = realm.where(Book::class.java).findAll()
//        val numberOfItems: MutableList<Book> = results.subList(0, bookList.size)
//        Log.d("hoge1", numberOfItems.toString())

//        val calcTime = time(numberOfItems.get(0))
//        val calculateTime = numberOfItems.calcTime

        holder.titleTextView.text = item.title
        holder.autherTextView.text = item.auther
//            holder.descriptionTextView.text = item.description
//            holder.priceTextView.text = item.price.toString()
        holder.timeTextView.text = time

    }

    interface OnItemClickListener {
        fun onItemClick(item: Book)
    }

    fun time(book: Book): String {
        val calendar = Calendar.getInstance()
        val yearLatest = calendar.get(Calendar.YEAR)
        val monthLatest = calendar.get(Calendar.MONTH)
        val dateLatest = calendar.get(Calendar.DATE)
        val hourLatest = calendar.get(Calendar.HOUR)
        val minLatest = calendar.get(Calendar.MINUTE)

        val year = book.year
        val month = book.month
        val date = book.date
        val hour = book.hour
        val min = book.min

        if (year != yearLatest) {
            val result = yearLatest - year
            val display = "$result + 年前"
            return display

        } else if (month != monthLatest) {

            val result = monthLatest - month
            val re = Math.abs(result)
            val display = "$re + 月前"
            return display

        } else if (date != dateLatest) {

            val result = dateLatest - date
            val re = Math.abs(result)
            return "$re + 日前"

        } else if (hour != hourLatest) {

            val result = hourLatest - hour
            val re = abs(result)
            return "$re + 時間前"

        } else if (date != dateLatest) {
            val calcResult = date - dateLatest
            val re = Math.abs(calcResult)
            return "$re + 分前"
        }

        return "0分前"
    }

}