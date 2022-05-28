package com.juhwan.anyang_yi.present.views.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.FragmentHomeBinding
import com.juhwan.anyang_yi.present.config.BaseFragment
import com.juhwan.anyang_yi.present.views.home.keyword.KeywordActivity
import com.juhwan.anyang_yi.present.views.home.notice.NoticeFragment
import com.juhwan.anyang_yi.present.views.home.social.SocialFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val model: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.model = model
        initViewPager2()

        binding!!.ivNotification.setOnClickListener {
            startActivity(Intent(context, KeywordActivity::class.java))
        }
    }

    private fun initViewPager2() {
        binding!!.tlHome.tabTextColors = resources.getColorStateList(R.color.tab_icon, null)
        val tabTextList = arrayListOf("홈페이지", "SNS")

        binding!!.vpHome.adapter = CustomFragmentStateAdapter(requireActivity())
        //binding!!.viewPagerNotice.isUserInputEnabled = false // 스와이프 막는 설정
        TabLayoutMediator(binding!!.tlHome, binding!!.vpHome) {
                tab, position ->
            tab.text = tabTextList[position]
        }.attach()
    }

    inner class CustomFragmentStateAdapter(fragmentActivity: FragmentActivity):
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
            return 2
        }

        override fun createFragment(position: Int): Fragment {
            return when(position) {
                0 -> NoticeFragment()
                else -> SocialFragment()
            }
        }
    }
}
