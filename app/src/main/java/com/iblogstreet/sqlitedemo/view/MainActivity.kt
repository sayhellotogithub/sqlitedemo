package com.iblogstreet.sqlitedemo.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iblogstreet.sqlitedemo.R
import com.iblogstreet.sqlitedemo.adapter.FeedReaderAdapter
import com.iblogstreet.sqlitedemo.bean.FeedReaderBean
import com.iblogstreet.sqlitedemo.util.DbUtil

/**
 * @author junwang
 * @date 2020/7/23 5:15 PM
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var rv: RecyclerView
    private var feedReaderList = mutableListOf<FeedReaderBean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        rv.adapter = FeedReaderAdapter(this, feedReaderList)

    }

    private fun getData() {
        feedReaderList.addAll(DbUtil.readAllData(this))
        rv.adapter?.notifyDataSetChanged()
    }

    private fun initEvent() {
        findViewById<TextView>(R.id.tv_add).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_add -> {
                startActivity(Intent(this, AddFeedReaderActivity::class.java))
            }
            R.id.tv_delete -> {

            }
            R.id.tv_update -> {

            }
            R.id.tv_select -> {

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            feedReaderList.clear()
            getData()
        }
    }

}