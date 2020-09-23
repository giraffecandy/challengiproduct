package com.lifeistech.l4s.challengeproduct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.read
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.temporal.ChronoUnit

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

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
        val book:Book? = read()

        realm.executeTramsaction{
            if (book != null) {

            }
        }
    }
}