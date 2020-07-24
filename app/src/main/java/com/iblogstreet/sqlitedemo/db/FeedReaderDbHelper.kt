package com.iblogstreet.sqlitedemo.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/**
 * @author junwang
 * @date 2020/7/23 4:17 PM
 */
class FeedReaderDbHelper(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null,
    DATABASE_VERSION
) {
    companion object {
        val TAG = FeedReaderDbHelper.javaClass.simpleName
        const val DATABASE_VERSION = 2
        const val DATABASE_NAME = "FeedReader.db"
    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.e(TAG, "onCreate")
        db.execSQL(FeedReaderContract.SQL_CREATE_ENTRIES)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
        Log.e(TAG, "onDowngrade")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.e(TAG, "onUpgrade${oldVersion},${newVersion}")
        if (oldVersion == 1) {
            db.execSQL(FeedReaderContract.SQL_ADD_COLUMN_UPDATE_DATE)
        }


    }
}