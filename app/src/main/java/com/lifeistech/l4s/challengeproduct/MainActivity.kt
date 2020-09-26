package com.lifeistech.l4s.challengeproduct

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.math.abs
import kotlin.math.truncate


class MainActivity : AppCompatActivity() {

    private val realm: Realm = Realm.getDefaultInstance()
    private lateinit var mContext: Context;
//    val bookData: List<BookData> = listOf()

    //onCreateアプリを開いたとき　onResume遷移から戻ってきたとき

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val empty = realm.where(Book::class.java).findFirst()
        if (empty != null) {
            firstTextView.isVisible = false
        } else {
            firstTextView.isVisible = true
        }

//        val cl = Calendar.getInstance()
//        val nowDate = Calendar.YEAR
//        val month: Int = cl.get(Calendar.MONTH)
//        Log.d("date", month.toString())
//        firstTextView.setText(truncate(month + 1.0).toInt().toString())
//        println(truncate(1.1f))

//        Log.d("bookcontent", bookList.toString())
//        if (bookList != null) {
//            firstTextView.isVisible = false
//        } else {
//            firstTextView.isVisible = true
//        }

    }


    override fun onResume() {
        super.onResume()

        mContext = this
        val bookList: RealmResults<Book> = readAll()
        Log.d("content", bookList.toString())

//        if (bookList == null){
//            firstTextView.isVisible = true
//        } else {
//            firstTextView.isVisible = false
//        }


        val adapter = RecyclerViewAdapter(
            this,
            bookList,
            true,
            object : RecyclerViewAdapter.OnItemClickListener {
                override fun onItemClick(item: Book) {

                    val intent = Intent(application, DetailActivity::class.java)
                    intent.putExtra("GO_DETAIL", item.id)
                    startActivity(intent)
                }
            })

        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

//        adapter.addAll(bookList)

        floatingActionButton.setOnClickListener {
            val intent = Intent(application, EditActivity::class.java)
            startActivity(intent)
        }

        val results = realm.where(Book::class.java).findAll()
        val numberOfItems: MutableList<Book> = results.subList(0, bookList.size)
        Log.d("hoge", results.toString())


//        val saveDate = realm.where(Book::class.java).findFirst()
//        saveDate.year

    }

    private fun readAll(): RealmResults<Book> {
        return realm.where(Book::class.java).findAll().sort("createdAt", Sort.ASCENDING)
    }
//    override fun onDestroy() {
//        super.onDestroy()
//        realm.close()
//    }

//    fun time(book: Book): Int {
//        val calendar = Calendar.getInstance()
//        val yearLatest = calendar.get(Calendar.YEAR)
//        val monthLatest = calendar.get(Calendar.MONTH)
//        val dateLatest = calendar.get(Calendar.DATE)
//        val hourLatest = calendar.get(Calendar.HOUR)
//        val minLatest = calendar.get(Calendar.MINUTE)
//
//        val year = book.year
//        val month = book.month
//        val date = book.date
//        val hour = book.hour
//        val min = book.min
//
//        if (year != yearLatest) {
//            val result = yearLatest - year
//            return result
//
//        } else if (month != monthLatest) {
//
//            val result = monthLatest - month
//            return Math.abs(result)
//
//        } else if (date != dateLatest) {
//
//            val result = dateLatest - date
//            return Math.abs(result)
//
//        } else if (hour != hourLatest) {
//
//            val result = hourLatest - hour
//            return abs(result)
//        } else if (date != dateLatest) {
//            val calcResult = date - dateLatest
//            return Math.abs(calcResult)
//        }
//
//        return 0
//    }
}


