package com.juhwan.anyang_yi.data.mapper

import com.juhwan.anyang_yi.data.model.KeywordEntity

object KeywordMapper {
    fun mapperToKeywordEntity(keyword: String): KeywordEntity {
        return KeywordEntity(
            keyword = keyword
        )
    }
}