package com.lifeistech.l4s.challengeproduct

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private val realm: Realm = Realm.getDefaultInstance()
    private lateinit var mContext: Context;

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

    }


    override fun onResume() {
        super.onResume()

        mContext = this
        val bookList: RealmResults<Book> = readAll()
        Log.d("content", bookList.toString())

        val AVERAGE_MONTH_IN_MILLIS:Long= DateUtils.DAY_IN_MILLIS * 30

        fun times(time: Long): String {
            val now: Long = Date().getTime()
            val delta: Long = now - time
            val resolution: Long
            if (delta <= DateUtils.MINUTE_IN_MILLIS) {
                resolution = DateUtils.SECOND_IN_MILLIS;
                Log.d("re", resolution.toString())
            } else if (delta <= DateUtils.HOUR_IN_MILLIS) {
                resolution = DateUtils.MINUTE_IN_MILLIS;
            } else if (delta <= DateUtils.DAY_IN_MILLIS) {
                resolution = DateUtils.HOUR_IN_MILLIS;
            } else if (delta <= DateUtils.WEEK_IN_MILLIS) {
                resolution = DateUtils.DAY_IN_MILLIS;
            } else if (delta <= AVERAGE_MONTH_IN_MILLIS) {
                val calc = delta / DateUtils.WEEK_IN_MILLIS
                val Integer: String =" $calc 週間前"
                return Integer
            } else if (delta <= DateUtils.YEAR_IN_MILLIS) {
                val calc = delta / AVERAGE_MONTH_IN_MILLIS
                val Integer: String = "$calc　か月前"
                return Integer
            } else {
                val calc = delta / DateUtils.YEAR_IN_MILLIS
                val Integer: String = "$calc　年前"
                return Integer
            }
            return DateUtils.getRelativeTimeSpanString(time, now, resolution).toString();
        }

        val items: RealmResults<Book> = realm.where(Book::class.java).findAll()
        for (i in items) {
            var time = times(i.createdAt.time)

            val adapter = RecyclerViewAdapter(
                this,
                bookList,
                time,
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
        }

        floatingActionButton.setOnClickListener {
            val intent = Intent(application, EditActivity::class.java)
            startActivity(intent)
        }
    }

    private fun readAll(): RealmResults<Book> {
        return realm.where(Book::class.java).findAll().sort("createdAt", Sort.ASCENDING)
    }
}


