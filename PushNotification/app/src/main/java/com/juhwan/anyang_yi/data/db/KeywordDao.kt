package com.juhwan.anyang_yi.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.juhwan.anyang_yi.data.model.KeywordEntity
import com.juhwan.anyang_yi.present.config.Constants.TABLE_NAME

@Dao
interface KeywordDao {
    @Query("SELECT * FROM $TABLE_NAME")
    fun readKeywordList(): LiveData<List<KeywordEntity>>

    @Insert
    suspend fun writeKeyword(keywordEntity: KeywordEntity)

    @Query("DELETE FROM $TABLE_NAME WHERE keyword = :keyword")
    suspend fun deleteKeyword(keyword: String)
}