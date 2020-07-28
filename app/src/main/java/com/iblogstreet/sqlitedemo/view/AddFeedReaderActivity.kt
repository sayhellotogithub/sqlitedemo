package com.iblogstreet.sqlitedemo.view

import android.app.Activity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.iblogstreet.sqlitedemo.R
import com.iblogstreet.sqlitedemo.bean.EntryBean
import com.iblogstreet.sqlitedemo.util.DBManager
import com.iblogstreet.sqlitedemo.util.DbUtil
import com.iblogstreet.sqlitedemo.viewmodel.EntryViewModel

/**
 * @author junwang
 * @date 2020/7/24 3:34 PM
 */
class AddFeedReaderActivity : AppCompatActivity() {
    private lateinit var etTitle: EditText
    private lateinit var etSubTitle: EditText
    private lateinit var entryViewModel: EntryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_feedreader)
        initView()
        initData()
        initEvent()
    }

    private fun initData() {
        entryViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)
            .create(EntryViewModel::class.java)

    }

    private fun initView() {
        etTitle = findViewById(R.id.et_title)
        etSubTitle = findViewById(R.id.et_sub_title)
    }

    private fun initEvent() {
        findViewById<TextView>(R.id.tv_add).setOnClickListener {
            val result = entryViewModel.insertEntry(
                EntryBean(
                    0,
                    etTitle.text.toString(),
                    etSubTitle.text.toString(),
                    System.currentTimeMillis().toString(),
                    null
                )
            )

            if (result
            ) {
                Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
                setResult(Activity.RESULT_OK)
                finish()
            } else {
                Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show()
            }
        }
    }

}