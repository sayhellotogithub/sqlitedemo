package com.iblogstreet.sqlitedemo.util

import android.content.ContentValues
import android.content.Context
import com.iblogstreet.myapplication.db.FeedReaderContract
import com.iblogstreet.myapplication.db.FeedReaderDbHelper

/**
 * @author junwang
 * @date 2020/7/23 5:17 PM
 */
object DbUtil {
    fun putData(context: Context, title: String, subtitle: String) {
        val dbHelper = FeedReaderDbHelper(context)
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, subtitle)
        }
        val newRowId = db?.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values)
    }
}