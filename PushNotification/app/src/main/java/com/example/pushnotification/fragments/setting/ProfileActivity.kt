package com.example.pushnotification.fragments.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.pushnotification.R
import com.vansuita.materialabout.builder.AboutBuilder
import com.vansuita.materialabout.views.AboutView

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val view: AboutView = AboutBuilder.with(this)
            .setPhoto(R.drawable.my_profile)
            .setCover(R.mipmap.profile_cover)
            .setName("김주환")
            .setSubTitle("Android Developer")
            .setBrief("안녕하세요! 컴퓨터공학과 김주환입니다.\n처음 개발해보는 앱이라 부족한점이 많지만\n꾸준히 업데이트 하도록 하겠습니다!")
            .setAppIcon(R.drawable.icon_sheep)
            .setAppName(R.string.app_name)
            .addLink(R.mipmap.google_play_store, "Play Store", "mdown.blog.me")
            //.addGooglePlayStoreLink("8002078663318221363")
            .addLink(R.mipmap.github, "GitHub", "https://github.com/juhwankim-dev")
            //.addLink(R.drawable.ic_tistory, "티스토리", "https://todaycode.tistory.com/category/%ED%82%A4%EC%9B%8C%EB%93%9C%20%EC%95%8C%EB%A6%BC%20%EC%95%B1%20%EC%A0%9C%EC%9E%91%EA%B8%B0")
            .addLink(R.drawable.ic_blog, "블로그", "https://blog.naver.com/mdown")
            .addFiveStarsAction()
            .setVersionNameAsAppSubTitle()
            .addShareAction(R.string.app_name)
            .setWrapScrollView(true)
            .setLinksAnimated(true)
            .setShowAsCard(true)
            .build()

        var layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT)

        addContentView(view, layoutParams)
    }
}
