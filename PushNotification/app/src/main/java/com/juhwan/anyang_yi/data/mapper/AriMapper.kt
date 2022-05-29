package com.juhwan.anyang_yi.data.mapper

import com.juhwan.anyang_yi.domain.model.Ari
import okhttp3.ResponseBody
import org.jsoup.Jsoup

object AriMapper {
    operator fun invoke(responseBody: ResponseBody): List<Ari> {
        var list = ArrayList<Ari>()

        var doc = Jsoup.parse(responseBody.string())
        var elementTitle = doc.select(".alignL a")
        var elementDate = doc.select(".alignC")

        for((i, e) in elementTitle.withIndex()){
            list.add(Ari(e.attr("href"), e.text(), elementDate[i * 4 + 2].text().replace("/", "-")))
        }
        list.add(Ari(" ", " ", " ")) // 프로그레스바를 위치할 곳

        return list
    }
}