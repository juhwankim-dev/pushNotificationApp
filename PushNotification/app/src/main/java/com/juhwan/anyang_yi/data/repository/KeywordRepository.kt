package com.juhwan.anyang_yi.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.juhwan.anyang_yi.data.db.Keyword
import com.juhwan.anyang_yi.data.db.KeywordDao
import com.juhwan.anyang_yi.data.db.KeywordDatabase

class KeywordRepository(application: Application) {
    private val keywordDao: KeywordDao
    private val keywordList: LiveData<List<Keyword>>

    init {
        var db = KeywordDatabase.getInstance(application)
        keywordDao = db!!.keywordDao()
        keywordList = db.keywordDao().getAll()
    }

    fun insert(keyword: Keyword) {
        keywordDao.insert(keyword)
    }

    fun deleteKeywordByTitle(keyword: String){
        keywordDao.deleteKeywordByTitle(keyword)
    }

    fun getAll(): LiveData<List<Keyword>> {
        return keywordDao.getAll()
    }
}