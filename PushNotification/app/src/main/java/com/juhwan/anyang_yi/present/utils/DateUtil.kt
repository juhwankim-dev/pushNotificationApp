package com.juhwan.anyang_yi.present.utils

import java.text.SimpleDateFormat
import java.time.LocalDate

object DateUtil {
    private val sdf = SimpleDateFormat("yyyy-MM-dd")
    private var today = sdf.parse("${LocalDate.now()} 00:00:00")

    fun millisecondToDate(millisecond: Long): String {
        return sdf.format(millisecond)
    }

    fun getLeftDay(millisecond: Long): Int {
        return getLeftDay(millisecondToDate(millisecond))
    }

    fun getLeftDay(dateString: String): Int {
        var writeDate = sdf.parse("$dateString 00:00:00")
        var calculateDate = (today.time - writeDate.time) / (60 * 60 * 24 * 1000)

        return calculateDate.toInt()
    }

    fun getFirstDayOfThisYear(): String {
        return "${LocalDate.now().year}-01-01"
    }

    fun getLastDayOfThisYear(): String {
        return "${LocalDate.now().year}-12-31"
    }
}