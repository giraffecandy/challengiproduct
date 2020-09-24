package com.lifeistech.l4s.challengeproduct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.read
import android.widget.ArrayAdapter
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_book_data_cell.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.temporal.ChronoUnit
import java.util.*


class DetailActivity : AppCompatActivity() {
    private val realm: Realm = Realm.getDefaultInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val book:Book? = null

        if (book != null) {
            titleEditText.setText(book.title)

        }

        addButton.setOnClickListener{
            val title: String = titleEditText.text.toString()
            val auther: String = autherEditText.text.toString()
            val description: String = descriptionEditText.text.toString()
            val price: String = priceEditText.text.toString()
            val getTime = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

            save(title, auther, description, price, getTime)
        }
    }

    fun save(title: String, auther: String, description: String, price: String, getTime:DateFormat){
        realm.executeTransaction {

            val book = it.createObject(Book::class.java, UUID.randomUUID().toString())
            book.title = title
            book.auther = auther
            book.description = description
            book.price = price.toInt()
            //book.getTime = getTime.toString()
        }
    }
}