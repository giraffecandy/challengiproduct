package com.lifeistech.l4s.challengeproduct

import android.R
import android.app.Activity
import android.content.Context
import android.net.sip.SipSession
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection


//class RecyclerViewAdapter(private val mactivity: Activity, private val item_count: Int) :
//    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder?>() {
//    }

    //ここでviewに対し変化を施す
//    override fun onBindViewHolder(
//        viewHolder: ViewHolder?,
//        i: Int
//    ) {
//        // データ表示
//        if (0 <= i && i <= 3) {
//            //viewHolder.title1.setText(test[i] + "さん");
//        } else if (4 <= i && i <= 7) { // creates second view
//            //viewHolder.title2.setText("hello");
//        } else { // creates third view
//            //viewHolder.title3.setText("test");
//        }
//    }


    /** */
//    inner class ViewHolder  /*TextView title1;
//        TextView title2;
//        TextView title3;*/
//        (view: View?, i: Int) : RecyclerView.ViewHolder(view!!)
//
//}

class RecyclerViewAdapter(
    private val context: Context,
    private val bookList: OrderedRealmCollection<Book>,
    private val autoUpdate: Boolean,
    private val listener: SipSession.Listener
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
//    override fun onCreateViewHolder(
//        viewGroup: ViewGroup
//    ): ViewHolder {
//    }



    val items: MutableList<BookData> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.titleTextView)
        val autherText: TextView = view.findViewById(R.id.autherTextView)
        val descriptionText: TextView = view.findViewById(R.id.descriptionTextView)
        val priceText: TextView = view.findViewById(R.id.priceTextView)
        val timeText: TextView = view.findViewById(R.id.timeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//setOnClickListenerはレイアウトを適用する場所を決める
//        val view: View
//        val viewHold: ViewHolder
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.layout1,  false)
        val viewHold: ViewHolder = ViewHolder(view)
        // クリックリスナを搭載
        view.setOnClickListener {
            val position = viewHold.adapterPosition // positionを取得
            Log.d("test", position.toString()) //何番目を押したか返す
        }

//        val view = LayoutInflater.from(context).inflate(R.layout.item_book_data_cell)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.titleText.text = item.title
        holder.autherText.text = item.auther
        holder.descriptionText.text = item.description
        holder.priceText.text = item.price
        holder.timeText.text = item.time
    }

    fun addAll(items: List<BookData>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}