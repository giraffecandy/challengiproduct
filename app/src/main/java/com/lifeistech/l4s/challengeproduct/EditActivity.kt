package com.lifeistech.l4s.challengeproduct

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_edit.*
import java.util.*


class EditActivity : AppCompatActivity() {

    private val realm: Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)


        val book: Book? = read()

        val acceptData: String = intent.getStringExtra("GO_EDIT").toString()

        var getId: Book? = realm.where(Book::class.java).equalTo("id", acceptData).findFirst()

        Log.d("accept", acceptData.toString())

        titleEditText.setText(getId?.title)
        autherEditText.setText(getId?.auther)
        priceEditText.setText(getId?.price.toString())
        descriptionEditText.setText(getId?.description)

//        addButton.setOnClickListener {
//
//            realm.commitTransaction()
//        }
//            var getId: Book? = realm.where(Book::class.java).equalTo("id", acceptData).findFirst()
//        if (getId == null){
//
//        }


        Log.d("accept", acceptData.toString())
//    var selectedData = realm.where(Book::class.java).equalTo("id", acceptData).findFirst()


//        Log.d("selecte", selectedData.toString())
//            if (acceptData != null) {
//            Log.d("data", getId.toString())
//            if (getId != null) {
        titleEditText.setText(getId?.title)
        autherEditText.setText(getId?.auther)
        priceEditText.setText(getId?.price.toString())
        descriptionEditText.setText(getId?.description)

//        addButton.setOnClickListener {
//
//            realm.commitTransaction()
//        }
//                }
//            }


        backButton.setOnClickListener {

            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

//        Log.d("number", numberOfItems.toString())
//        val calcTime = time(numberOfItems.get()))
//        Log.d("hoge1", calcTime

            addButton.setOnClickListener {


                if (getId == null) {
                    val title: String = titleEditText.text.toString()
                    val auther: String = autherEditText.text.toString()
                    val description: String = descriptionEditText.text.toString()
                    val price: String = priceEditText.text.toString()
//                    val calendar = Calendar.getInstance()
//                    val year = calendar.get(Calendar.YEAR)
//                    val month: Int = calendar.get(Calendar.MONTH)
//                    val date = calendar.get(Calendar.DATE)
//                    val hour = calendar.get(Calendar.HOUR_OF_DAY)
//                    val min = calendar.get(Calendar.MINUTE)
//                val time = eachesss()
//                Log.d("date", month.toString())
//                firstTextView.setText(truncate(month + 1.0).toInt().toString())


                    save(title, auther, description, price)
                } else {
                    realm.executeTransaction {
                        var title: String = titleEditText.text.toString()
                        var auther: String = autherEditText.text.toString()
                        var description: String = descriptionEditText.text.toString()
                        var price: String = priceEditText.text.toString()

                        getId.title = title
                        getId.auther = auther
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

    fun save(
        title: String,
        auther: String,
        description: String,
        price: String
//        year: Int,
//        month: Int,
//        date: Int,
//        hour: Int,
//        min: Int
//        time: String,
    ) {
        realm.executeTransaction {

            val book = it.createObject(Book::class.java, UUID.randomUUID().toString())
            book.title = title
            book.auther = auther
            book.description = description
            book.price = price.toInt()
//            book.year = year
//            book.month = month
//            book.date = date
//            book.hour = hour
//            book.min = min
//            book.time = time.toString()
        }
    }

}

