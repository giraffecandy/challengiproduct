package com.lifeistech.l4s.challengeproduct

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_edit.*
import java.util.*


class EditActivity : AppCompatActivity() {

    private val realm: Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

//        val book: Book? = read()

        val acceptData: String = intent.getStringExtra("GO_EDIT").toString()

        var getId: Book? = realm.where(Book::class.java).equalTo("id", acceptData).findFirst()

        Log.d("accept", acceptData)

        titleEditText.setText(getId?.title)
        autherEditText.setText(getId?.author)
        priceEditText.setText(getId?.price.toString())
        descriptionEditText.setText(getId?.description)


        Log.d("accept", acceptData.toString())

        titleEditText.setText(getId?.title)
        autherEditText.setText(getId?.author)
        priceEditText.setText(getId?.price.toString())
        descriptionEditText.setText(getId?.description)

        backButton.setOnClickListener {

            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

            addButton.setOnClickListener {


                if (getId == null) {
                    val title: String = titleEditText.text.toString()
                    val auther: String = autherEditText.text.toString()
                    val description: String = descriptionEditText.text.toString()
                    val price: String = priceEditText.text.toString()

                    save(title, auther, description, price)
                } else {
                    realm.executeTransaction {
                        var title: String = titleEditText.text.toString()
                        var auther: String = autherEditText.text.toString()
                        var description: String = descriptionEditText.text.toString()
                        var price: String = priceEditText.text.toString()

                        getId.title = title
                        getId.author = auther
                        getId.description = description
                        getId.price = price.toInt()

                    }
                }

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

    private fun read(): Book? {
        return realm.where(Book::class.java).findFirst()
    }

    private fun save(
        title: String,
        author: String,
        description: String,
        price: String
    ) {
        realm.executeTransaction {

            val book = it.createObject(Book::class.java, UUID.randomUUID().toString())
            book.title = title
            book.author = author
            book.description = description
            book.price = price.toInt()

        }
    }

}

