package com.juhwan.anyang_yi.data.mapper

import com.juhwan.anyang_yi.data.model.KakaoEntity
import com.juhwan.anyang_yi.domain.model.Kakao
import com.juhwan.anyang_yi.present.utils.DateUtil

object KakaoMapper {
    operator fun invoke(kakaoEntity: KakaoEntity): List<Kakao> {
        var newList = arrayListOf<Kakao>()
        var size = if(kakaoEntity.posts.items.size > 10) {
            10
        } else {
            kakaoEntity.posts.items.size
        }

        for ((index, value) in kakaoEntity.posts.items.withIndex()) {
            if(index == size) {
                break
            }

            try {
                val url = if(value.media == null) {
                    null
                } else if(value.media[0].small_url == null) {
                    value.media[0].medium.small_url
                } else {
                    value.media[0].small_url
                }

                newList.add(
                    Kakao(
                        isNew = DateUtil.getLeftDay(value.created_at) < 2,
                        url = url,
                        title = value.title,
                        date = DateUtil.millisecondToDate(value.created_at),
                        webLink = value.permalink
                    )
                )
            } catch (e: Exception) {

            }
        }

        return newList
    }
}