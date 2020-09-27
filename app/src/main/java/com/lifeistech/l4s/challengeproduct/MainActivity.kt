package com.lifeistech.l4s.challengeproduct

import android.content.ClipData.Item
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


//        val time = "hello"

//        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//        sdf.setTimeZone(TimeZone.getTimeZone("GMT+9"))
//        try {
//            val time: Long = sdf.parse("2016-01-24T16:00:00.000Z").getTime()
//            val now = System.currentTimeMillis()
//            val ago =
//                DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)
//        } catch (e: ParseException) {
//            e.printStackTrace()
//        }

        val AVERAGE_MONTH_IN_MILLIS:Long= DateUtils.DAY_IN_MILLIS * 30

        fun timeidk(time: Long): String {
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

//        class Se (book: Book){
//            val search = book.id
//        }

        val items: RealmResults<Book> =
            realm.where(Book::class.java).findAll()
        for (i in items) {
//            addText(i.getId().toString() + ":" + i.getName())
            val time = timeidk(i.createdAt.time)


//        fun main() {
//            val dhu: Se = Se()
//            var gettimeid = realm.where(Book::class.java).equalTo("id", id).findAll()
//            var getTimeR = realm.where(Book::class.java).e
//            val time = timeidk(dhu.Se)
//        }
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


