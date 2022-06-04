package com.juhwan.anyang_yi.data.mapper

import com.juhwan.anyang_yi.data.model.KakaoEntity
import com.juhwan.anyang_yi.domain.model.Kakao
import com.juhwan.anyang_yi.present.utils.DateUtil

object KakaoMapper {
    operator fun invoke(kakaoEntity: KakaoEntity): List<Kakao> {
        var newList = arrayListOf<Kakao>()

        kakaoEntity.posts.items.forEach { i ->
            newList.add(
                Kakao(
                    isNew = DateUtil.getLeftDay(i.created_at) < 2,
                    url = i.media[0].small_url,
                    title = i.title,
                    date = DateUtil.millisecondToDate(i.created_at),
                    webLink = i.permalink
                )
            )
        }

        return newList
    }
}