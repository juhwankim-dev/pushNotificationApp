package com.example.pushnotification.fragments.keyword

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Keyword::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun keywordDao(): KeywordDao
}