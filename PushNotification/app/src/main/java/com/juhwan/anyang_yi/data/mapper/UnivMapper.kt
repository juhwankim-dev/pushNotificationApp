package com.juhwan.anyang_yi.data.mapper

import com.juhwan.anyang_yi.domain.model.Univ
import okhttp3.ResponseBody

object UnivMapper {
    val baseUrl = "https://www.anyang.ac.kr/main/communication/notice.do?mode=view"

    operator fun invoke(responseBody: ResponseBody): List<Univ> {
        var list = arrayListOf<Univ>()

        // TODO: 학교 홈페이지 리뉴얼에 대응 하기
        list.add(
            Univ(
                isHeaderNotice = true,
                isNew = true,
                title = "임시",
                date = "2022.05.29",
                url = baseUrl
            )
        )

        return list
    }
}