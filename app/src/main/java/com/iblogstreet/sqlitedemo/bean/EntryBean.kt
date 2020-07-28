package com.iblogstreet.sqlitedemo.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author junwang
 * @date 2020/7/24 11:20 AM
 */
@Entity(tableName = "entry")
data class EntryBean(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") val id: Int,

    val title: String? = null,
    val subtitle: String? = null,
    @ColumnInfo(name = "createdate")
    val createDate: String? = null,
    @ColumnInfo(name = "updatedate")
    val updateDate: String? = null
)