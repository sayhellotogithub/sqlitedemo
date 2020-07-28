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
import com.iblogstreet.sqlitedemo.bean.EntryBean
import com.iblogstreet.sqlitedemo.bean.FeedReaderBean
import com.iblogstreet.sqlitedemo.util.DBManager
import com.iblogstreet.sqlitedemo.util.DbUtil

/**
 * @author junwang
 * @date 2020/7/24 3:34 PM
 */
class SearchFeedReaderActivity : AppCompatActivity() {
    private lateinit var etTitle: EditText
    private lateinit var rv: RecyclerView
    private lateinit var dbManager: DBManager
    private var feedReaderList = mutableListOf<EntryBean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_feedreader)
        initView()
        initRecycler()
        initEvent()
        initData()
    }

    private fun initData() {
        dbManager = DBManager(this)
        getData()
    }

    private fun getData() {
        feedReaderList.addAll(DbUtil.readAllData(dbManager.getDb()))
        rv.adapter?.notifyDataSetChanged()
    }

    private fun initRecycler() {
        val linearLayoutManager = LinearLayoutManager(this)

        rv.layoutManager = linearLayoutManager
        val adpater = FeedReaderAdapter(this, feedReaderList)
        rv.adapter = adpater
        adpater.listener = object : FeedReaderAdapter.IFeedReaderAdapterListener {
            override fun onItemClick(position: Int) {

            }
        }

    }

    private fun initView() {
        etTitle = findViewById(R.id.et_title)
        rv = findViewById(R.id.rv)
    }

    private fun initEvent() {
        findViewById<TextView>(R.id.tv_search).setOnClickListener {
            val list = DbUtil.readData(dbManager.getDb(), etTitle.text.toString())
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

    override fun onDestroy() {
        dbManager.close()
        super.onDestroy()
    }
}