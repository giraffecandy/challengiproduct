package com.lifeistech.l4s.challengeproduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val realm: Realm = Realm.getDefaultInstance()

    val bookData: List<BookData> = listOf()

    //onCreateアプリを開いたとき　onResume遷移から戻ってきたとき

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        class UpdateTime(){
            val newDate =
        }

        val book: Book? = read()

        if (book != null) {
            
        }

        val adapter = RecyclerViewAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        adapter.addAll(bookData)
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun read(): Book? {
        return realm.where(Book::class.java).findAll()
    }

    override fun onResume() {
        super.onResume()
        mContext = this

        val bookList: RealmResults<Book> =readAll()

        val adapter = RecyclerViewAdapter(this, bookList, true, object : RecyclerViewAdapter.Listener{
            override fun onClickRow(view: View, rowModel: Book){
                val intent = Intent(application, DetailActivity::class.java)
                startActivity(intent)
            }
        })

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this)
        recyclerView.adapter = adapter

        floatingActionButton.setOnClickListener {
            val intent = Intent(application, DetailActivity::class.java)
            startActivity(intent)
        }

        val results = realm.where(Book::class.java).findAll()
        val numberOfItems: MutableList<Book> = results.subList(0, bookList.size)
        Log.d("hoge", results.toString())
    }

    fun readAll(): RealmResults<Book>{
        return realm.where(Book::class.java).findAll()
            .sort("createdAt", Sort.ASCENDING)
    }
}
