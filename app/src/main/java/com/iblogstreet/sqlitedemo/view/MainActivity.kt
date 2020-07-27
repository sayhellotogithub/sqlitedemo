package com.iblogstreet.sqlitedemo.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iblogstreet.sqlitedemo.R
import com.iblogstreet.sqlitedemo.adapter.FeedReaderAdapter
import com.iblogstreet.sqlitedemo.bean.FeedReaderBean
import com.iblogstreet.sqlitedemo.util.DBManager
import com.iblogstreet.sqlitedemo.util.DbUtil

/**
 * @author junwang
 * @date 2020/7/23 5:15 PM
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var rv: RecyclerView
    private var feedReaderList = mutableListOf<FeedReaderBean>()
    private lateinit var dbManager: DBManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbManager = DBManager(this)
        initView()
        initRecycler()
        initEvent()
        getData()
    }

    private fun initView() {
        rv = findViewById(R.id.rv)
    }

    private fun initRecycler() {
        val linearLayoutManager = LinearLayoutManager(this)

        rv.layoutManager = linearLayoutManager
        val adpater = FeedReaderAdapter(this, feedReaderList)
        rv.adapter = adpater
        adpater.listener = object : FeedReaderAdapter.IFeedReaderAdapterListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, UpdateFeedReaderActivity::class.java)
                intent.putExtra("id", feedReaderList.get(position).id)
                startActivityForResult(intent, 100)
            }
        }

    }

    private fun getData() {
        feedReaderList.addAll(DbUtil.readAllData(dbManager.getDb()))
        rv.adapter?.notifyDataSetChanged()
    }

    private fun initEvent() {
        findViewById<TextView>(R.id.tv_add).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_select).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_delete).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_add -> {
                startActivityForResult(Intent(this, AddFeedReaderActivity::class.java), 100)
            }
            R.id.tv_delete -> {
                startActivity(Intent(this, DeleteFeedReaderActivity::class.java))
            }
            R.id.tv_select -> {
                startActivity(Intent(this, SearchFeedReaderActivity::class.java))
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            feedReaderList.clear()
            getData()
            Log.e("", "onActivityResult")
        }
    }

    override fun onDestroy() {
        dbManager.close()
        super.onDestroy()
    }

}