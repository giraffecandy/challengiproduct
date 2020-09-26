package com.lifeistech.l4s.challengeproduct

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val realm: Realm = Realm.getDefaultInstance()
    private lateinit var mContext: Context;
//    val bookData: List<BookData> = listOf()

    //onCreateアプリを開いたとき　onResume遷移から戻ってきたとき

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



    override fun onResume() {
        super.onResume()

//                val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
//        recyclerView.setHasFixedSize(true) //viewの大きさが固定の場合，処理パフォーマンスが向上する
//
//        /*****************************************重要*****************************************************/
//        /*****************************************重要 */
//        val gridLayoutManagerVertical =
//            GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false)
//        gridLayoutManagerVertical.spanSizeLookup = Custom_Spansize(4, 2, 1)
//        recyclerView.layoutManager = gridLayoutManagerVertical
//        /*************************************************************************************************/
//        /** */
//        val adapter2 = RecyclerViewAdapter(this) //20個itemを作る


        mContext = this

        val bookList: RealmResults<Book> = readAll()

        if (bookList == null){
            firstTextView.isVisible = true
        } else {
            firstTextView.isVisible = false
        }


        val adapter = RecyclerViewAdapter(this, bookList,true, object : RecyclerViewAdapter.OnItemClickListener{
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
//        Log.d("hoge", results.toString())

    }

    private fun readAll(): RealmResults<Book> {
        return realm.where(Book::class.java).findAll().sort("createdAt", Sort.ASCENDING)
    }
//    override fun onDestroy() {
//        super.onDestroy()
//        realm.close()
//    }


}
