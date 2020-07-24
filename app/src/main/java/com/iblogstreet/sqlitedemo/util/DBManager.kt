package com.iblogstreet.sqlitedemo.util

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.iblogstreet.sqlitedemo.db.FeedReaderDbHelper

/**
 * @author junwang
 * @date 2020/7/24 1:56 PM
 */
class DBManager {
    private lateinit var context: Context
    private var dbHelper: SQLiteOpenHelper? = null
    private var db: SQLiteDatabase? = null

    constructor(context: Context) {
        this.context = context
        this.dbHelper = FeedReaderDbHelper(context)
    }

    fun open(): DBManager {
        db = dbHelper?.writableDatabase
        return this
    }

    fun getDb(): SQLiteDatabase? {
        return db
    }

    fun close() {
        db?.close()
    }

}