package com.example.pushnotification

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.pushnotification.fragments.home.HomeFragment
import com.example.pushnotification.fragments.keyword.KeywordFragment
import com.example.pushnotification.fragments.setting.SettingFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
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

        // 현재 프래그먼트 화면을 생성하는 메소드
        makeCurrentFragment(homeFragment)

        // 프래그먼트가 클릭 되면 할 행동 (화면 전환)
        nav_view.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.navigation_home -> makeCurrentFragment(homeFragment)
                R.id.navigation_home2 -> {
                    makeCurrentFragment(keywordFragment)
                }
                R.id.navigation_home3 -> makeCurrentFragment(settingFragment)
            }
            true
        }
        /*-------------------------------------------------------------------------*/
    }

    // 현재 프래그먼트 화면을 생성하는 메소드
    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, fragment)
            commit()
        }
}
