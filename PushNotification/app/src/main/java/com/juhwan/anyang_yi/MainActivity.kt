package com.juhwan.anyang_yi

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.juhwan.anyang_yi.fragments.calendar.CalendarFragment
import com.juhwan.anyang_yi.fragments.home.HomeFragment
import com.juhwan.anyang_yi.fragments.keyword.KeywordFragment
import com.juhwan.anyang_yi.fragments.setting.SettingFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* -------------------------------프래그먼트 관련--------------------------------*/
        /* 래그먼트 변수*/
        val homeFragment =
            HomeFragment()
        val keywordFragment =
            KeywordFragment()
        val settingFragment =
            SettingFragment()
        val calendarFragment =
            CalendarFragment()

        // 현재 프래그먼트 화면을 생성하는 메소드
        makeCurrentFragment(homeFragment)

        // 프래그먼트가 클릭 되면 할 행동 (화면 전환)
        nav_view.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.navigation_home -> makeCurrentFragment(homeFragment)
                R.id.navigation_keyword -> makeCurrentFragment(keywordFragment)
                R.id.navigation_settings -> makeCurrentFragment(settingFragment)
                R.id.navigation_calendar -> makeCurrentFragment(calendarFragment)
            }
            true
        }
        /*-------------------------------------------------------------------------*/
    }

    // 현재 프래그먼트 화면을 생성하는 메소드
    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, fragment)
            //addToBackStack(null)
            commit()
        }
}
