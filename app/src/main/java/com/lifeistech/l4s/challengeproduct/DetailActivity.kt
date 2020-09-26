package com.lifeistech.l4s.challengeproduct

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private val realm: Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        returnButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val acceptData = intent.getStringExtra("GO_DETAIL")

        var selectedData = realm.where(Book::class.java).equalTo("id", acceptData).findFirst()

            titleEditTextView.text = selectedData?.title
            autherEditTextView.text = selectedData?.auther
            priceEditTextView.text = selectedData?.price.toString()
            descriptionEditTextView.text = selectedData?.description

        editButton.setOnClickListener {

//            fun editOnItemClick(item: Book) {
                val intent = Intent(this, EditActivity::class.java)
                intent.putExtra("GO_EDIT", selectedData.toString())
                startActivity(intent)
                Log.d("edit", selectedData.toString())
//            }
        }

        deleteButton.setOnClickListener {
            realm.executeTransaction {
                selectedData?.deleteFromRealm()
            }
            finish()
        }
    }
}

