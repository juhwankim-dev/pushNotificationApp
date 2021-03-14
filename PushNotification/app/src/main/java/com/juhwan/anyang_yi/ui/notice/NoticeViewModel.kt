package com.juhwan.anyang_yi.ui.notice

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.juhwan.anyang_yi.network.Result
import com.juhwan.anyang_yi.network.ResultList
import com.juhwan.anyang_yi.repository.NoticeRepository

class NoticeViewModel: ViewModel() {
/*    private val repository = NoticeRepository()

    private val noticeInfo: LiveData<Result>
    get() = repository._noticeInfo

    fun requestPost(page: Int) {
        repository.requestPost(page)
    }

    fun getAll(): LiveData<Result> {
        return noticeInfo
    }*/

    var itemDataSourceFactory = ItemDataSourceFactory()

    val liveDataSource: LiveData<PageKeyedDataSource<Int, ResultList>>
    get() = itemDataSourceFactory.getItemLiveDataSource()

    var config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(15)

        .setInitialLoadSizeHint(40)
        .setPrefetchDistance(5)
        .build()

    val itemPagedList: LiveData<PagedList<ResultList>>
        get() = LivePagedListBuilder(itemDataSourceFactory, config).build()

/*    init {
        var itemDataSourceFactory = ItemDataSourceFactory()
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource()

        var config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(15)
            .build()

        itemPagedList = LivePagedListBuilder(itemDataSourceFactory, config).build()
    }*/
}