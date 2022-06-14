package com.juhwan.anyang_yi.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.juhwan.anyang_yi.data.api.UnivApi
import com.juhwan.anyang_yi.data.mapper.UnivMapper
import com.juhwan.anyang_yi.domain.model.Univ
import javax.inject.Inject

class SearchUnivPagingDataSource @Inject constructor(
    private val univApi: UnivApi,
    private val keyword: String,
) : PagingSource<Int, Univ>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Univ> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = univApi.getSearchResultList(keyword, page.toString())

            LoadResult.Page(
                data = UnivMapper(response.body()!!),
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(10),
                nextKey = if (!response.isSuccessful) null else page.plus(10)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Univ>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(10)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(10)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 0
    }
}