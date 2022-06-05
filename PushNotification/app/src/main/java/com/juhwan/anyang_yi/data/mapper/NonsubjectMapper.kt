package com.juhwan.anyang_yi.data.mapper

import com.juhwan.anyang_yi.domain.model.Nonsubject
import com.juhwan.anyang_yi.present.config.Constants
import okhttp3.ResponseBody
import org.jsoup.Jsoup

object NonsubjectMapper {
    operator fun invoke(responseBody: ResponseBody): List<Nonsubject> {
        var list = ArrayList<Nonsubject>()

        var doc = Jsoup.parse(responseBody.string())
        var programList = doc.select(".list_program")[0]
        var elementDDay = programList.select(".txt_1")
        var elementImage = programList.select(".img img")
        var elementTitle = programList.select(".tit a")
        var elementUrl = programList.select(".tit a")
        var elementPeriod = programList.select(".line")
        var elementApplicant = programList.select(".app strong")

        for(i in elementTitle.size - 1 downTo 0) {
            val trainingPeriod = elementTitle[i].text()
            list.add(
                Nonsubject (
                    title = trainingPeriod.substring(5, trainingPeriod.length),
                    trainingPeriod = "기간: " + elementPeriod[i * 2 + 1].text(),
                    applicant = "인원: " + elementApplicant[i * 2].text() + "/" + elementApplicant[i * 2 + 1].text(),
                    leftDay = elementDDay[i].text().replace("ay", "").replace("종료", "0"),
                    imageUrl = Constants.NONSUBJECT_IMAGE_BASE_URL + elementImage[i].attr("src"),
                    webLink = Constants.NONSUBJECT_BASE_URL + elementUrl[i].attr("href")
                )
            )
        }

        return list
    }
}