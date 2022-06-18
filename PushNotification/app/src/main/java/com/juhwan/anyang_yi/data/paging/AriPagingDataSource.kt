package com.juhwan.anyang_yi.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.juhwan.anyang_yi.data.api.AriApi
import com.juhwan.anyang_yi.data.mapper.AriMapper
import com.juhwan.anyang_yi.domain.model.Ari
import javax.inject.Inject

class AriPagingDataSource @Inject constructor(
    private val ariApi: AriApi,
    private val keyword: String?
) : PagingSource<Int, Ari>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Ari> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val field: MutableMap<String, String> = HashMap()
            field["schWord"] = keyword ?: "empty"
            field["noneChk"] = "2"
            field["pageNo"] = page.toString()
            if(keyword != null) {
                field["schKeyM"] = "CONT"
            }

            val response = ariApi.getAriNoticeList(field)
            LoadResult.Page(
                data = AriMapper(response.body()!!),
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (!response.isSuccessful) null else page.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Ari>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}