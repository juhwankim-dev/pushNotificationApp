package com.juhwan.anyang_yi.data.model

data class KakaoEntity(
    val posts: Posts
)

data class Posts(
    val items: ArrayList<Item>
)

data class Item(
    val created_at: Long,
    val media: ArrayList<Media>,
    val title: String,
    val permalink: String
)

data class Media(
    val medium: Medium,
    val small_url: String
)

data class Medium(
    val small_url: String
)
