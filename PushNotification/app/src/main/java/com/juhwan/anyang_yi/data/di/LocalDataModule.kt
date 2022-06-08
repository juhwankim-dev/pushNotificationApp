package com.juhwan.anyang_yi.data.di

import android.content.Context
import androidx.room.Room
import com.juhwan.anyang_yi.data.db.KeywordDao
import com.juhwan.anyang_yi.data.db.KeywordDatabase
import com.juhwan.anyang_yi.data.repository.keyword.KeywordLocalDataSource
import com.juhwan.anyang_yi.data.repository.keyword.KeywordLocalDataSourceImpl
import com.juhwan.anyang_yi.present.config.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LocalDataModule {
    @Provides
    @Singleton
    fun provideKeywordDatabase(@ApplicationContext context: Context): KeywordDatabase {
        return Room.databaseBuilder(
            context,
            KeywordDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideKeywordDao(keywordDatabase: KeywordDatabase): KeywordDao {
        return keywordDatabase.keywordDao()
    }

    @Provides
    @Singleton
    fun provideKeywordLocalDataSource(keywordDao: KeywordDao): KeywordLocalDataSource {
        return KeywordLocalDataSourceImpl(keywordDao)
    }
}