package com.juhwan.anyang_yi.data.mapper

import android.util.Log
import com.juhwan.anyang_yi.domain.model.Univ
import com.juhwan.anyang_yi.present.config.Constants
import com.juhwan.anyang_yi.present.utils.DateUtil
import okhttp3.ResponseBody
import org.jsoup.Jsoup

object UnivMapper {
    operator fun invoke(responseBody: ResponseBody): List<Univ> {
        var list = arrayListOf<Univ>()

        var doc = Jsoup.parse(responseBody.string())
        var noticeList = doc.select(".b-title-box ")

        val size = noticeList.size
        for(i in size - 10 until size) {
            val href = noticeList[i].select("a[href]").attr("href")
            val title = noticeList[i].select("a[href]").attr("title")
            val date = "20" + noticeList[i].select(".b-date").text()

            list.add(
                Univ(
                    isHeaderNotice = false,
                    isNew = DateUtil.getLeftDay(date.replace(".", "-")) == 0,
                    title = title,
                    date = date,
                    url = Constants.UNIV_WEB_LINK_BASE_URL + href
                )
            )
        }

        return list
    }
}