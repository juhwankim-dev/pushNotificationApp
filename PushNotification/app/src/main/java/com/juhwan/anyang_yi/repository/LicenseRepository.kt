package com.juhwan.anyang_yi.repository

import com.juhwan.anyang_yi.ui.setting.license.License

class LicenseRepository {
    fun requestLicense(): List<License> {
        return listOf(
            License(
                "firebase",
                "firebase",
                " "
            ),
            License(
                "jsoup",
                "mit",
                "Copyright © 2009 - 2021 Jonathan Hedley (https://jsoup.org/)"
            ),
            License(
                "retrofit2",
                "apache",
                "Copyright 2013 Square, Inc."
            ),
            License(
                "rxjava2",
                "apache",
                "Copyright (c) 2016-present, RxJava Contributors."
            ),
            License(
                "lottie",
                "lottie",
                "Copyright 2018 Airbnb, Inc."
            ),
            License(
                "inko",
                "mit",
                "Copyright (c) 2020 kimcore"
            ),
            License(
                "FloatingActionButton",
                "apache",
                "Copyright 2014 Zendesk"
            ),
            License(
                "StickyTimeLine",
                "apache",
                "Copyright 2019 Jeong Seok-Won"
            )
        )
    }
}