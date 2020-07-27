package com.iblogstreet.sqlitedemo.view

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iblogstreet.sqlitedemo.R
import com.iblogstreet.sqlitedemo.adapter.FeedReaderAdapter
import com.iblogstreet.sqlitedemo.bean.FeedReaderBean
import com.iblogstreet.sqlitedemo.util.DbUtil

/**
 * @author junwang
 * @date 2020/7/27 10:04 AM
 */
class DeleteFeedReaderActivity : AppCompatActivity() {
    private lateinit var etTitle: EditText
    private lateinit var rv: RecyclerView
    private var feedReaderList = mutableListOf<FeedReaderBean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_feedreader)
        initView()
        initEvent()

        initRecycler()
        initData()
    }

    private fun initData() {
        getData()
    }

    private fun getData() {
        feedReaderList.addAll(DbUtil.readAllData(this))
        rv.adapter?.notifyDataSetChanged()
    }

    private fun initRecycler() {
        val linearLayoutManager = LinearLayoutManager(this)

        rv.layoutManager = linearLayoutManager
        val adpater = FeedReaderAdapter(this, feedReaderList)
        rv.adapter = adpater
        adpater.listener = object : FeedReaderAdapter.IFeedReaderAdapterListener {
            override fun onItemClick(position: Int) {
                if (DbUtil.deleteDataById(
                        this@DeleteFeedReaderActivity,
                        feedReaderList[position].id
                    )
                ) {
                    Toast.makeText(this@DeleteFeedReaderActivity, "success", Toast.LENGTH_SHORT)
                        .show()
                    feedReaderList.removeAt(position)
                    rv.adapter?.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@DeleteFeedReaderActivity, "fail", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun initView() {
        etTitle = findViewById(R.id.et_title)
        rv = findViewById(R.id.rv)
    }

    private fun initEvent() {
        findViewById<TextView>(R.id.tv_search).setOnClickListener {
            val list = DbUtil.readData(this, etTitle.text.toString())
            feedReaderList.clear()
            if (list.size > 0) {
                feedReaderList.addAll(list)
                Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show()
            }
            rv.adapter?.notifyDataSetChanged()
        }
    }
}