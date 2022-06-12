package com.juhwan.anyang_yi.data.mapper

import com.juhwan.anyang_yi.domain.model.Univ
import com.juhwan.anyang_yi.present.config.Constants
import com.juhwan.anyang_yi.present.utils.DateUtil
import okhttp3.ResponseBody
import org.jsoup.Jsoup

object UnivMapper {
    operator fun invoke(responseBody: ResponseBody): List<Univ> {
        var list = arrayListOf<Univ>()
        var doc = Jsoup.parse(responseBody.string())
        val headerList = doc.select(".b-top-box")
        val noticeList = doc.select(".b-title-box ")
        val headerSize = headerList.size
        val noticeSize = noticeList.size

        for(i in headerSize until noticeSize) {
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