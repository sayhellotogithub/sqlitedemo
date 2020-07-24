package com.iblogstreet.sqlitedemo.util

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import android.text.TextUtils
import android.util.Log

import com.iblogstreet.sqlitedemo.bean.FeedReaderBean
import com.iblogstreet.sqlitedemo.db.FeedReaderContract
import com.iblogstreet.sqlitedemo.db.FeedReaderDbHelper
import java.util.*

/**
 * @author junwang
 * @date 2020/7/23 5:17 PM
 */
object DbUtil {
    fun putData(context: Context, title: String, subtitle: String): Boolean {
        val dbHelper = FeedReaderDbHelper(context)
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title)
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, subtitle)
            put(FeedReaderContract.FeedEntry.COLUMN_CREATE_DATE, System.currentTimeMillis())
        }
        val newRowId = db?.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values)
        Log.e("DbUtil", "newRowId$newRowId")
        return newRowId != null
    }

    fun readAllData(context: Context): List<FeedReaderBean> {
        val dbHelper = FeedReaderDbHelper(context)
        val db = dbHelper.readableDatabase
        val projection = arrayOf(
            BaseColumns._ID,
            FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
            FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE,
            FeedReaderContract.FeedEntry.COLUMN_CREATE_DATE,
            FeedReaderContract.FeedEntry.COLUMN_UPDATE_DATE
        )

        val sortOrder = "${FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE} DESC"

        val cursor = db.query(
            FeedReaderContract.FeedEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            sortOrder
        )
        val list = mutableListOf<FeedReaderBean>()
        with(cursor) {
            while (moveToNext()) {
                val bean = FeedReaderBean()
                bean.id = getLong(getColumnIndex(BaseColumns._ID))
                bean.title =
                    getString(getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE))
                bean.subtitle =
                    getString(getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE))

                bean.createDate =
                    getString(getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_CREATE_DATE))
                bean.updateDate =
                    getString(getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_UPDATE_DATE))

                list.add(bean)
            }
        }
        cursor.close()
        Log.e("DbUtil", "list${list.size}")
        return list
    }

    fun readData(context: Context, title: String): List<FeedReaderBean> {
        val dbHelper = FeedReaderDbHelper(context)
        val db = dbHelper.readableDatabase
        val projection = arrayOf(
            BaseColumns._ID,
            FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
            FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE,
            FeedReaderContract.FeedEntry.COLUMN_CREATE_DATE,
            FeedReaderContract.FeedEntry.COLUMN_UPDATE_DATE
        )
        val selection = "${FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE}= ?"
        val selectionArgs = arrayOf(title)

        val sortOrder = "${FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE} DESC"

        val cursor = db.query(
            FeedReaderContract.FeedEntry.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )
        val list = mutableListOf<FeedReaderBean>()
        with(cursor) {
            while (moveToNext()) {
                val bean = FeedReaderBean()
                bean.id = getLong(getColumnIndex(BaseColumns._ID))
                bean.title =
                    getString(getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE))
                bean.subtitle =
                    getString(getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE))

                bean.createDate =
                    getString(getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_CREATE_DATE))
                bean.updateDate =
                    getString(getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_UPDATE_DATE))

                list.add(bean)
            }
        }
        cursor.close()
        Log.e("DbUtil", "list${list.size}")
        return list
    }

    fun deleteData(context: Context): Boolean {
        val dbHelper = FeedReaderDbHelper(context)
        val db = dbHelper.writableDatabase
        val selection = "${FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE} LIKE ?"
        val selectionArgs = arrayOf("myTitle")
        val deletedRows =
            db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, selectionArgs)
        return deletedRows > 0
    }

    fun updateData(context: Context) {
        val dbHelper = FeedReaderDbHelper(context)
        val db = dbHelper.writableDatabase

        val title = "myNewTitle"
        val values = ContentValues().apply {
            put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title)
        }
        val selection = "${FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE} LIKE ?"
        val selectionArgs = arrayOf("MyOldTitle")
        val count =
            db.update(FeedReaderContract.FeedEntry.TABLE_NAME, values, selection, selectionArgs)
    }
}