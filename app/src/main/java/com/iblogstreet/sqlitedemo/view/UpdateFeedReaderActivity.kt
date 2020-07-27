package com.iblogstreet.sqlitedemo.view

import android.app.Activity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.iblogstreet.sqlitedemo.R
import com.iblogstreet.sqlitedemo.util.DbUtil

/**
 * @author junwang
 * @date 2020/7/24 3:34 PM
 */
class UpdateFeedReaderActivity : AppCompatActivity() {
    private lateinit var etTitle: EditText
    private lateinit var etSubTitle: EditText
    private var id: Long? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_feedreader)
        initView()
        initEvent()
        initData()
    }

    private fun initData() {
        id = intent.getLongExtra("id", 0)
        getData()
    }

    private fun getData() {
        DbUtil.readDataById(this, id)?.apply {
            etSubTitle.setText(subtitle)
            etTitle.setText(title)
        }
    }

    private fun initView() {
        etTitle = findViewById(R.id.et_title)
        etSubTitle = findViewById(R.id.et_sub_title)
    }

    private fun initEvent() {
        findViewById<TextView>(R.id.tv_update).setOnClickListener {
            if (DbUtil.updateData(this, id, etTitle.text.toString(), etSubTitle.text.toString())) {
                Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
                setResult(Activity.RESULT_OK)
                finish()
            } else {
                Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show()
            }
        }
    }
}