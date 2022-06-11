package com.juhwan.anyang_yi.domain.model

data class AppNotice (
    var content: String,
    var date: String,
    var title: String,
    var isNew: Boolean
)