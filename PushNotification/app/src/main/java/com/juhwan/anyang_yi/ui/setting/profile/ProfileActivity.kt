package com.juhwan.anyang_yi.ui.setting.profile

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.awesomedialog.AwesomeDialog
import com.example.awesomedialog.body
import com.example.awesomedialog.icon
import com.example.awesomedialog.title
import com.juhwan.anyang_yi.R
import com.vansuita.materialabout.builder.AboutBuilder
import com.vansuita.materialabout.views.AboutView
class ProfileActivity : AppCompatActivity() {

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_profile)

        val view: AboutView = AboutBuilder.with(this)
            .setPhoto(R.drawable.my_profile)
            .setCover(R.mipmap.profile_cover)
            .setName("김주환")
            .setSubTitle("Android Developer")
            .setBrief("안녕하세요! 컴퓨터공학과 김주환입니다.")
            .setAppIcon(R.drawable.icon_sheep)
            .setAppName(R.string.app_name)
            .addLink(R.mipmap.google_play_store, "Play Store", "https://play.google.com/store/apps/developer?id=Kim+Juhwan")
            //.addGooglePlayStoreLink("6396811699168376907")
            .addLink(R.mipmap.github, "GitHub", "https://github.com/juhwankim-dev/pushNotificationApp")
            .addLink(R.drawable.ic_tistory, "티스토리", "https://todaycode.tistory.com/")
            .addLink(R.drawable.ic_blog, "블로그", "https://blog.naver.com/mdown")
            .addEmailLink("juhwan.dev@gmail.com")
            .addAction(R.mipmap.donate, "Thanks To", View.OnClickListener {
                AwesomeDialog.build(this)
                    .title("이선경 님")
                    .body("Web Developer\n\n\n 웹, 통신 분야 등 많은 부분에 있어서\n도움을 주신 웹 개발자 이선경님께\n감사의 말씀을 전합니다.")
                    .icon(R.drawable.sg_profile)
            })
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
