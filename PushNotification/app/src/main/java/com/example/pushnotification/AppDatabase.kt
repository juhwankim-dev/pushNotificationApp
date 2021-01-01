package com.example.pushnotification

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Keyword::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun keywordDao(): KeywordDao
}