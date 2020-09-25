package com.lifeistech.l4s.challengeproduct

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_edit.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class EditActivity : AppCompatActivity() {

    private val realm: Realm = Realm.getDefaultInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)


        // Backボタンを有効にする
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        supportActionBar!!.setHomeButtonEnabled(true)
//        supportActionBar!!.setHomeActionContentDescription(R.drawable.ic_baseline_keyboard_backspace_24)
//
//        fun onBackPressed() {
//            super.onBackPressed()
//
//        }

        fun onSupportNavigateUp(): Boolean {
            onBackPressed()
            return true
        }

        val book:Book? = read()

        if (book != null) {
            titleEditText.setText(book.title)
            autherEditText.setText(book.auther)
            priceEditText.setText(book.price.toString())
            descriptionEditText.setText(book.description)
        }

//        val imageButton = backButton(this)
//        imageButton.setBackgroundDrawable(null)
        
        backButton.setOnClickListener {
            val title: String = titleEditText.text.toString()
            val auther: String = autherEditText.text.toString()
            val description: String = descriptionEditText.text.toString()
            val price: String = priceEditText.text.toString()
            val getTime = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

            save(title, auther, description, price, getTime)

            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        addButton.setOnClickListener{
//            if(realm.where(Book::class.java).equalTo("id", id) == null)
            val title: String = titleEditText.text.toString()
            val auther: String = autherEditText.text.toString()
            val description: String = descriptionEditText.text.toString()
            val price: String = priceEditText.text.toString()
            val getTime = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

            save(title, auther, description, price, getTime)

            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        when (id) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun read(): Book?{
        return realm.where(Book::class.java).findFirst()
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

