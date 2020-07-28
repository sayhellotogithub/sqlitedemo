package com.iblogstreet.sqlitedemo.repo

import androidx.lifecycle.LiveData
import com.iblogstreet.sqlitedemo.bean.EntryBean
import com.iblogstreet.sqlitedemo.dao.EntryDao

/**
 * @author junwang
 * @date 2020/7/27 4:59 PM
 */
class EntryRepository(private val entryDao: EntryDao) {
    fun getAllEntry(): LiveData<List<EntryBean>> {
        return entryDao.getAll()
    }

    fun  insertEntry(entry: EntryBean): Boolean {
        return entryDao.insert(entry) > 0
    }
}