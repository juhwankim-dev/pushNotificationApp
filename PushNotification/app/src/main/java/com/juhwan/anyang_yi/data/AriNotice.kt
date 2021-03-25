package com.juhwan.anyang_yi.data

data class AriNotice(var ariNotice: ArrayList<AriNoticeList>)

data class AriNoticeList (var link: String, var title: String, var date: String)