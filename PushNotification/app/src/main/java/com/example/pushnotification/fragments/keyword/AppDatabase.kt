package com.example.pushnotification.fragments.keyword

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pushnotification.fragments.keyword.Keyword
import com.example.pushnotification.fragments.keyword.KeywordDao

@Database(entities = [Keyword::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun keywordDao(): KeywordDao
}