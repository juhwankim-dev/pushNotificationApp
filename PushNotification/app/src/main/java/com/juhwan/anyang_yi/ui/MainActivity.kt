package com.juhwan.anyang_yi.ui

import android.annotation.SuppressLint
import android.app.Fragment
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ActivityMainBinding
import com.juhwan.anyang_yi.network.AriNotice
import com.juhwan.anyang_yi.ui.keyword.KeywordFragment
import com.juhwan.anyang_yi.ui.notice.NoticeFragment
import com.juhwan.anyang_yi.ui.notice.NoticeViewModel
import com.juhwan.anyang_yi.ui.schedule.ScheduleFragment
import com.juhwan.anyang_yi.ui.setting.SettingFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var noticeViewModel: NoticeViewModel

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        noticeViewModel = ViewModelProvider(this).get(NoticeViewModel::class.java)

        val noticeFragment = NoticeFragment()
        val keywordFragment = KeywordFragment()
        val scheduleFragment = ScheduleFragment()
        val settingFragment = SettingFragment()

/*        // 네비게이션들을 담는 호스트
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host) as NavHostFragment

        // 네비게이션 컨트롤러
        val navController = navHostFragment.navController

        // 바템 네비게이션 뷰와 네비게이션을 묶어준다.
        NavigationUI.setupWithNavController(binding.myBottomNav, navController)*/

        binding.myBottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.noticeFragment -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.my_nav_host, noticeFragment)
                        commit()
                    }
                }
                R.id.keywordFragment -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.my_nav_host, keywordFragment)
                        commit()
                    }
                }
                R.id.scheduleFragment -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.my_nav_host, scheduleFragment)
                        commit()
                    }
                }
                R.id.settingFragment -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.my_nav_host, settingFragment)
                        commit()
                    }
                }
            }
            true
        }
    }
}
