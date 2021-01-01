package com.example.pushnotification

import androidx.room.*

@Dao
interface KeywordDao {
    @Query("SELECT * FROM Keyword")
    fun getAll(): List<Keyword>

    @Insert
    fun insert(todo: Keyword)

    @Update
    fun update(todo: Keyword)

    @Delete
    fun delete(todo: Keyword)

    @Query("DELETE FROM Keyword WHERE keyword = :keyword")
    fun deleteBytitle(keyword: String)

    @Query("UPDATE Keyword SET keyword = :keyword WHERE keyword = :keyword")
    fun updateByischecked(keyword: String)
}