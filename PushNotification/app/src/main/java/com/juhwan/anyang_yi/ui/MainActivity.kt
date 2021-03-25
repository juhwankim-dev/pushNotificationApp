package com.juhwan.anyang_yi.ui

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ActivityMainBinding
import com.juhwan.anyang_yi.repository.ContactRepository
import com.juhwan.anyang_yi.repository.InitialRepository
import com.juhwan.anyang_yi.repository.KakaoRepository


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        KakaoRepository.loadInitialData()

        // 네비게이션들을 담는 호스트
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host) as NavHostFragment

        // 네비게이션 컨트롤러
        val navController = navHostFragment.navController

        // 바템 네비게이션 뷰와 네비게이션을 묶어준다.
        NavigationUI.setupWithNavController(binding.myBottomNav, navController)
    }
}
