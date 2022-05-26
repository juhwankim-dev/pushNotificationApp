package com.juhwan.anyang_yi.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.juhwan.anyang_yi.data.db.Keyword

@Dao
interface KeywordDao {
    @Query("SELECT * FROM Keyword")
    fun getAll(): LiveData<List<Keyword>>

    @Insert
    fun insert(todo: Keyword)

    @Query("DELETE FROM Keyword WHERE keyword = :keyword")
    fun deleteKeywordByTitle(keyword: String)
}