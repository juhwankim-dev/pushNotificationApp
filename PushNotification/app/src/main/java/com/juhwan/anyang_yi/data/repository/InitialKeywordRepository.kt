package com.juhwan.anyang_yi.data.repository

import com.juhwan.anyang_yi.data.model.InitialKeyword
import kotlin.collections.ArrayList

object InitialKeywordRepository {

    var internalUnivKeyword = ArrayList<InitialKeyword>(
        listOf(
            InitialKeyword("튜터링", false),
            InitialKeyword("신공재전", false),
            InitialKeyword("교환학생", false),
            InitialKeyword("신입생", false),
            InitialKeyword("근로", false),
            InitialKeyword("장학", false)
        )
    )
    var externalUnivKeyword = ArrayList<InitialKeyword>(
        listOf(
            InitialKeyword("채용", false),
            InitialKeyword("인턴", false),
            InitialKeyword("창업", false),
            InitialKeyword("공모전", false),
            InitialKeyword("취업", false)
        )
    )
    var univKeyword = ArrayList<InitialKeyword>(
        listOf(
            InitialKeyword("비대면", false),
            InitialKeyword("대면", false),
            InitialKeyword("휴학", false),
            InitialKeyword("복학", false),
            InitialKeyword("등록금", false),
            InitialKeyword("계절", false)
        )
    )
}