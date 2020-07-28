package com.iblogstreet.sqlitedemo.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iblogstreet.sqlitedemo.R
import com.iblogstreet.sqlitedemo.adapter.FeedReaderAdapter
import com.iblogstreet.sqlitedemo.bean.EntryBean
import com.iblogstreet.sqlitedemo.viewmodel.EntryViewModel


/**
 * @author junwang
 * @date 2020/7/23 5:15 PM
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var rv: RecyclerView
    private var feedReaderList = mutableListOf<EntryBean>()

    private lateinit var entryViewModel: EntryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        entryViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)
            .create(EntryViewModel::class.java)
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
        entryViewModel.getAllEntry().observe(this, Observer {
            feedReaderList.addAll(it)
            rv.adapter?.notifyDataSetChanged()
        })
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
            Log.e("onActivityResult", "onActivityResult")
            feedReaderList.clear()
            getData()

        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}