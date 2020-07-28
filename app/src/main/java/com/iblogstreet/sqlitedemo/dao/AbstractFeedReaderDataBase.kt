package com.iblogstreet.sqlitedemo.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iblogstreet.sqlitedemo.bean.EntryBean

/**
 * @author junwang
 * @date 2020/7/27 1:49 PM
 */
@Database(entities = arrayOf(EntryBean::class), version = 2)
abstract class AbstractFeedReaderDataBase : RoomDatabase() {
    abstract fun entryDao(): EntryDao

    companion object {
        @Volatile
        private var INSTANCE: AbstractFeedReaderDataBase? = null

        fun getDataBase(context: Context): AbstractFeedReaderDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AbstractFeedReaderDataBase::class.java,
                    "FeedReader.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}