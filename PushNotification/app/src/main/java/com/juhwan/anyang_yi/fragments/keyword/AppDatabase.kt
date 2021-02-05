package com.juhwan.anyang_yi.fragments.keyword

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Keyword::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun keywordDao(): KeywordDao
}