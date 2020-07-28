package com.iblogstreet.sqlitedemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.iblogstreet.sqlitedemo.bean.EntryBean
import com.iblogstreet.sqlitedemo.dao.AbstractFeedReaderDataBase
import com.iblogstreet.sqlitedemo.repo.EntryRepository


/**
 * @author junwang
 * @date 2020/7/27 5:29 PM
 */
open class EntryViewModel(application: Application) : AndroidViewModel(application) {
    private val entryRepository: EntryRepository

    init {
        entryRepository =
            EntryRepository(AbstractFeedReaderDataBase.getDataBase(application).entryDao())
    }

    fun getAllEntry(): LiveData<List<EntryBean>> {
        return entryRepository.getAllEntry()
    }

    fun insertEntry(entryBean: EntryBean): Boolean {
        return entryRepository.insertEntry(entryBean)
    }


}