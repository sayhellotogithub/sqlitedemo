package com.iblogstreet.sqlitedemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iblogstreet.sqlitedemo.R
import com.iblogstreet.sqlitedemo.bean.EntryBean

/**
 * @author junwang
 * @date 2020/7/24 2:16 PM
 */
class FeedReaderAdapter(val context: Context, val list: MutableList<EntryBean>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var listener: IFeedReaderAdapterListener? = null

    class FeedReaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        var tvSubTitle: TextView = itemView.findViewById(R.id.tv_sub_title)
        var tvCreate: TextView = itemView.findViewById(R.id.tv_create)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedReaderViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_feed_reader, parent, false)
        return FeedReaderViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val bean = list[position]
        val viewHolder = holder as FeedReaderViewHolder

        viewHolder.tvTitle.text = bean.title
        viewHolder.tvSubTitle.text = bean.subtitle
        viewHolder.tvCreate.text = bean.createDate

        viewHolder.itemView.setOnClickListener {
            listener?.onItemClick(position)
        }

    }

   open interface IFeedReaderAdapterListener {
        fun onItemClick(position: Int)
    }
}