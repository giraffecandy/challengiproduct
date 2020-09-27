package com.lifeistech.l4s.challengeproduct

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {

    private val realm: Realm = Realm.getDefaultInstance()
    private lateinit var mContext: Context;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val BORDER_WEIGHT = 2


//// 白（半透明），太さ2pxのボーダーをつける
//        val borderDrawable = GradientDrawable()
//        borderDrawable.setStroke(BORDER_WEIGHT, 0x557e7b78)
//
//// LayerDrawableにボーダーを付けたDrawableをセット
//
//        val layerDrawable = LayerDrawable(arrayOf<Drawable>(borderDrawable))
//// ボーダーがいらない辺にオフセット（負値）をつける
//// ボーダーがいらない辺にオフセット（負値）をつける
//        layerDrawable.setLayerInset(0, 0, -BORDER_WEIGHT, -BORDER_WEIGHT, -BORDER_WEIGHT)
//
//// ボーダーを付けたいViewにセットする
//
//        view.setBackground(textView6)

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

        val titleForMessage = selectedData?.title

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
                .setTitle("本の削除")
                .setMessage("$titleForMessage　を削除しますか？")
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

