package com.juhwan.anyang_yi.data.model

data class ScheduleEntity(
    val items: ArrayList<Items>
)

data class Items(
    val startDate: String,
    val endDate: String,
    val title: String
)
