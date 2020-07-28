package com.iblogstreet.sqlitedemo.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.iblogstreet.sqlitedemo.bean.EntryBean

/**
 * @author junwang
 * @date 2020/7/27 1:39 PM
 */
@Dao
interface EntryDao {
    @Query("SELECT * FROM entry ORDER BY createdate ASC")
    fun getAll(): LiveData<List<EntryBean>>

    @Query("SELECT * FROM entry WHERE title LIKE  :title ")
    fun searchByTitle(title: String): List<EntryBean>

    @Insert
    fun insert(feed: EntryBean): Long

}