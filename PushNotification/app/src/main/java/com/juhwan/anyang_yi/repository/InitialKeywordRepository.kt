package com.juhwan.anyang_yi.repository

import com.juhwan.anyang_yi.data.initialKeyword
import kotlin.collections.ArrayList

object InitialKeywordRepository {

    var internalUnivKeyword = ArrayList<initialKeyword>(
        listOf(
            initialKeyword("튜터링", false),
            initialKeyword("신공재전", false),
            initialKeyword("교환학생", false),
            initialKeyword("신입생", false),
            initialKeyword("근로", false),
            initialKeyword("장학", false)
        )
    )
    var externalUnivKeyword = ArrayList<initialKeyword>(
        listOf(
            initialKeyword("채용", false),
            initialKeyword("인턴", false),
            initialKeyword("창업", false),
            initialKeyword("공모전", false),
            initialKeyword("취업", false)
        )
    )
    var univKeyword = ArrayList<initialKeyword>(
        listOf(
            initialKeyword("비대면", false),
            initialKeyword("대면", false),
            initialKeyword("휴학", false),
            initialKeyword("복학", false),
            initialKeyword("등록금", false),
            initialKeyword("계절", false)
        )
    )
}