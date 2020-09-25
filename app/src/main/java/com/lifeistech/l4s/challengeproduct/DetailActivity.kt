package com.lifeistech.l4s.challengeproduct

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private val realm: Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val acceptData = intent.getStringExtra("GO_DETAIL")

        var selectedData = realm.where(Book::class.java).equalTo("id", acceptData).findFirst()

        titleEditTextView.text = selectedData?.title
        autherEditTextView.text = selectedData?.auther
        priceEditTextView.text = selectedData?.price.toString()
        descriptionEditTextView.text = selectedData?.description

        editButton.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra("GO_EDIT", acceptData)
            startActivity(intent)
        }

        deleteButton.setOnClickListener {
            realm.executeTransaction {
                selectedData?.deleteFromRealm()
            }

        }
        finish()
    }
}

