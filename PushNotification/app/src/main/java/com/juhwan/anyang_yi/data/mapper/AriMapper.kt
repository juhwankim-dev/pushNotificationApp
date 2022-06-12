package com.juhwan.anyang_yi.data.mapper

import com.juhwan.anyang_yi.domain.model.Ari
import com.juhwan.anyang_yi.present.config.Constants.ARI_BASE_URL
import com.juhwan.anyang_yi.present.utils.DateUtil
import okhttp3.ResponseBody
import org.jsoup.Jsoup

object AriMapper {
    operator fun invoke(responseBody: ResponseBody): List<Ari> {
        var list = ArrayList<Ari>()

        var doc = Jsoup.parse(responseBody.string())
        var elementTitle = doc.select(".alignL a")
        var elementDate = doc.select(".alignC")

        for((i, e) in elementTitle.withIndex()){
            val date = elementDate[i * 4 + 2].text().replace("/", "-")
            list.add(Ari(ARI_BASE_URL + e.attr("href"), e.text(), date, DateUtil.getLeftDay(date) == 0))
        }

        return list
    }
}