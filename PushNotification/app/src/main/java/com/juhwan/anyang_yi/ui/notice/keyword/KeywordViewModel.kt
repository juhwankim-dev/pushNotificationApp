package com.juhwan.anyang_yi.ui.notice.keyword

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.juhwan.anyang_yi.data.Result
import com.juhwan.anyang_yi.database.Keyword
import com.juhwan.anyang_yi.repository.KeywordRepository
import com.juhwan.anyang_yi.repository.MainNoticeRepository

class KeywordViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = KeywordRepository(application)
    private val items = repository.getAll()

    private val mainNoticeRepository = MainNoticeRepository()
    private val searchResult: LiveData<Result>
        get() = mainNoticeRepository._searchResult

    fun insert(keyword: Keyword) {
        repository.insert(keyword)
    }

    fun deleteKeywordByTitle(keyword: String) {
        repository.deleteKeywordByTitle(keyword)
    }

    fun getAll(): LiveData<List<Keyword>> {
        return items
    }

    fun searchKeyword(keyword: String){
        mainNoticeRepository.searchKeyword(keyword)
    }

    fun getResult(): LiveData<Result> {
        return searchResult
    }
}