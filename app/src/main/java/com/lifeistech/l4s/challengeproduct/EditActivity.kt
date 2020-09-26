package com.lifeistech.l4s.challengeproduct

import android.content.Intent
import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_edit.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class EditActivity : AppCompatActivity() {

    private val realm: Realm = Realm.getDefaultInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)


        val book: Book? = read()


        val acceptData:String? = intent.getStringExtra("GO_EDIT")

        Log.d("accept", acceptData.toString())
//    var selectedData = realm.where(Book::class.java).equalTo("id", acceptData).findFirst()


//        Log.d("selecte", selectedData.toString())
        if (acceptData != null) {
            var getId: Book? =
                realm.where(Book::class.java).equalTo("id", acceptData).findFirst()
            Log.d("data", getId.toString())

            if (getId != null) {
                titleEditTextView.text = getId.title
                if (titleEditTextView == null){
                titleEditTextView.text = ""
                }
                if (autherEditTextView != null){
                    autherEditTextView.text = getId.auther
                }
                if (priceEditTextView != null){
                    priceEditTextView.setText(getId.price.toString())
                }
                if (descriptionEditTextView != null){
                descriptionEditTextView.setText(getId.description)
                }
            }
//                else {

//                titleEditText.text = null
//                autherEditText.text = null
//                priceEditText.text = null
//                descriptionEditText.text = null
//
//        }
    }

//            titleEditText.setText(book?.title)
//            autherEditText.setText(book?.auther)
//            priceEditText.setText(book?.price.toString())
//            descriptionEditText.setText(book?.description)


//        val imageButton = backButton(this)
//        imageButton.setBackgroundDrawable(null)

                backButton.setOnClickListener {
//            val title: String = titleEditText.text.toString()
//            val auther: String = autherEditText.text.toString()
//            val description: String = descriptionEditText.text.toString()
//            val price: String = priceEditText.text.toString()
//            val getTime = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
//
//            save(title, auther, description, price, getTime)

                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }

                addButton.setOnClickListener {
//            if(realm.where(Book::class.java).equalTo("id", id) == null)
                    val title: String = titleEditText.text.toString()
                    val auther: String = autherEditText.text.toString()
                    val description: String = descriptionEditText.text.toString()
                    val price: String = priceEditText.text.toString()
                    val getTime = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
//                    val sdf =
//                        SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'")
//                    sdf.timeZone = TimeZone.getTimeZone("Asia/Tokyo")

//                    try {
//                        val time = sdf.parse("2016-01-24T16:00:00.000Z").time
//                        val now = System.currentTimeMillis()
//                        val ago = DateUtils.getRelativeTimeSpanString(
//                            time,
//                            now,
//                            DateUtils.MINUTE_IN_MILLIS
//                        )
//                    } catch (e: ParseException) {
//                        e.printStackTrace()
//                    }

//                    val date: Date = getTime.parse(dateStr)
//                    val niceDateStr: String = DateUtils.getRelativeTimeSpanString(
//                        date.time,
//                        Calendar.getInstance().timeInMillis,
//                        DateUtils.MINUTE_IN_MILLIS
//                    ) as String

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

            private fun read(): Book? {
                return realm.where(Book::class.java).findFirst()
            }

            fun save(
                title: String,
                auther: String,
                description: String,
                price: String,
                getTime: SimpleDateFormat
//                sdf: SimpleDateFormat
            ) {
                realm.executeTransaction {

                    val book = it.createObject(Book::class.java, UUID.randomUUID().toString())
                    book.title = title
                    book.auther = auther
                    book.description = description
                    book.price = price.toInt()
//                    book.time = sdf.toInt()
//                    book.time = getTime.toInt()
                }
            }


        }

