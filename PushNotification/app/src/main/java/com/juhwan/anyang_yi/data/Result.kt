package com.juhwan.anyang_yi.data

data class Result(var resultList: ArrayList<ResultList>)

data class ResultList (
    var WRITER: String,
    var WRITE_DATE2: String,
    var B_IDX: String,
    var SUBJECT: String
)