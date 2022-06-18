package com.juhwan.anyang_yi.data.repository

import com.juhwan.anyang_yi.domain.model.License

class LicenseRepository {
    fun requestLicense(): List<License> {
        return listOf(
            License(
                "dagger hilt",
                null
            ),
            License(
                "paging",
                null
            ),
            License(
                "livedata",
                null
            ),
            License(
                "room",
                null
            ),
            License(
                "retrofit2",
                "https://github.com/square/retrofit/blob/master/LICENSE.txt"
            ),
            License(
                "coroutine",
                "https://github.com/Kotlin/kotlinx.coroutines/blob/master/LICENSE.txt"
            ),
            License(
                "glide",
                "https://github.com/bumptech/glide/blob/master/LICENSE"
            ),
            License(
                "firebase",
                "https://firebase.google.com/terms?hl=ko"
            ),
            License(
                "jsoup",
                "https://github.com/jhy/jsoup/blob/master/LICENSE"
            ),
            License(
                "lottie",
                "https://github.com/airbnb/lottie-android/blob/master/LICENSE"
            ),
            License(
                "inko",
                "https://github.com/kimcore/inko.kt/blob/master/LICENSE"
            ),
            License(
                "FloatingActionButton",
                "https://github.com/zendesk/android-floating-action-button/blob/master/LICENSE"
            ),
            License(
                "StickyTimeLine",
                "https://github.com/sangcomz/StickyTimeLine/blob/master/LICENSE"
            )
        )
    }
}