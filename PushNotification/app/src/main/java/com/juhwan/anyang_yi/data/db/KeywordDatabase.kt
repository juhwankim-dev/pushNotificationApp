package com.juhwan.anyang_yi.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Keyword::class], version = 1)
abstract class KeywordDatabase: RoomDatabase() {
    abstract fun keywordDao(): KeywordDao

    companion object {
        private var instance: KeywordDatabase? = null

        @Synchronized
        fun getInstance(context: Context): KeywordDatabase? {
            if (instance == null) {
                synchronized(KeywordDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        KeywordDatabase::class.java,
                        "database-name" // 처음에 이름을 잘못정했음
                    )
                        .build()
                }
            }
            return instance
        }
    }
}