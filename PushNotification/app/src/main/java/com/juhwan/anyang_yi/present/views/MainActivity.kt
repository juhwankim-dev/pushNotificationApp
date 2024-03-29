package com.juhwan.anyang_yi.present.views

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ActivityMainBinding
import com.juhwan.anyang_yi.present.config.ApplicationClass.Companion.authReference
import com.juhwan.anyang_yi.present.config.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 네비게이션들을 담는 호스트
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_main) as NavHostFragment
        // 네비게이션 컨트롤러
        val navController = navHostFragment.navController
        // 바템 네비게이션 뷰와 네비게이션을 묶어준다.
        NavigationUI.setupWithNavController(binding.bnvMain, navController)
    }

    override fun onDestroy() {
        super.onDestroy()

        if(authReference.currentUser != null) {
            authReference.currentUser!!.delete()
        }
    }
}
