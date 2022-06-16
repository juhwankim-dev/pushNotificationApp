package com.juhwan.anyang_yi.present.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime

object DateUtil {
    private val sdf = SimpleDateFormat("yyyy-MM-dd")
    private var now = sdf.parse("${LocalDate.now()} 00:00:00")

    fun millisecondToDate(millisecond: Long): String = sdf.format(millisecond)
    fun getLeftDay(millisecond: Long): Int = getLeftDay(millisecondToDate(millisecond))
    fun getFirstDayOfThisYear(): String = "${LocalDate.now().year}-01-01"
    fun getLastDayOfThisYear(): String = "${LocalDate.now().year}-12-31"
    fun getLocalDateTime(): String {
        var dateTime = "${LocalDateTime.now()}"
        return dateTime.replace(".", "")
    }

    fun getLeftDay(dateString: String): Int {
        var writeDate = sdf.parse("$dateString 00:00:00")
        var calculateDate = (now.time - writeDate.time) / (60 * 60 * 24 * 1000)

        return calculateDate.toInt()
    }
}