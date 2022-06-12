package com.juhwan.anyang_yi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.juhwan.anyang_yi.data.model.KeywordEntity

@Database(entities = [KeywordEntity::class], version = 1)
abstract class KeywordDatabase: RoomDatabase() {
    abstract fun keywordDao(): KeywordDao
}