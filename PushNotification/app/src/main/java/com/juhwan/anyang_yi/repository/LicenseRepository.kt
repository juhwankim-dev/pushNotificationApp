package com.juhwan.anyang_yi.repository

import com.juhwan.anyang_yi.ui.setting.license.License

class LicenseRepository {
    fun requestLicense(): List<License> {
        return listOf(
            License(
                "inko",
                "mit",
                "Copyright (c) 2020 kimcore"
            ),
            License(
                "lottie",
                "apache",
                "Copyright 2018 Airbnb, Inc."
            ),
            License(
                "firebase-database",
                "firebase",
                " "
            ),
            License(
                "firebase-messaging",
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
                "MaterialAbout+",
                "mit",
                "Copyright (c) 2016 Arleu Cezar Vansuita Júnior"
            ),
            License(
                "StickyTimeLine",
                "apache",
                "Copyright 2019 Jeong Seok-Won"
            ),
            License(
                "lottie Love Explosion Animation",
                "lottie",
                "CC by Chris Gannon"
            )
        )
    }
}