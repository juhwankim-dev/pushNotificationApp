package com.juhwan.anyang_yi.ui.notice

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.FragmentNoticeBinding
import com.juhwan.anyang_yi.ui.notice.keyword.KeywordActivity
import com.juhwan.anyang_yi.ui.notice.homepage.HomepageFragment
import com.juhwan.anyang_yi.ui.notice.sns.SNSFragment

class NoticeFragment : Fragment() {

    lateinit var binding: FragmentNoticeBinding
    private val model: NoticeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding = FragmentNoticeBinding.inflate(inflater, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notice, container, false)
        //binding = DataBindingUtil.setContentView(this, R.layout.fragment_notice)
        binding.model = model
        binding.lifecycleOwner = this

        initViewPager2()

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.ivNotification.setOnClickListener {
            startActivity(Intent(context, KeywordActivity::class.java))
        }
    }

    /*
    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
    */

    private fun initViewPager2() {
        binding!!.tabLayoutNotice.tabTextColors = resources.getColorStateList(R.color.tab_icon, null)
        val tabTextList = arrayListOf("홈페이지", "SNS")

        binding!!.viewPagerNotice.adapter = CustomFragmentStateAdapter(requireActivity())
        //binding!!.viewPagerNotice.isUserInputEnabled = false // 스와이프 막는 설정
        TabLayoutMediator(binding!!.tabLayoutNotice, binding!!.viewPagerNotice) {
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
                0 -> HomepageFragment()
                else -> SNSFragment()
            }
        }
    }
}
