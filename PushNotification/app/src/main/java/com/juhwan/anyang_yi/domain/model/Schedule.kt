package com.juhwan.anyang_yi.domain.model

data class Schedule (
    var startYear: String,
    var startMonth: String,
    var startDay: String,
    var endYear: String,
    var endMonth: String,
    var endDay: String,
    var period: String,
    var content: String
)