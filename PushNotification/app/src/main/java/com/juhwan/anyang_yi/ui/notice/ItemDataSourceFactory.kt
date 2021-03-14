package com.juhwan.anyang_yi.ui.notice

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.juhwan.anyang_yi.network.ResultList


class ItemDataSourceFactory : DataSource.Factory<Int, ResultList>(){

    private var itemLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, ResultList>>()

    override fun create(): DataSource<Int, ResultList> {
        var itemDataSource =  ItemDataSource()
        itemLiveDataSource.postValue(itemDataSource)
        return itemDataSource
    }

    fun getItemLiveDataSource(): MutableLiveData<PageKeyedDataSource<Int, ResultList>> {
        Log.v("여기는?", itemLiveDataSource.value.toString())
        return itemLiveDataSource
    }


/*    val sourceLiveData = MutableLiveData<ItemDataSource>()
    lateinit var latestSource: ItemDataSource

    override fun create(): DataSource<Int, ResultList> {
        latestSource = ItemDataSource()
        sourceLiveData.value = latestSource
        return latestSource
    }

    fun getItemLiveDataSource(): ItemDataSource {
        return latestSource
    }*/
}