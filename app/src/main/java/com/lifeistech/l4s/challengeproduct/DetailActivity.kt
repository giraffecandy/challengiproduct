package com.lifeistech.l4s.challengeproduct

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.internal.ContextUtils.getActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {

    private val realm: Realm = Realm.getDefaultInstance()
    private lateinit var mContext: Context;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mContext = this

        returnButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val acceptData = intent.getStringExtra("GO_DETAIL")

        var selectedData = realm.where(Book::class.java).equalTo("id", acceptData).findFirst()
Log.d("selectd", selectedData.toString())
            titleEditTextView.text = selectedData?.title
            autherEditTextView.text = selectedData?.auther
            priceEditTextView.text = selectedData?.price.toString()
            descriptionEditTextView.text = selectedData?.description
        val getId = selectedData?.id

        editButton.setOnClickListener {

//            fun editOnItemClick(item: Book) {
                val intent = Intent(this, EditActivity::class.java)
                intent.putExtra("GO_EDIT", getId)
                startActivity(intent)
                Log.d("edit", selectedData.toString())
//            }
        }

        deleteButton.setOnClickListener {

            AlertDialog.Builder(mContext)
                .setTitle("title")
                .setMessage("message")
                .setPositiveButton("OK",
                    DialogInterface.OnClickListener { dialog, which ->
                        // OK button pressed
                        realm.executeTransaction {
                            selectedData?.deleteFromRealm()
                        }
                        finish()
                    })
                .setNegativeButton("Cancel", null)
                .show()


        }
    }
}

