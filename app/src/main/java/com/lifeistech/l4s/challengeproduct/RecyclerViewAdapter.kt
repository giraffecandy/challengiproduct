package com.lifeistech.l4s.challengeproduct

import android.app.Activity
import android.content.Context
import android.net.sip.SipSession
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter


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
    private var bookList: OrderedRealmCollection<Book>,
    private val autoUpdate: Boolean,
    private val listener: OnItemClickListener
) : RealmRecyclerViewAdapter<Book, RecyclerViewAdapter.ViewHolder>(bookList, autoUpdate) {
//    override fun onCreateViewHolder(
//        viewGroup: ViewGroup
//    ): ViewHolder {
//    }

        interface OnItemClickListener{
            fun onItemClick(item: Book)
        }


//    val items: MutableList<BookData> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.titleEditText)
        val autherText: TextView = view.findViewById(R.id.autherEditText)
        val descriptionText: TextView = view.findViewById(R.id.descriptionEditText)
        val priceText: TextView = view.findViewById(R.id.priceEditText)
        val timeText: TextView = view.findViewById(R.id.timeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//setOnClickListenerはレイアウトを適用する場所を決める
//        val view: View
//        val viewHold: ViewHolder
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.layout1, parent, false)
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
        return bookList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (bookList.get(position).isValid()) {
            val item = bookList[position]
            holder.titleText.text = item.title
            holder.autherText.text = item.auther
            holder.descriptionText.text = item.description
            holder.priceText.text = item.price.toString()
            holder.timeText.text = item.time.toString()
        }

//    fun addAll(items: List<BookData>) {
//        this.items.addAll(items)
//        notifyDataSetChanged()
//    }
    }}