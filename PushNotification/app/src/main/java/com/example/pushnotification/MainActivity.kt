package com.example.pushnotification

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.pushnotification.fragments.home.HomeFragment
import com.example.pushnotification.fragments.keyword.KeywordFragment
import com.example.pushnotification.fragments.setting.SettingFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT // 가로모드 고정
        setContentView(R.layout.activity_main)

        /* -------------------------------프래그먼트 관련--------------------------------*/
        /* 래그먼트 변수*/
        val homeFragment =
            HomeFragment()
        val keywordFragment =
            KeywordFragment()
        val settingFragment =
            SettingFragment()

        // 현재 프래그먼트 화면을 생성하는 메소드
        makeCurrentFragment(homeFragment)

        // 프래그먼트가 클릭 되면 할 행동 (화면 전환)
        nav_view.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.navigation_home -> makeCurrentFragment(homeFragment)
                R.id.navigation_keyword -> {
                    makeCurrentFragment(keywordFragment)
                }
                R.id.navigation_settings -> makeCurrentFragment(settingFragment)
            }
            true
        }
        /*-------------------------------------------------------------------------*/
    }

    // 현재 프래그먼트 화면을 생성하는 메소드
    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            //addToBackStack(null)
            replace(R.id.nav_host_fragment, fragment)
            commit()
        }

    private fun firstFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(
                R.anim.slide_in_left,
                R.anim.slide_out_left)
            //addToBackStack(null)
            replace(R.id.nav_host_fragment, fragment)
            commit()
        }

    private fun thirdFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_right)
            //addToBackStack(null)
            replace(R.id.nav_host_fragment, fragment)
            commit()
        }
}
