package com.juhwan.anyang_yi.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface KeywordDao {
    @Query("SELECT * FROM Keyword")
    fun getAll(): LiveData<List<Keyword>>

    @Insert
    fun insert(todo: Keyword)

    @Query("DELETE FROM Keyword WHERE keyword = :keyword")
    fun deleteKeywordByTitle(keyword: String)
}