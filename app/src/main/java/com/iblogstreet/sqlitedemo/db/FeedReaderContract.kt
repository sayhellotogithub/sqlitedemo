package com.iblogstreet.myapplication.db

import android.provider.BaseColumns

/**
 * @author junwang
 * @date 2020/7/23 4:07 PM
 */
object FeedReaderContract {
    object FeedEntry : BaseColumns {
        const val TABLE_NAME = "entry"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_SUBTITLE = "subtitle"
        const val COLUMN_CREATE_DATE = "createdate"
        const val COLUMN_UPDATE_DATE = "updatedate"
    }

    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${FeedEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${FeedEntry.COLUMN_NAME_TITLE} TEXT," +
                "${FeedEntry.COLUMN_NAME_SUBTITLE} TEXT," +
                "${FeedEntry.COLUMN_CREATE_DATE} Text" +
                ")"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${FeedEntry.TABLE_NAME}"
}