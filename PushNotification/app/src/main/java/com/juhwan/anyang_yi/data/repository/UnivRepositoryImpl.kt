package com.juhwan.anyang_yi.data.repository

import com.juhwan.anyang_yi.data.mapper.UnivMapper
import com.juhwan.anyang_yi.data.repository.univ.UnivRemoteDataSource
import com.juhwan.anyang_yi.domain.model.Univ
import com.juhwan.anyang_yi.domain.repository.UnivRepository
import com.juhwan.anyang_yi.present.utils.Result
import javax.inject.Inject

class UnivRepositoryImpl @Inject constructor(
    private val univRemoteDataSource: UnivRemoteDataSource
) : UnivRepository {
    val field: MutableMap<String, String> = mutableMapOf(
        "mode" to "list"
    )

    override fun getUnivNoticeList(): Result<List<Univ>> {
        //field["page"] = page.toString()
        // TODO: 리뉴얼된 홈페이지에 대응

        return try {
            val response = univRemoteDataSource.getUnivNoticeList(field)

            if(response.isSuccessful && response.body() != null) {
                Result.success(UnivMapper(response.body()!!))
            } else {
                Result.error(response.errorBody().toString(), null)
            }
        } catch (error: Exception) {
            Result.fail()
        }
    }
}